package com.beinternational.product_management_be.logic.controller.product;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beinternational.product_management_be.common.MessageCommon;
import com.beinternational.product_management_be.logic.service.category.ICategoryService;
import com.beinternational.product_management_be.logic.service.product.IProductService;
import com.beinternational.product_management_be.model.db.category.Category;
import com.beinternational.product_management_be.model.db.product.Product;
import com.beinternational.product_management_be.model.view.ErrorResDTO;
import com.beinternational.product_management_be.model.view.product.ProductCreateReqDTO;
import com.beinternational.product_management_be.model.view.product.ProductDelResDTO;
import com.beinternational.product_management_be.model.view.product.ProductDetailGetResDTO;
import com.beinternational.product_management_be.model.view.product.ProductListGetReqDTO;
import com.beinternational.product_management_be.model.view.product.ProductListGetResDTO;
import com.beinternational.product_management_be.model.view.product.ProductUpdReqDTO;
import com.beinternational.product_management_be.utils.StringUtils;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProductController {

	@Autowired
	private IProductService productService;

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private ModelMapper modelMapper;

	private static final int MIN_NAME_LENGH = 10;

	/**
	 * get all product
	 * 
	 * @param productListGetReqDTO
	 * @return
	 */
	@GetMapping("/api/products")
	public ResponseEntity<Object> getAllProducts(ProductListGetReqDTO productListGetReqDTO) {

		try {
			ProductListGetResDTO productListGetResDTO = new ProductListGetResDTO();

			Page<Product> productGetList = productService.findAll(productListGetReqDTO.getP(),
					productListGetReqDTO.getS(), productListGetReqDTO.getSortList());

			productGetList.getContent().stream().forEach(p -> {
				ProductListGetResDTO.Product data = modelMapper.map(p, ProductListGetResDTO.Product.class);
				data.setProductCategoryName(p.getProductCategory().getCategoryName());
				productListGetResDTO.getProductList().add(data);
			});
			productListGetResDTO.setTotalRecords(productGetList.getTotalElements());

			return new ResponseEntity<>(productListGetResDTO, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(new ErrorResDTO(MessageCommon.INTERNAL_ERROR), HttpStatus.OK);
		}
	}

	/**
	 * get a specific product
	 * 
	 * @param productCode
	 * @return
	 */
	@GetMapping(value = { "/api/products/{productCode}", "/api/products/" })
	public ResponseEntity<Object> getProductDetail(@PathVariable(required = false) String productCode) {

		try {
			ProductDetailGetResDTO productDetailGetResDTO = new ProductDetailGetResDTO();

			List<Category> categoryList = categoryService.findAll();
			categoryList.stream().forEach(c -> {
				ProductDetailGetResDTO.Category category = modelMapper.map(c, ProductDetailGetResDTO.Category.class);
				productDetailGetResDTO.getCategoryList().add(category);
			});

			// case init form to get category list
			if (productCode == null) {
				return new ResponseEntity<>(productDetailGetResDTO, HttpStatus.OK);
			}

			Product productGet = productService.findById(productCode);

			if (productGet == null) {
				return new ResponseEntity<>(productDetailGetResDTO, HttpStatus.NOT_FOUND);
			}

			productDetailGetResDTO.setProduct(modelMapper.map(productGet, ProductDetailGetResDTO.Product.class));
			productDetailGetResDTO.getProduct()
					.setProductCategoryId(productGet.getProductCategory().getCategoryId().toString());

			return new ResponseEntity<>(productDetailGetResDTO, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(new ErrorResDTO(MessageCommon.INTERNAL_ERROR),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * create new product
	 * 
	 * @param productCreateReqDTO
	 * @return
	 */
	@PostMapping("/api/products")
	public ResponseEntity<Object> createProduct(@RequestBody ProductCreateReqDTO productCreateReqDTO) {

		try {
			if (StringUtils.isEmpty(productCreateReqDTO.getProductCode())) {
				return new ResponseEntity<>(
						new ErrorResDTO(MessageCommon.setMessage(MessageCommon.EMPTY, "Product Code")),
						HttpStatus.BAD_REQUEST);
			}

			if (StringUtils.isEmpty(productCreateReqDTO.getProductName())) {
				return new ResponseEntity<>(
						new ErrorResDTO(MessageCommon.setMessage(MessageCommon.EMPTY, "Product Name")),
						HttpStatus.BAD_REQUEST);
			}

			if (productCreateReqDTO.getProductName().length() < MIN_NAME_LENGH) {
				return new ResponseEntity<>(new ErrorResDTO(MessageCommon.setMessage(MessageCommon.INVALID_LENGTH,
						MIN_NAME_LENGH + StringUtils.EMPTY, "the product")), HttpStatus.BAD_REQUEST);
			}

			Product productGet = productService.findById(productCreateReqDTO.getProductCode());

			if (productGet != null) {
				return new ResponseEntity<>(
						new ErrorResDTO(MessageCommon.setMessage(MessageCommon.EXISTED, "Product Code")),
						HttpStatus.FOUND);
			}

			UUID categoryUUID = StringUtils.convertToUUID(productCreateReqDTO.getProductCategoryId());

			if (categoryUUID == null) {
				return new ResponseEntity<>(
						new ErrorResDTO(MessageCommon.setMessage(MessageCommon.INVALID, "Category ID")),
						HttpStatus.BAD_REQUEST);
			}

			Category categoryFind = categoryService.findById(categoryUUID);

			if (categoryFind == null) {
				return new ResponseEntity<>(
						new ErrorResDTO(MessageCommon.setMessage(MessageCommon.NOT_FOUND, "Product Category")),
						HttpStatus.NOT_FOUND);
			}

			Product productSave = modelMapper.map(productCreateReqDTO, Product.class);
			productSave.setProductCategory(categoryFind);

			productSave = productService.save(productSave);

			ProductListGetResDTO.Product productCreatResDTO = modelMapper.map(productSave,
					ProductListGetResDTO.Product.class);
			productCreatResDTO.setProductCategoryName(productSave.getProductCategory().getCategoryName());

			return new ResponseEntity<>(productCreatResDTO, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(new ErrorResDTO(MessageCommon.INTERNAL_ERROR),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * update a product
	 * 
	 * @param productCode
	 * @param productUpdReqDTO
	 * @return
	 */
	@PutMapping("/api/products/{productCode}")
	public ResponseEntity<Object> updateProduct(@PathVariable String productCode,
			@RequestBody ProductUpdReqDTO productUpdReqDTO) {

		try {
			Product productGet = productService.findById(productCode);

			if (productGet == null) {
				return new ResponseEntity<>(
						new ErrorResDTO(MessageCommon.setMessage(MessageCommon.NOT_FOUND, "Product")),
						HttpStatus.NOT_FOUND);
			}

			UUID categoryUUID = StringUtils.convertToUUID(productUpdReqDTO.getProductCategoryId());

			if (categoryUUID == null) {
				return new ResponseEntity<>(
						new ErrorResDTO(MessageCommon.setMessage(MessageCommon.INVALID, "Category ID")),
						HttpStatus.BAD_REQUEST);
			}

			Category categoryFind = categoryService.findById(categoryUUID);

			if (categoryFind == null) {
				return new ResponseEntity<>(
						new ErrorResDTO(MessageCommon.setMessage(MessageCommon.NOT_FOUND, "Product Category")),
						HttpStatus.NOT_FOUND);
			}

			if (StringUtils.isEmpty(productUpdReqDTO.getProductName())) {
				return new ResponseEntity<>(
						new ErrorResDTO(MessageCommon.setMessage(MessageCommon.EMPTY, "Product Name")),
						HttpStatus.BAD_REQUEST);
			}

			if (productUpdReqDTO.getProductName().length() < MIN_NAME_LENGH) {
				return new ResponseEntity<>(new ErrorResDTO(MessageCommon.setMessage(MessageCommon.INVALID_LENGTH,
						MIN_NAME_LENGH + StringUtils.EMPTY, "the product")), HttpStatus.BAD_REQUEST);
			}

			productGet = modelMapper.map(productUpdReqDTO, Product.class);
			productGet.setProductCode(productCode);
			productGet.setProductCategory(categoryFind);

			Product productSave = productService.save(productGet);

			ProductListGetResDTO.Product productUpdResDTO = modelMapper.map(productSave,
					ProductListGetResDTO.Product.class);
			productUpdResDTO.setProductCategoryName(productSave.getProductCategory().getCategoryName());

			return new ResponseEntity<>(productUpdResDTO, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(new ErrorResDTO(MessageCommon.INTERNAL_ERROR),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * delete a product
	 * 
	 * @param productCode
	 * @param productUpdReqDTO
	 * @return
	 */
	@DeleteMapping("/api/products/{productCode}")
	public ResponseEntity<Object> delProduct(@PathVariable String productCode) {

		try {
			Product productGet = productService.findById(productCode);

			if (productGet == null) {
				return new ResponseEntity<>(
						new ErrorResDTO(MessageCommon.setMessage(MessageCommon.NOT_FOUND, "Product")),
						HttpStatus.NOT_FOUND);
			}

			productService.deleteById(productCode);

			return new ResponseEntity<>(new ProductDelResDTO(productCode), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(new ErrorResDTO(MessageCommon.INTERNAL_ERROR),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

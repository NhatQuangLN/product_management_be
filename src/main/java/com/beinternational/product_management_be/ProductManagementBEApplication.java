package com.beinternational.product_management_be;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.beinternational.product_management_be.logic.service.category.ICategoryService;
import com.beinternational.product_management_be.logic.service.product.IProductService;
import com.beinternational.product_management_be.model.db.category.Category;
import com.beinternational.product_management_be.model.db.product.Product;

@SpringBootApplication
public class ProductManagementBEApplication implements CommandLineRunner {

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private IProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(ProductManagementBEApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Initialize category data
		Category categoryFs = categoryService.save(Category.builder().categoryName("Fashion").build());
		Category categoryC = categoryService.save(Category.builder().categoryName("Cosmetics").build());
		Category categoryFo = categoryService.save(Category.builder().categoryName("Food").build());
		Category categoryB = categoryService.save(Category.builder().categoryName("Beverage").build());
		Category categoryH = categoryService.save(Category.builder().categoryName("Household appliances").build());

		// Initialize product data
		productService.save(Product.builder().productCode("P01").productCategory(categoryFs)
				.productName("AIRism Cotton Relaxed Fit Half Sleeve T-Shirt").productBrand("Uniqlo")
				.productType("Male Shirt")
				.productDescription(
						"Smooth 'AIRism' fabric with the look of cotton. Relaxed silhouette with a chest pocket.")
				.build());
		productService.save(Product.builder().productCode("P02").productCategory(categoryFs)
				.productName("Slim Fit Jeans").productBrand("Calvin Klein").productType("Male Pants")
				.productDescription("Classic slim fit jeans made from premium denim fabric.").build());
		productService.save(Product.builder().productCode("P03").productCategory(categoryB).productName("Green Tea")
				.productBrand("Lipton").productType("Tea")
				.productDescription("Lipton Green Tea Bags, 100% Natural Tea, 100 count.").build());
		productService
				.save(Product.builder().productCode("P04").productCategory(categoryH)
						.productName("7 Quart Oval Manual Slow Cooker").productBrand("Crock-Pot").productType("Cooker")
						.productDescription(
								"Spacious 7 quart manual slow cooker serves 9 plus people or fits a 7 pounds; Roast.")
						.build());
		productService.save(Product.builder().productCode("P05").productCategory(categoryC)
				.productName("Liquid Foundation").productBrand("L'Oréal").productType("Foundation")
				.productDescription("L'Oréal True Match Super-Blendable Liquid Foundation for a flawless complexion.")
				.build());
		productService
				.save(Product.builder().productCode("P06").productCategory(categoryFo).productName("Grilled Salmon")
						.productBrand("Local Seafood Market").productType("Seafood")
						.productDescription(
								"Fresh grilled salmon fillet served with lemon butter sauce and steamed vegetables.")
						.build());
		productService.save(Product.builder().productCode("P07").productCategory(categoryFs).productName("Denim Jacket")
				.productBrand("Levi's").productType("Male Jacket")
				.productDescription("Classic denim jacket with button closure and multiple pockets.").build());
		productService.save(Product.builder().productCode("P08").productCategory(categoryFs)
				.productName("Floral Print Dress").productBrand("Zara").productType("Female Dress")
				.productDescription("Lightweight floral print dress with a cinched waist and ruffled hem.").build());
		productService.save(Product.builder().productCode("P09").productCategory(categoryC)
				.productName(" Invisible Pressed Powder").productBrand("Covergirl").productType("Pressed Powder")
				.productDescription(
						"Covergirl Clean Invisible Pressed Powder, Lightweight, Breathable, Vegan Formula, Classic Ivory 110, 0.38oz")
				.build());
		productService.save(Product.builder().productCode("P010").productCategory(categoryC)
				.productName("Waterproof Mascara").productBrand("Maybelline").productType("Mascara")
				.productDescription(
						"Maybelline Lash Sensational Waterproof Mascara for voluminous and lengthened lashes.")
				.build());
		productService.save(Product.builder().productCode("P11").productCategory(categoryFo)
				.productName("Spaghetti Bolognese").productBrand("Mama's Kitchen").productType("Pasta")
				.productDescription(
						"Classic spaghetti with rich Bolognese sauce made with ground beef, tomatoes, and herbs.")
				.build());
		productService.save(Product.builder().productCode("P12").productCategory(categoryC).productName("Lip Gloss Set")
				.productBrand("NYX").productType("Lip Gloss")
				.productDescription(
						"NYX Professional Makeup Butter Gloss Set featuring a variety of shades for a glossy finish.")
				.build());
		productService.save(Product.builder().productCode("P13").productCategory(categoryC)
				.productName("Matte Lipstick").productBrand("MAC").productType("Lipstick")
				.productDescription("Matte lipstick in various shades for a long-lasting, bold look.").build());
		productService.save(Product.builder().productCode("P14").productCategory(categoryC)
				.productName("Eyeshadow Palette").productBrand("Urban Decay").productType("Eyeshadow Palette")
				.productDescription(
						"Urban Decay Naked Eyeshadow Palette featuring a range of neutral shades for everyday wear.")
				.build());
		productService.save(Product.builder().productCode("P15").productCategory(categoryFo).productName("Short Rib")
				.productBrand("PIZZERIA VETRI").productType("Pizza")
				.productDescription("Braised short rib, sofrito, mozzarella, parmesan, mixed herbs.").build());

		productService.save(Product.builder().productCode("P16").productCategory(categoryB)
				.productName("7-UP, Variety Pack – Powder Drink Mix - (5 boxes, 30 sticks)").productBrand("7-UP")
				.productType("Powder Drink")
				.productDescription("Sugar Free & Delicious, Makes 30 flavored water beverages.").build());
		productService.save(Product.builder().productCode("P17").productCategory(categoryB)
				.productName("Espresso Beans").productBrand("Starbucks").productType("Coffee")
				.productDescription("Dark roasted espresso beans for a rich and bold coffee experience.").build());
		productService.save(Product.builder().productCode("P18").productCategory(categoryH)
				.productName("Robot Vacuum Cleaner").productBrand("iRobot").productType("Vacuum Cleaner")
				.productDescription("Smart robot vacuum cleaner with mapping technology for efficient cleaning.")
				.build());
		productService.save(Product.builder().productCode("P19").productCategory(categoryH)
				.productName("Instant Pot Duo").productBrand("Instant Pot").productType("Pressure Cooker")
				.productDescription("Multi-functional pressure cooker with 7-in-1 capabilities.").build());
		productService.save(Product.builder().productCode("P20").productCategory(categoryFs)
				.productName("Floral Maxi Dress").productBrand("H&M").productType("Female Dress")
				.productDescription("Flowy maxi dress with a floral print, perfect for summer outings.").build());
		productService.save(Product.builder().productCode("P21").productCategory(categoryFs).productName("Leather Belt")
				.productBrand("Gucci").productType("Unisex Accessory")
				.productDescription("Genuine leather belt with a signature GG buckle.").build());
		productService.save(Product.builder().productCode("P22").productCategory(categoryFo)
				.productName("Chicken Alfredo Pasta").productBrand("Olive Garden").productType("Pasta Dish")
				.productDescription(
						"Olive Garden's signature Chicken Alfredo pasta made with creamy Alfredo sauce and grilled chicken.")
				.build());
		productService.save(Product.builder().productCode("P23").productCategory(categoryFo).productName("Vegan Burger")
				.productBrand("Beyond Meat").productType("Burger")
				.productDescription(
						"Beyond Burger made with plant-based ingredients, perfect for vegans and vegetarians.")
				.build());
		productService.save(Product.builder().productCode("P24").productCategory(categoryFo)
				.productName("Sushi Platter").productBrand("Sushi Samba").productType("Sushi")
				.productDescription("Assorted sushi platter featuring nigiri, maki rolls, and sashimi.").build());
		productService.save(Product.builder().productCode("P25").productCategory(categoryB)
				.productName("Sparkling Water").productBrand("Perrier").productType("Carbonated Water")
				.productDescription("Perrier Sparkling Mineral Water, refreshing and naturally carbonated.").build());
		productService.save(Product.builder().productCode("P26").productCategory(categoryB)
				.productName("Cold Brew Coffee").productBrand("Starbucks").productType("Coffee")
				.productDescription("Starbucks Cold Brew Coffee, smooth and refreshing with a hint of sweetness.")
				.build());
		productService.save(Product.builder().productCode("P27").productCategory(categoryB)
				.productName("Fruit Infused Water").productBrand("Hint").productType("Flavored Water")
				.productDescription(
						"Hint Fruit Infused Water, zero calories and no artificial sweeteners, available in various fruit flavors.")
				.build());
		productService.save(Product.builder().productCode("P28").productCategory(categoryH)
				.productName("Smart Thermostat").productBrand("Nest").productType("Thermostat")
				.productDescription(
						"Nest Learning Thermostat, smart and energy-efficient temperature control for your home.")
				.build());
		productService.save(Product.builder().productCode("P29").productCategory(categoryH).productName("Air Purifier")
				.productBrand("Dyson").productType("Air Purifier")
				.productDescription(
						"Dyson Pure Cool Air Purifier, removes allergens and pollutants while cooling the air.")
				.build());
		productService.save(Product.builder().productCode("P30").productCategory(categoryH)
				.productName("Robot Lawn Mower").productBrand("Husqvarna").productType("Lawn Care")
				.productDescription("Husqvarna Automower, robotic lawn mower for effortless lawn maintenance.")
				.build());
	}

}

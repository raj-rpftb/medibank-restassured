package api.endpoints;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import java.util.logging.Logger;

import api.utilities.GetBodyResponse;

/**
 * This class contains methods to interact with Product related endpoints
 */
public class ProductEndPoints {
	private static final Logger LOGGER = Logger.getLogger(GetBodyResponse.class.getName());

	/**
	 * This method is used to search a product by color
	 * 
	 * @param color This is the color of the product to be searched
	 * @return Response This returns the response of the API call
	 */
	public static Response searchProduct(String color) {
		try {
			Response res = given()
					.formParam("search_product", color)
					.when()
					.post(Routes.API_SEARCH_PRODUCT);
			return res;
		} catch (Exception e) {
			LOGGER.severe("Error while searching for product with color " + color + ": " + e.getMessage());
			throw e;
		}
	}

	/**
	 * This method is used to get all products
	 * 
	 * @return Response This returns the response of the API call
	 */
	public static Response getAllProducts() {
		try {
			Response res = given()
					.when()
					.get(Routes.API_GET_PRODUCT_LIST);
			return res;
		} catch (Exception e) {
			LOGGER.severe("Error while fetching all products: " + e.getMessage());
			throw e;
		}
	}
}

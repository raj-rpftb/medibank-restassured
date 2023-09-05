package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.ProductEndPoints;
import io.restassured.response.Response;
import api.utilities.GetBodyResponse;

import java.io.IOException;

/**
 * This class contains tests for the Product API.
 */
public class ProductTests {

	// Instance of GetBodyResponse to parse API responses
	private GetBodyResponse responseBodyParser;
	// Logger instance for logging test events
	private Logger testLogger;

	/**
	 * This method sets up the necessary objects before each test.
	 */
	@BeforeClass
	public void setup() {
		responseBodyParser = new GetBodyResponse();
		testLogger = LogManager.getLogger(this.getClass());
		testLogger.debug("Test setup completed.");
	}

	/**
	 * This method cleans up after each test.
	 */
	@AfterClass
	public void teardown() {
		testLogger.debug("Test teardown completed.");
	}

	/**
	 * This test checks the functionality of the 'getAllProducts' endpoint.
	 * 
	 * @throws IOException if an I/O error occurs during the test
	 */
	@Test()
	public void getAllProductList() throws IOException {
		testLogger.info("Starting test: Get All Product List");
		Response response;
		try {
			response = ProductEndPoints.getAllProducts();
		} catch (Exception e) {
			testLogger.error("Error while fetching all products: " + e.getMessage());
			throw e;
		}
		Assert.assertEquals(response.getStatusCode(), 200,
				"Expected status code 200, but got " + response.getStatusCode());
		Assert.assertEquals(response.body().asString(), responseBodyParser.getProductListResponse(),
				"Response body does not match expected data.");
	}

	/**
	 * This test checks the functionality of the 'searchProduct' endpoint.
	 * 
	 * @throws IOException if an I/O error occurs during the test
	 */
	@Test()
	public void postToSearchProduct() throws IOException {
		testLogger.info("Starting test: Post To Search Product");
		Response response;
		try {
			response = ProductEndPoints.searchProduct("Blue");
		} catch (Exception e) {
			testLogger.error("Error while searching for product: " + e.getMessage());
			throw e;
		}
		Assert.assertEquals(response.getStatusCode(), 200,
				"Expected status code 200, but got " + response.getStatusCode());
		Assert.assertEquals(response.body().asString(), responseBodyParser.getProductSearchResponse(),
				"Response body does not match expected data.");
	}
}

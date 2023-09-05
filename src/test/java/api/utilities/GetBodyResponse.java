package api.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 * This class is responsible for getting the body response from a file.
 */
public class GetBodyResponse {
	private static final Logger LOGGER = Logger.getLogger(GetBodyResponse.class.getName());

	/**
	 * This method reads the data from the productListResponse.json file and returns
	 * it as a string.
	 * 
	 * @return the data from the productListResponse.json file as a string
	 * @throws IOException if an I/O error occurs reading from the file or a
	 *                     malformed or unmappable byte sequence is read
	 */
	public String getProductListResponse() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/api/response/productListResponse.json";
		try {
			return new String(Files.readAllBytes(Paths.get(path)));
		} catch (IOException e) {
			LOGGER.severe("Failed to get product list response: " + e.getMessage());
			throw e;
		}
	}

	/**
	 * This method reads the data from the productSearchResponse.json file and
	 * returns it as a string.
	 * 
	 * @return the data from the productSearchResponse.json file as a string
	 * @throws IOException if an I/O error occurs reading from the file or a
	 *                     malformed or unmappable byte sequence is read
	 */
	public String getProductSearchResponse() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/api/response/productSearchResponse.json";
		try {
			return new String(Files.readAllBytes(Paths.get(path)));
		} catch (IOException e) {
			LOGGER.severe("Failed to get product search response: " + e.getMessage());
			throw e;
		}
	}
}
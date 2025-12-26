package psdpal.common;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

/**
 * APIClient (Core Engine Class)
 * ------------------------------------------------------- 
 * Responsible for executing all HTTP requests.
 *
 * Key Features: 
 * ✔ Dynamic base URLs per service 
 * ✔ Dynamic headers & tokens 
 * ✔ Supports all HTTP methods 
 * ✔ Unified logging (request + response) 
 * ✔ Allure reporting ready 
 * ✔ Automatically handles JSON bodies 
 * ✔ Extensible for query/path parameters
 *    Used internally by: - RequestBuilder - TokenManager - Services (ReqRes,RestfulBooker, IGF, etc.)
 */
public class APIClient {

	public APIClient() {
		// Enable request/response logging only on failure → cleaner reports
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}

	/**
	 * Create a base request specification.
	 * @param baseUrl Service base URL
	 * @param headers Custom headers (nullable)
	 * @return Prepared RequestSpecification
	 */
	public RequestSpecification createRequest(String baseUrl, Map<String, String> headers) {

		RequestSpecification spec = RestAssured
				.given()
				.baseUri(baseUrl)
				.relaxedHTTPSValidation() // helpful for QA/staging cert issues																				// QA/staging cert
				.contentType("application/json");

		// Attach dynamic headers
		if (headers != null && !headers.isEmpty()) {
			spec.headers(headers);
		}

		return spec;
	}

	/**
	 * Main execution method for all HTTP requests.
	 * @param spec     RequestSpecification
	 * @param method   HTTP method (GET/POST/PUT/PATCH/DELETE)
	 * @param endpoint API endpoint ("/api/users")
	 * @param body     JSON body (or POJO) for POST/PUT/PATCH
	 * @return Response object
	 */
	public Response send(RequestSpecification spec, Method method, String endpoint, Object body) {

		// Attach body only when needed
		if (body != null) {
			spec.body(body);
		}

		// Unified logging
		spec.log().all();

		Response response;

		try {
			response = spec.when().request(method, endpoint);

		} catch (Exception e) {
			throw new RuntimeException("API Request failed for endpoint: " + endpoint + "\nMethod: " + method
					+ "\nError: " + e.getMessage(), e);
		}

		// Log response
		response.then().log().all();
		return response;
	}
}

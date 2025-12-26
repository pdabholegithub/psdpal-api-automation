package psdpal.common;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * TokenManager
 * -------------------------------------------------------------------------
 * Generates, caches, and refreshes tokens.
 *
 * Features: ✔ Thread-safe (double-lock) ✔ Tokens fetched only when needed ✔
 * Auto-refresh on expiry ✔ Config-driven (no hardcoding) ✔ Supports multiple
 * services in future (reqres, IGF, restfulbooker)
 */
public class TokenManager {

	private static String token;
	private static long tokenGeneratedTime;

	// Token validity (10 minutes)
	private static final long TOKEN_VALIDITY = 10 * 60 * 1000;

	/**
	 * Main public entry. Will generate or return cached token.
	 */
	public static String getToken() {
		if (token == null || isTokenExpired()) {
			synchronized (TokenManager.class) { // Thread safety
				if (token == null || isTokenExpired()) {
					generateNewToken();
				}
			}
		}
		return token;
	}

	/**
	 * Check expiration based on timestamp difference.
	 */
	private static boolean isTokenExpired() {
		return (System.currentTimeMillis() - tokenGeneratedTime) > TOKEN_VALIDITY;
	}

	/**
	 * Calls auth API and fetches token dynamically.
	 */
	private static void generateNewToken() {
		System.out.println("⟳ Generating NEW token...");

		// Load auth payload from config
		String username = ConfigReader.getOrDefault("restful.user", "admin");
		String password = ConfigReader.getOrDefault("restful.password", "password123");

		Map<String, String> authBody = Map.of("username", username, "password", password);

		String baseUrl = ConfigReader.getOrDefault("restful.baseUrl", ConfigReader.getBaseUrl());

		Response response = given().baseUri(baseUrl).contentType(ContentType.JSON).body(authBody).when().post("/auth")
				.then().log().all().extract().response();

		validateAuthResponse(response);

		token = response.jsonPath().getString("token");
		tokenGeneratedTime = System.currentTimeMillis();

		System.out.println("✔ Token generated successfully.");
	}

	/**
	 * Validate response before extracting token.
	 */
	private static void validateAuthResponse(Response response) {

		int status = response.getStatusCode();

		if (status != 200 && status != 201) {
			throw new RuntimeException("❌ Token API failed. HTTP Status: " + status);
		}

		if (!response.getContentType().contains("application/json")) {
			throw new RuntimeException("❌ Token API did not return JSON.");
		}

		String tokenValue = response.jsonPath().getString("token");

		if (tokenValue == null || tokenValue.isEmpty()) {
			throw new RuntimeException("❌ Token field missing in response JSON!");
		}
	}
}

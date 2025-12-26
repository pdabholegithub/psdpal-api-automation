package psdpal.common;
// Common package for core reusable components shared across all APIs.

import java.util.UUID;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

/**
 * BaseAPI
 * ---------------------------------------------------------------------
 * This abstract class provides a reusable, configurable RequestSpecification
 * shared across all API test modules (ReqRes, Restful Booker, etc.).
 *
 * Enhancements included:
 *  - Automatic token injection (if required)
 *  - Global headers (Accept, correlation ID, etc.)
 *  - Timeout settings
 *  - Environment-based logging control
 *  - Optional overloaded method for multi-website support
 */

public abstract class BaseAPI {   // abstract → cannot be instantiated directly

    /**
     * Fetch the default request specification using the base URL from config.
     * This is the MOST commonly used method in your framework.
     */
    protected RequestSpecification reqSpec() {
        return buildSpec(ConfigReader.getBaseUrl());
    }

    /**
     * Overloaded method: Useful for multi-website frameworks.
     * Allows passing a specific base URL if the test needs it.
     */
    protected RequestSpecification reqSpec(String baseUrl) {
        return buildSpec(baseUrl);
    }


    /**
     * CORE SPEC BUILDER
     * This is where the full enterprise-level configuration happens.
     */
    private RequestSpecification buildSpec(String baseUrl) {

        // Fetch environment to control logging / behavior
        String env = ConfigReader.get("env").toUpperCase();

        // Generate unique correlation ID for backend debugging
        String correlationId = UUID.randomUUID().toString();

        // Optional: fetch authorization token (if TokenManager is implemented)
        String token = null;
        try {
            token = TokenManager.getToken();  // TokenManager should handle refresh
        } catch (Exception e) {
            // token is optional, handle gracefully
        }

        // -------------------------------
        // BUILD THE REQUEST SPECIFICATION
        // -------------------------------
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(baseUrl)                // Dynamic base URL
                .setContentType(ContentType.JSON)   // Default content type
                .addHeader("Accept", "application/json")  // Common header
                .addHeader("X-Correlation-ID", correlationId); // Helpful for tracing


        // Add Authorization token if available
        if (token != null) {
            builder.addHeader("Authorization", "Bearer " + token);
        }

        // ----------------------------------
        // TIMEOUT SETTINGS (VERY IMPORTANT)
        // ----------------------------------
        builder.setConfig(RestAssuredConfig.config().httpClient(
                HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", 8000)
                        .setParam("http.socket.timeout", 8000)
        ));


        // ------------------------------------------------
        // ENVIRONMENT-BASED LOGGING (BEST PRACTICE)
        // ------------------------------------------------
        switch (env) {
            case "DEV":
            case "LOCAL":
                builder.log(LogDetail.ALL);   // Full logging for debugging
                break;

            case "QA":
            case "UAT":
                builder.log(LogDetail.STATUS); // Medium logging
                break;

            case "PROD":
            	 // No logs for production
                break;

            default:
                builder.log(LogDetail.ALL);
        }

        // FINAL SPECIFICATION
        return builder.build();
    }
}

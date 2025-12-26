package psdpal.common;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * RequestBuilder
 * -------------------------------------------------------------------------
 * PURPOSE:
 *  - Build reusable RestAssured request specifications dynamically
 *  - Support multiple services (reqres, restful, igf, etc.)
 *  - Allows GET/POST/PUT/DELETE with optional path & query parameters
 *
 * BENEFITS:
 *  ✔ Avoid duplicate code in API test classes
 *  ✔ Single place to control headers, content-type, token injection
 *  ✔ Works across all services based on environment (dev/qa/uat/prod)
 */
public class RequestBuilder {

    /**
     * Builds a RequestSpecification dynamically based on service name.
     *
     * Example:
     *   defaultSpec("reqres")
     *   defaultSpec("restful")
     *
     * Reads baseUrl from ConfigReader → application-<env>.properties
     */
    public static RequestSpecification defaultSpec(String service) {

        String baseUrl = ConfigReader.getBaseUrlFor(service);

        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setRelaxedHTTPSValidation()        // Ignore SSL problems in QA/Stage
                .setContentType(ContentType.JSON)   // Default content type
                .addHeader("Accept", "application/json") // Default Accept header
                .build();
    }


    // =========================================================================
    // GET REQUESTS
    // =========================================================================

    /**
     * GET with optional path params.
     *
     * Example:
     *   get("reqres", "/api/users/{id}", "id", 2);
     */
    public static Response get(String service, String path, Object... pathParams) {

        RequestSpecification spec = defaultSpec(service);

        return given()
                .spec(spec)
                .pathParams(pathParamsMap(pathParams))
                .when()
                .get(path)
                .then()
                .log().all()     // log request+response
                .extract()
                .response();
    }

    /**
     * GET with query parameters.
     *
     * Example:
     *   getWithQuery("reqres", "/api/users", "page", "2");
     */
    public static Response getWithQuery(String service, String path, String paramKey, String paramValue) {

        RequestSpecification spec = defaultSpec(service);

        return given()
                .spec(spec)
                .queryParam(paramKey, paramValue)
                .when()
                .get(path)
                .then()
                .log().all()
                .extract()
                .response();
    }


    // =========================================================================
    // POST REQUESTS
    // =========================================================================

    /**
     * POST request with JSON body (String or POJO allowed).
     */
    public static Response post(String service, String path, Object body) {

        RequestSpecification spec = defaultSpec(service);

        return given()
                .spec(spec)
                .body(body)
                .when()
                .post(path)
                .then()
                .log().all()
                .extract()
                .response();
    }


    // =========================================================================
    // PUT REQUESTS
    // =========================================================================

    public static Response put(String service, String path, Object body, Object... pathParams) {

        RequestSpecification spec = defaultSpec(service);

        return given()
                .spec(spec)
                .pathParams(pathParamsMap(pathParams))
                .body(body)
                .when()
                .put(path)
                .then()
                .log().all()
                .extract()
                .response();
    }


    // =========================================================================
    // DELETE REQUESTS
    // =========================================================================

    public static Response delete(String service, String path, Object... pathParams) {

        RequestSpecification spec = defaultSpec(service);

        return given()
                .spec(spec)
                .pathParams(pathParamsMap(pathParams))
                .when()
                .delete(path)
                .then()
                .log().all()
                .extract()
                .response();
    }


    // =========================================================================
    // UTILITY — Convert ("id", 123) → Map
    // =========================================================================

    /**
     * Converts key/value pairs into a map.
     *
     * Example:
     *   pathParamsMap("id", 10, "type", "admin")
     */
    private static Map<String, Object> pathParamsMap(Object... kv) {

        Map<String, Object> map = new HashMap<>();

        for (int i = 0; i + 1 < kv.length; i += 2) {
            map.put(String.valueOf(kv[i]), kv[i + 1]);
        }

        return map;
    }
}

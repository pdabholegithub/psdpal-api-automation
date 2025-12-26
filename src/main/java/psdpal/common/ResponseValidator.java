package psdpal.common;

import io.restassured.response.Response;

/**
 * ResponseValidator
 * -------------------------------------------------------------------------
 * Provides reusable validation methods for:
 *  - Status codes
 *  - JSON content type
 *  - JSON field existence
 *  - Field equality checks
 *  - List size validation
 *  - Contains / not-contains assertions
 *
 * Clean, readable, reusable validation layer for API tests.
 */
public class ResponseValidator {

    /* ------------------------------------------------------------
       CENTRALIZED ASSERTION FAILURE HANDLER
       ------------------------------------------------------------ */
    private static void fail(String message) {
        throw new AssertionError("❌ Validation Failed: " + message);
    }


    /* ------------------------------------------------------------
       STATUS CODE VALIDATIONS
       ------------------------------------------------------------ */
    public static void statusIs(Response r, int expectedStatus) {
        int actual = r.getStatusCode();
        if (actual != expectedStatus) {
            fail("Expected status " + expectedStatus + " but got " + actual);
        }
    }

    public static void statusIsSuccess(Response r) {
        int code = r.getStatusCode();
        if (code < 200 || code >= 300) {
            fail("Expected SUCCESS (2xx) but got: " + code);
        }
    }


    /* ------------------------------------------------------------
       CONTENT TYPE VALIDATIONS
       ------------------------------------------------------------ */
    public static void isJson(Response r) {
        if (!r.getContentType().contains("application/json")) {
            fail("Expected JSON but content type is: " + r.getContentType());
        }
    }


    /* ------------------------------------------------------------
       JSON FIELD VALIDATION
       ------------------------------------------------------------ */
    public static void fieldExists(Response r, String jsonPath) {
        Object value = r.jsonPath().get(jsonPath);
        if (value == null) {
            fail("Field missing: " + jsonPath);
        }
    }

    public static void fieldIs(Response r, String jsonPath, Object expectedValue) {
        Object actual = r.jsonPath().get(jsonPath);

        if (actual == null) {
            fail("Expected field '" + jsonPath + "' but it was missing!");
        }

        if (!actual.toString().equals(expectedValue.toString())) {
            fail("Field mismatch at '" + jsonPath +
                    "'. Expected: " + expectedValue +
                    ", but got: " + actual);
        }
    }


    /* ------------------------------------------------------------
       LIST VALIDATIONS
       ------------------------------------------------------------ */
    public static void listSizeIs(Response r, String jsonPath, int expectedSize) {
        int size = r.jsonPath().getList(jsonPath).size();
        if (size != expectedSize) {
            fail("Expected list size " + expectedSize +
                    " but got: " + size + " at path: " + jsonPath);
        }
    }

    public static void listContains(Response r, String jsonPath, Object expectedValue) {
        var list = r.jsonPath().getList(jsonPath);

        if (!list.contains(expectedValue)) {
            fail("List at '" + jsonPath + "' does not contain: " + expectedValue);
        }
    }

    public static void listNotContains(Response r, String jsonPath, Object value) {
        var list = r.jsonPath().getList(jsonPath);

        if (list.contains(value)) {
            fail("List at '" + jsonPath + "' should NOT contain: " + value);
        }
    }


    /* ------------------------------------------------------------
       NUMBER VALIDATIONS
       ------------------------------------------------------------ */
    public static void numberGreaterThan(Response r, String jsonPath, int minValue) {
        int actual = r.jsonPath().getInt(jsonPath);

        if (actual <= minValue) {
            fail("Expected number > " + minValue +
                    " at '" + jsonPath + "', but got: " + actual);
        }
    }

    public static void numberLessThan(Response r, String jsonPath, int maxValue) {
        int actual = r.jsonPath().getInt(jsonPath);

        if (actual >= maxValue) {
            fail("Expected number < " + maxValue +
                    " at '" + jsonPath + "', but got: " + actual);
        }
    }
}


package psdpal.common;

/**
 * Endpoints
 * -------------------------------------------------------------------------
 * PURPOSE:
 *   Centralized place to store all API endpoint paths for all services.
 *
 * BENEFITS:
 *   ✔ No hardcoding of URLs inside test scripts
 *   ✔ Clean separation of different API modules
 *   ✔ Easy to update endpoints in future
 *   ✔ Helps beginners understand where all endpoints reside
 *
 * DESIGN:
 *   - final class → cannot be extended
 *   - private constructor → cannot be instantiated
 *   - Nested static classes → groups endpoints per service
 */
public final class Endpoints {

    // --------------------------------------
    // REQRES API ENDPOINTS
    // --------------------------------------
    public static final class ReqRes {

        // GET Requests
        public static final String LIST_USERS = "/api/users?page=2";
        public static final String SINGLE_USER = "/api/users/{id}";

        // POST Requests
        public static final String CREATE_USER = "/api/users";

        // PUT/PATCH Requests
        public static final String UPDATE_USER = "/api/users/{id}";

        // DELETE Requests
        public static final String DELETE_USER = "/api/users/{id}";
    }


    // --------------------------------------
    // RESTFUL BOOKER ENDPOINTS
    // --------------------------------------
    public static final class RestfulBooker {

        // AUTH
        public static final String CREATE_TOKEN = "/auth";

        // BOOKINGS
        public static final String CREATE_BOOKING = "/booking";
        public static final String GET_BOOKING = "/booking/{id}";
        public static final String UPDATE_BOOKING = "/booking/{id}";
        public static final String DELETE_BOOKING = "/booking/{id}";
    }


    // --------------------------------------
    // FUTURE ENDPOINT GROUP (for IGF, Payments, Razorpay, etc.)
    // Just uncomment or add when needed
    // --------------------------------------
    /*
    public static final class IGF {
        public static final String CREATE_PAYMENT = "/igf/payment";
        public static final String VERIFY_PAYMENT = "/igf/payment/verify";
        public static final String GET_DONOR = "/igf/donor/{id}";
    }
    */


    // Private constructor → prevents object creation
    private Endpoints() {}
}

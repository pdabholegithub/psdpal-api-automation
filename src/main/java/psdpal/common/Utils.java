package psdpal.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Utils
 * -------------------------------------------------------------------------
 * Framework-wide reusable utilities for:
 *  - JSON conversion
 *  - Random data generation
 *  - File reading
 *  - Sleep/wait helpers
 *  - String formatting helpers
 *
 * These utilities avoid duplication across API tests.
 */
public class Utils {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Random random = new Random();


    /* ------------------------------------------------------------
       JSON HELPERS
       ------------------------------------------------------------ */

    /** Convert Java object → JSON string */
    public static String toJson(Object obj) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to convert object to JSON", e);
        }
    }

    /** Convert JSON string → Java Map/POJO */
    public static <T> T fromJson(String json, Class<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to parse JSON string", e);
        }
    }


    /* ------------------------------------------------------------
       FILE READ HELPERS
       ------------------------------------------------------------ */

    public static String readFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            throw new RuntimeException("❌ Unable to read file: " + filePath, e);
        }
    }


    /* ------------------------------------------------------------
       RANDOM DATA HELPERS
       ------------------------------------------------------------ */

    public static int randomNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public static String randomEmail() {
        return "user" + randomNumber(1000, 9999) + "@test.com";
    }

    public static String randomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }

        return sb.toString();
    }


    /* ------------------------------------------------------------
       WAIT / SLEEP HELPERS
       ------------------------------------------------------------ */

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }


    /* ------------------------------------------------------------
       ENVIRONMENT HELPERS
       ------------------------------------------------------------ */

    /** Returns true if running in Jenkins CI */
    public static boolean isRunningInCI() {
        return System.getenv("JENKINS_HOME") != null;
    }

    /** Simple environment printing */
    public static String currentEnv() {
        return ConfigReader.getOrDefault("env", "dev");
    }


    /* ------------------------------------------------------------
       STRING HELPERS
       ------------------------------------------------------------ */

    public static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }
}

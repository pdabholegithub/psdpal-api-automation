package psdpal.common;

import java.io.InputStream;
import java.util.Properties;

/**
 * ConfigReader
 * -------------------------------------------------------------------------
 * Loads environment-specific configuration files. Supports: -
 * application-dev.properties - application-qa.properties -
 * application-uat.properties - application-prod.properties
 *
 * Default fallback → application.properties
 */
public class ConfigReader {

	private static final Properties props = new Properties();
	private static final String ENV;

	static {
		// Read -Denv parameter OR fallback to "dev"
		ENV = System.getProperty("env", "dev").trim().toLowerCase();

		String fileName = "application-" + ENV + ".properties";

		try (InputStream envFile = ConfigReader.class.getClassLoader().getResourceAsStream(fileName)) {

			if (envFile != null) {
				props.load(envFile);
				System.out.println("Loaded environment file: " + fileName);

			} else {
				System.out.println("Environment file NOT found: " + fileName);
				System.out.println("Loading default application.properties");

				try (InputStream defaultFile = ConfigReader.class.getClassLoader()
						.getResourceAsStream("application.properties")) {

					if (defaultFile != null) {
						props.load(defaultFile);
					} else {
						throw new RuntimeException("Default application.properties missing!");
					}
				}
			}
			System.out.println("Active ENV = " + ENV);
		} catch (Exception ex) {
			throw new RuntimeException("Failed to load configuration!", ex);
		}
	}

	// -------------------------------------------------------------------------
	// BASIC GETTERS
	// -------------------------------------------------------------------------

	/** Regular getter */
	public static String get(String key) {
		String value = props.getProperty(key);
		return value != null ? value.trim() : null;
	}

	/** Safe getter with default value (used by Banner, TokenManager, etc.) */
	public static String getOrDefault(String key, String defaultValue) {
		String value = get(key);
		return (value == null || value.isEmpty()) ? defaultValue : value;
	}

	// -------------------------------------------------------------------------
	// TYPED GETTERS (safe & convenient)
	// -------------------------------------------------------------------------

	public static int getInt(String key) {
		return Integer.parseInt(get(key));
	}

	public static int getIntOrDefault(String key, int defaultValue) {
		try {
			return Integer.parseInt(get(key));
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static boolean getBoolean(String key) {
		return Boolean.parseBoolean(get(key));
	}

	public static boolean getBooleanOrDefault(String key, boolean defaultValue) {
		try {
			return Boolean.parseBoolean(get(key));
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static double getDoubleOrDefault(String key, double defaultValue) {
		try {
			return Double.parseDouble(get(key));
		} catch (Exception e) {
			return defaultValue;
		}
	}

	// -------------------------------------------------------------------------
	// BASE URL GETTERS
	// -------------------------------------------------------------------------

	/**
	 * Get base URL for specific microservice: qa.reqres.baseUrl qa.restful.baseUrl
	 * qa.igf.baseUrl
	 */
	public static String getBaseUrlFor(String service) {

		String key = ENV + "." + service + ".baseUrl";
		String value = get(key);

		if (value == null || value.isEmpty()) {
			throw new IllegalArgumentException("Missing base URL for key: " + key);
		}

		return value;
	}

	/** Get single-site baseUrl key: baseUrl=xxx */
	public static String getBaseUrl() {
		String url = get("baseUrl");

		if (url == null || url.isEmpty()) {
			throw new RuntimeException("baseUrl not found in properties file!");
		}

		return url;
	}
}

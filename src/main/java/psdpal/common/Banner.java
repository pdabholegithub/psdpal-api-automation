package psdpal.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Banner --------------------------------------------------------------- Prints
 * an ANSI-colored banner for the API Automation Framework. Useful for: - Local
 * debugging - CI pipelines - Environment identification
 */
public class Banner {

	// Choose column width for formatting (banner box width)
	private static final int WIDTH = 66;

	/**
	 * Prints the startup banner. Called from BaseAPI or @BeforeSuite.
	 */
	public static void print() {

		// Fetch environment safely
		String env = ConfigReader.getOrDefault("env", "qa").toUpperCase();

		// Current timestamp
		String startTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss"));

		// Select banner color based on env
		String color = getColorForEnv(env);
		
		// Optional: allow disabling banner in Jenkins for cleaner logs
		if (ConfigReader.getOrDefault("banner.enabled", "true").equalsIgnoreCase("false")) {
			return;
		}
		System.out.println();
		printLine(color);
		printCentered(color, "PSDPAL API AUTOMATION FRAMEWORK");
		printEmpty(color);
		printKeyValue(color, "Owner", FrameworkInfo.OWNER);
		printKeyValue(color, "Version", FrameworkInfo.VERSION);
		printKeyValue(color, "Environment", env);
		printKeyValue(color, "Description", FrameworkInfo.DESCRIPTION);
		printEmpty(color);
		printKeyValue(color, "Execution Start Time", startTime);
		printBottom(color);
		System.out.println();
		printBottom(color);
		System.out.println();
	}

	/*
	 * ---------------------------------------------------------- 
	 * Helper Methods
	 * ----------------------------------------------------------
	 */

	private static void printLine(String color) {
		System.out.println(color + "╔" + "═".repeat(WIDTH) + "╗" + "\u001B[0m");
	}

	private static void printBottom(String color) {
		System.out.println(color + "╚" + "═".repeat(WIDTH) + "╝" + "\u001B[0m");
	}

	private static void printEmpty(String color) {
		System.out.println(color + "║" + " ".repeat(WIDTH) + "║" + "\u001B[0m");
	}

	private static void printCentered(String color, String text) {
		int padding = (WIDTH - text.length()) / 2;
		String line = " ".repeat(Math.max(padding, 0)) + text;
		line += " ".repeat(Math.max(WIDTH - line.length(), 0));

		System.out.println(color + "║" + line + "║" + "\u001B[0m");
	}

	private static void printKeyValue(String color, String key, String value) {
		String text = String.format("   %-12s: %s", key, value);
		int padding = WIDTH - text.length();

		if (padding < 0)
			padding = 1;

		System.out.println(color + "║" + text + " ".repeat(padding) + "║" + "\u001B[0m");
	}

	/** Assign color by environment */
	private static String getColorForEnv(String env) {
		return switch (env) {
		case "DEV" -> "\u001B[36m"; // Cyan
		case "QA" -> "\u001B[34m"; // Blue
		case "UAT", "STAGE" -> "\u001B[33m"; // Yellow
		case "PROD" -> "\u001B[31m"; // Red
		default -> "\u001B[37m"; // White
		};
	}
}

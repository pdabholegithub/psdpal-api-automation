package psdpal.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * FrameworkInfo
 * -------------------------------------------------------------------------
 * Loads framework metadata such as:
 *  - Owner
 *  - Version
 *  - Description
 *
 * These values are read from:
 *  - framework-owner.txt
 *  - framework-version.txt
 *  - framework-description.txt
 *
 * Used primarily by the Banner class.
 */
public class FrameworkInfo {

    public static final String OWNER = load("framework-owner.txt");
    public static final String VERSION = load("framework-version.txt");
    public static final String DESCRIPTION = load("framework-description.txt");


    /**
     * Loads a text file from src/main/resources.
     */
    private static String load(String fileName) {

        try (InputStream in = FrameworkInfo.class.getClassLoader().getResourceAsStream(fileName)) {

            if (in == null) {
                return "N/A (missing: " + fileName + ")";
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();

            String line;

            // Append each line to string
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(" ");
            }

            return sb.toString().trim(); // remove trailing space

        } catch (Exception ex) {
            return "N/A (error loading: " + fileName + ")";
        }
    }
}

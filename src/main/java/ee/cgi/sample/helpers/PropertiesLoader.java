package ee.cgi.sample.helpers;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;

    /**
     * This class defines different type of properties and loads the content of .properties files
     *
     */

public class PropertiesLoader {
    private static Properties mainProperties;
    private static Properties environmentProperties;

    /**
     * Loads main settings file and returns properties from the file
     *
     * @return main settings properties
     */

    public static Properties loadGeneralSettings() {
        if (mainProperties != null) {
            return mainProperties;
        }
        mainProperties = PropertiesLoader.readPropertiesFromFile(Constants.MAIN_SETTINGS_FILE);
        return mainProperties;
    }

        /**
         * Loads main settings file and returns properties from the file
         *
         * @return main settings properties
         */

        public static Properties loadEnvironmentSettings(){
            if (environmentProperties != null) {
                return environmentProperties;
            }
            environmentProperties = PropertiesLoader.readPropertiesFromFile(Constants.ENVIRONMENT_SETTINGS_FILE);
            return environmentProperties;
        }

    /**
     * Loads properties from a file with the given filename.
     *
     * @param fileName name of the properties files
     *
     * @return properties read from the file
     */

    private static Properties readPropertiesFromFile(String fileName) {
        Properties p = null;
        FileInputStream fis = null;
            p = new Properties();
        try {
            fis  = new FileInputStream(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            p.load(new InputStreamReader(fis, Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return p;
    }
}



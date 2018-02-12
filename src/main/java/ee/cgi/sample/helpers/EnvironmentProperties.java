package ee.cgi.sample.helpers;

import java.util.Properties;

    /**
     * This class loads the content of environment.properties and provides data ifneedbe
     *
     */

public final class EnvironmentProperties {


    public final String url;
    public final String logsFolderName;

    private final static EnvironmentProperties _instance = new EnvironmentProperties();

    private EnvironmentProperties() {

        Properties environments = PropertiesLoader.loadEnvironmentSettings();

        url = environments.getProperty("mail.url");
        logsFolderName = environments.getProperty("logs.folder.name");
    }

    public static EnvironmentProperties get() {
        return _instance;
    }
}

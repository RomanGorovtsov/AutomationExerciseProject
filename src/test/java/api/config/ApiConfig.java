package api.config;

import java.io.InputStream;
import java.util.Properties;

public class ApiConfig {

    private static final String CONFIG_FILE = "config.api.properties";
    private static final String ENVIRONMENT_KEY = "environment";

    private final Properties properties;

    public ApiConfig() {
        this.properties = loadProperties();
    }

    private Properties loadProperties() {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input != null) {
                prop.load(input);
            } else {
                throw new RuntimeException("Configuration file not found: " + CONFIG_FILE);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
        return prop;
    }

    public String getUrlFromProperties() {
        String environment = getEnvironment();
        String urlKey = environment + ".url";

        String baseUrl = properties.getProperty(urlKey);
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            throw new RuntimeException("No URL configured for environment: " + environment);
        }
        return baseUrl;
    }

    public String getEnvironment() {
        return properties.getProperty(ENVIRONMENT_KEY);
    }
}
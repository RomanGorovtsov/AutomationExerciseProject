package api.config;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class ApiConfig {

    private static final String CONFIG_FILE = "config.api.properties";
    private static final String ENVIRONMENT_KEY = "environment";

    private final Properties properties;

    public ApiConfig() {
        log.debug("Initializing ApiConfig, loading properties from: {}", CONFIG_FILE);
        this.properties = loadProperties();
        log.info("ApiConfig initialized. Environment: {}, Base URL: {}",
                getEnvironment(), getUrlFromProperties());
    }

    private Properties loadProperties() {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input != null) {
                prop.load(input);
                log.debug("Successfully loaded properties file: {}", CONFIG_FILE);
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
        log.debug("Retrieved base URL for environment '{}': {}", environment, baseUrl);
        return baseUrl;
    }

    public String getEnvironment() {
        String env = properties.getProperty(ENVIRONMENT_KEY);
        if (env == null) {
            log.warn("No environment specified in config, defaulting to 'prod'");
            return "prod";
        }
        log.debug("Current environment: {}", env);
        return env;
    }
}
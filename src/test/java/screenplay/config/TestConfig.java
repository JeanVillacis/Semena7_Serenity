package screenplay.config;

public class TestConfig {

    public static String baseUrl() {
        return getWithEnv("webdriver.base.url", "WEBDRIVER_BASE_URL", "http://localhost:4000");
    }

    public static String loginUrl() {
        return baseUrl() + "/login";
    }

    public static String nuevoReclamoUrl() {
        return baseUrl() + "/nuevo-reclamo";
    }

    public static String estadoReclamoUrl() {
        return baseUrl() + "/estado-reclamo";
    }

    public static String gestorUsername() {
        return getWithEnv("gestor.username", "GESTOR_USERNAME", "gestor01");
    }

    public static String gestorPassword() {
        return getWithEnv("gestor.password", "GESTOR_PASSWORD", "gestor123");
    }

    private static String getWithEnv(String property, String envVar, String defaultValue) {
        String fromEnv = System.getenv(envVar);
        if (fromEnv != null && !fromEnv.isBlank()) {
            return fromEnv;
        }
        return System.getProperty(property, defaultValue);
    }
}

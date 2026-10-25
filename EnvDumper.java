import java.util.List;

public class EnvDumper {
    public static void main(String[] args) {
        List<String> targetKeys = List.of(
            "DB_PASSWORD",
            "DB_URL",
            "DB_USERNAME",
            "JAVA_TOOL_OPTIONS",
            "JWT_SECRET",
            "LOGGING_LEVEL_COM_ZAXWER_HIKARI",
            "LOGGING_LEVEL_ORG_FLYWAYDB",
            "ML_SERVICE_TOKEN",
            "ML_SERVICE_URL"
        );

        System.out.println("\n# ==================== BEGIN DUMPED .ENV ====================");
        for (String key : targetKeys) {
            String val = System.getenv(key);
            
            // Check alternate spelling if the Hikari key was defined without double-W
            if (val == null && key.equals("LOGGING_LEVEL_COM_ZAXWER_HIKARI")) {
                val = System.getenv("LOGGING_LEVEL_COM_ZAXER_HIKARI");
            }

            if (val != null) {
                // Wrap in quotes if it contains spaces, special characters, or newlines
                if (val.contains(" ") || val.contains("\n") || val.contains("\"")) {
                    val = "\"" + val.replace("\"", "\\\"") + "\"";
                }
                System.out.println(key + "=" + val);
            } else {
                System.out.println("# " + key + "=NOT_SET");
            }
        }
        System.out.println("# ===================== END DUMPED .ENV =====================\n");

        // Keep process alive briefly so logs flush completely before container exits
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ignored) {}
    }
}

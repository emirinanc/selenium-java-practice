package config;

public enum Properties {
    // URLs
    URL_HOME("https://useinsider.com/"),
    URL_CAREERS("https://useinsider.com/careers/"),
    URL_QA_JOBS("https://useinsider.com/careers/quality-assurance/");

    private final String stringValue;

    // Constructor for string values (URLs)
    Properties(String value) {
        this.stringValue = value;
    }

    // Method to get string value (for URLs)
    public String getString() {
        if (stringValue.isEmpty()) {
            throw new IllegalStateException(this.name() + " is not a string configuration");
        }
        return stringValue;
    }

}
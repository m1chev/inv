package util;

public class Constants {
    public static final String SITE_URL;
    public static final String USERNAME;
    public static final String PASSWORD;
    public static final String COMPANY;
    public static final String BLANK = "";
    public static final String BROWSER;
    public static final String SCREENSHOT_LOCATION;

    static {
        PropertyReader propertyReader = new PropertyReader();
        USERNAME = propertyReader.getProperty("username");
        PASSWORD = propertyReader.getProperty("password");
        COMPANY = propertyReader.getProperty("company");
        SCREENSHOT_LOCATION = propertyReader.getProperty("screenshot.location");
        SITE_URL = getURL();
        BROWSER = getBrowser();
        //add more properties
    }

    private static String getBrowser() {
        String browser = System.getProperty("browser");
        return browser == null ? "chrome" : browser;
    }

    private static String getURL() {
        String url = System.getProperty("url");
        return url == null ? "https://trial.i3.identysoft.com" : url;
    }
}

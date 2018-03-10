import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import util.Constants;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class AbstractTest {
    public static WebDriver driver = null;
    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);
    public static final String chromeDriverLocation = "C:\\webdrivers\\chromedriver.exe";
    public static final String firefoxDriverLocation = "C:\\webdrivers\\geckodriver.exe";
    public static final String ieDriverLocation = "C:\\webdrivers\\IEDriverServer.exe";
    public static final String chromeProperty = "webdriver.chrome.driver";
    public static final String firefoxProperty = "webdriver.gecko.driver";
    public static final String ieProperty = "webdriver.ie.driver";


    protected void startBrowser() {
        if (Constants.BROWSER.equalsIgnoreCase("firefox")) {
            System.setProperty(firefoxProperty, firefoxDriverLocation);
            driver = new FirefoxDriver();
            configureBrowser();
        }
        if (Constants.BROWSER.equalsIgnoreCase("chrome")) {
            System.setProperty(chromeProperty, chromeDriverLocation);
            driver = new ChromeDriver();
            configureBrowser();
        }
        if (Constants.BROWSER.equalsIgnoreCase("ie")) {
            System.setProperty(ieProperty, ieDriverLocation);
            driver = new InternetExplorerDriver();
            configureBrowser();
        }

    }

    private void configureBrowser() {
        LOGGER.info("Starting browser:" + Constants.BROWSER);
        driver.manage().deleteAllCookies(); //delete cookies
        driver.manage().window().maximize(); //To maximize browser
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);   //Implicit wait
    }


    protected void quitBrowser() {
        LOGGER.info("Killing driver...");
        driver.quit();
    }


    protected void screenShot(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                File src = screenshot.getScreenshotAs(OutputType.FILE); //capture screenshot
                String fileName = result.getName() + ".png";
                FileUtils.copyFile(src, new File(Constants.SCREENSHOT_LOCATION + fileName)); //copy file to location
                LOGGER.info("Successfully captured a screenshot");
                LOGGER.info("Stored image:" + fileName + " at:" + Constants.SCREENSHOT_LOCATION);
            } catch (Exception e) {
                LOGGER.info("Exception while taking screenshot " + e.getMessage());
            }

        }
    }

    @AfterMethod
    public void afterTest(ITestResult result) {
        screenShot(result);
        quitBrowser();
    }
}
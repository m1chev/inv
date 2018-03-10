import org.fest.assertions.Assertions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.login.LoginPage;
import pages.settings.PrivacySettingsPage;

public class PrivacySettingsTest extends AbstractTest{
    private LoginPage loginPage;
    private PrivacySettingsPage privacySettingsPage;

    @BeforeMethod
    public void beforeTest() {
        startBrowser();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        privacySettingsPage = PageFactory.initElements(driver, PrivacySettingsPage.class);
    }

    @Test
    public void canGoToPrivacySettingsPage() {
        loginPage.login();
        privacySettingsPage.gotoPage();
        Assertions.assertThat(privacySettingsPage.getAllResetPasswordOptions()).as("Password Options").contains("Never", "7 days", "30 days", "90 days", "120 days");
        Assertions.assertThat(privacySettingsPage.getAllLogDeleteIntervalOptions()).as("Log Delete Options").contains("Never", "3 months", "6 months", "1 year", "2 years");
        Assertions.assertThat(privacySettingsPage.getAllUserDeleteIntervalOptions()).as("User Delete Options").contains("Never", "3 months", "6 months", "1 year", "2 years");
        privacySettingsPage.getAllResetPasswordOptions();
    }
}

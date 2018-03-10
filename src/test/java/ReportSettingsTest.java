import enums.ErrorMessages;
import org.fest.assertions.Assertions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.login.LoginPage;
import pages.settings.ReportSettingsPage;

public class ReportSettingsTest extends AbstractTest {
    private LoginPage loginPage;
    private ReportSettingsPage reportSettingsPage;

    @BeforeMethod
    public void beforeTest() {
        startBrowser();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        reportSettingsPage = PageFactory.initElements(driver, ReportSettingsPage.class);
    }

    @Test
    public void canGoToReportsPage() {
        loginPage.login();
        reportSettingsPage.gotoPage();
        Assertions.assertThat(reportSettingsPage.getDefaultWarningMessage()).as("Warn Message").contains(ErrorMessages.REPORT_SETTINGS_WARN.getMessage());
    }
}

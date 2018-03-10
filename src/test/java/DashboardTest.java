import org.fest.assertions.Assertions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.login.DashboardPage;
import pages.login.LoginPage;

public class DashboardTest extends AbstractTest {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @BeforeMethod
    public void beforeTest() {
        startBrowser();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
    }

    @Test
    public void canGoToDashboardPage() {
        loginPage.login();
        dashboardPage.gotoPage();
        Assertions.assertThat(dashboardPage.isTableDisplayed()).as("Table Displayed").isTrue();
        Assertions.assertThat(dashboardPage.isLogCanvasVisible()).as("Log Canvas Displayed").isTrue();
    }
}

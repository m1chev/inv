import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.access.DoorManagementPage;
import pages.login.LoginPage;

public class DoorManagementTest extends AbstractTest {
    private LoginPage loginPage;
    private DoorManagementPage doorManagementPage;

    @BeforeMethod
    public void beforeTest() {
        startBrowser();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        doorManagementPage = PageFactory.initElements(driver, DoorManagementPage.class);
    }

    @Test
    public void canGoToDoorManagementPage() {
        loginPage.login();
        doorManagementPage.gotoPage();
    }
}

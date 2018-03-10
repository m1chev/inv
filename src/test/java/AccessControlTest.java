import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.access.AccessControlPage;
import pages.login.LoginPage;
import util.Constants;

public class AccessControlTest extends AbstractTest {
    private LoginPage loginPage;
    private AccessControlPage accessControlPage;


    @BeforeMethod
    public void beforeTest() {
        startBrowser();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        accessControlPage = PageFactory.initElements(driver, AccessControlPage.class);
    }


    @Test
    public void canGoToAccessControlPage() {
        loginPage.login(Constants.COMPANY, Constants.USERNAME, Constants.PASSWORD);
        accessControlPage.gotoPage();
    }
}

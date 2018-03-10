import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.login.LoginPage;
import util.Constants;

public class LoginTest extends AbstractTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void beforeTest() {
        startBrowser();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
    }

    @Test
    public void canLoginSuccessfully() {
        loginPage.gotoPage();
        loginPage.enterCompany(Constants.COMPANY);
        loginPage.enterUsername(Constants.USERNAME);
        loginPage.enterPassword(Constants.PASSWORD);
        loginPage.pressLoginButton();
    }

    @Test
    public void cantLoginWithInvalidPassword() {
        loginPage.gotoPage();
        loginPage.enterCompany(Constants.COMPANY);
        loginPage.enterUsername(Constants.USERNAME);
        loginPage.enterPassword("Invalid Password");
        loginPage.pressLoginButton();
        loginPage.verifyBadCredentialsMessage();
    }

    @Test
    public void cantLoginWithInvalidCompany() {
        loginPage.gotoPage();
        loginPage.enterCompany("Bad Company");
        loginPage.enterUsername(Constants.USERNAME);
        loginPage.enterPassword(Constants.PASSWORD);
        loginPage.pressLoginButton();
        loginPage.verifyBadCompanyMessage();
    }

    @Test
    public void cantLoginWithBlankCredentials() {
        loginPage.gotoPage();
        loginPage.enterCompany(Constants.BLANK);
        loginPage.enterUsername(Constants.BLANK);
        loginPage.enterPassword(Constants.BLANK);
        loginPage.pressLoginButton();
        loginPage.verifyBadCompanyMessage();
    }

}

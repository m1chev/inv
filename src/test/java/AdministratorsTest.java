import enums.ErrorMessages;
import org.fest.assertions.Assertions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.administration.AdminLevelPage;
import pages.administration.AdministratorsPage;
import pages.login.DashboardPage;
import pages.login.LoginPage;
import util.Constants;

public class AdministratorsTest extends AbstractTest {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private AdministratorsPage administratorsPage;
    private AdminLevelPage adminLevelPage;


    @BeforeMethod
    public void beforeTest() {
        startBrowser();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
        administratorsPage = PageFactory.initElements(driver, AdministratorsPage.class);
        adminLevelPage = PageFactory.initElements(driver, AdminLevelPage.class);
    }

    @Test
    public void canAddNewAdministratorAndDeleteIt() {
        String username = "Automated";
        loginPage.login();
        administratorsPage.createAdministrator("Automated", "alex@pragmatic.bg", "Al3x_01", "Al3x_01");
        administratorsPage.verifyAdded();
        administratorsPage.deleteByCriteria(username);
    }

    @Test
    public void canViewAllDefaultLanguages() {
        loginPage.login();
        administratorsPage.gotoPage();
        administratorsPage.gotoAddAdministrator();
        Assertions.assertThat(administratorsPage.getAllLanguages()).as("Languages").contains("Nederlands", "German", "English", "Norway");
    }

    @Test
    public void canAddNewAdministratorLoginWithItAndDeleteIt() {
        String username = "Automated Login";
        String password = "Al3x_01";
        loginPage.login();
        administratorsPage.deleteByCriteria(username);
        administratorsPage.createAdministrator(username, "alex@pragmatic.bg", password, password);
        administratorsPage.verifyAdded();
        administratorsPage.gotoPage();
        dashboardPage.logout();
        loginPage.login(Constants.COMPANY, username, password);
        dashboardPage.logout();
        loginPage.login();
        administratorsPage.deleteByCriteria(username);
    }

    @Test
    public void cantAddUserWithWeakPassword() {
        String username = "Pass";
        String password = "Pass";
        loginPage.login();
        administratorsPage.deleteByCriteria(username);
        administratorsPage.createAdministrator(username, "alex@pragmatic.bg", password, password);
        Assertions.assertThat(administratorsPage.getPasswordError()).as("Password Error").contains(ErrorMessages.SHORT_PASSWORD.getMessage());
        Assertions.assertThat(administratorsPage.getPasswordError()).as("Password Error").contains(ErrorMessages.WEAK_PASSWORD.getMessage());
        Assertions.assertThat(administratorsPage.getPasswordError()).as("Password Error").contains(ErrorMessages.USER_CONTAINED_PASSWORD.getMessage());
        password = "12345";
        administratorsPage.createAdministrator(username, "alex@pragmatic.bg", password, password);
        Assertions.assertThat(administratorsPage.getPasswordError()).as("Password Error").contains(ErrorMessages.SHORT_PASSWORD.getMessage());
        Assertions.assertThat(administratorsPage.getPasswordError()).as("Password Error").contains(ErrorMessages.WEAK_PASSWORD.getMessage());
        password = "123456";
        administratorsPage.createAdministrator(username, "alex@pragmatic.bg", password, password);
        Assertions.assertThat(administratorsPage.getPasswordError()).as("Password Error").contains(ErrorMessages.WEAK_PASSWORD.getMessage());
        administratorsPage.verifyNotAdded();
    }

    //TODO: Add tag I3-106
    public void canAddAdministratorToAdminLevelWithNoPermissions() {
        String level = "Manager - Auto";
        String username = "Test";
        String password = "User02";
        loginPage.login();
        //Create admin level with no permissions
        adminLevelPage.deleteByCriteria(level);
        adminLevelPage.add(level);
        adminLevelPage.verifyAdded();
        adminLevelPage.pressBackButton();
        //Create administrator and assign it to level
        administratorsPage.deleteByCriteria(username);
        administratorsPage.gotoAddAdministrator();
        administratorsPage.enterUsername(username);
        administratorsPage.enterEmail("alex@pragmatic.bg");
        administratorsPage.enterPassword(password);
        administratorsPage.enterConfirmPassword(password);
        //administratorsPage.selectLevel(level); //TODO: Uncomment when  I3-106 is fixed for real
        administratorsPage.pressSubmitButton();
        administratorsPage.verifyAdded();
        dashboardPage.logout();
        loginPage.login(Constants.COMPANY, username, password);
        administratorsPage.deleteByCriteria(username);
        adminLevelPage.deleteByCriteria(level);
    }

    @Test
    public void cantAddDuplicateAdmin() {
        String username = "Duplicate";
        String password = "Strong123";
        loginPage.login();
        administratorsPage.deleteByCriteria(username);
        administratorsPage.createAdministrator(username, "alex@pragmatic.bg", password, password);
        administratorsPage.verifyAdded();
        administratorsPage.createAdministrator(username, "alex@pragmatic.bg", password, password);
        administratorsPage.verifyNotAdded();
        Assertions.assertThat(administratorsPage.getBadFieldMessage()).as("Password Error").contains(ErrorMessages.DUPLICATE_USERNAME.getMessage());
        administratorsPage.deleteByCriteria(username);
    }


    @Test
    public void cantAdminWithPasswordMissMatch() {
        String username = "PasswordMissMatch";
        String password = "Strong123";
        String password2 = "Strong1234";
        loginPage.login();
        administratorsPage.deleteByCriteria(username);
        administratorsPage.createAdministrator(username, "alex@pragmatic.bg", password, password2);
        administratorsPage.verifyNotAdded();
        Assertions.assertThat(administratorsPage.getBadFieldMessage()).as("Password MissMatch").contains(ErrorMessages.PASSWORD_MISS_MATCH.getMessage());
    }
}

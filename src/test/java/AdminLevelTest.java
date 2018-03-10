import org.fest.assertions.Assertions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.administration.AdminLevelPage;
import pages.administration.AdministratorsPage;
import pages.login.LoginPage;

public class AdminLevelTest extends AbstractTest {
    private LoginPage loginPage;
    private AdminLevelPage adminLevelPage;
    private AdministratorsPage administratorsPage;
    private String name = null;


    @BeforeMethod
    public void beforeTest() {
        startBrowser();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        adminLevelPage = PageFactory.initElements(driver, AdminLevelPage.class);
        administratorsPage = PageFactory.initElements(driver, AdministratorsPage.class);
    }

    @Test
    public void canCreateAndDeleteAdminLevel() {
        name = "Automated Admin Level";
        loginPage.login();
        adminLevelPage.gotoPage();
        adminLevelPage.gotoAddAdminLevelPage();
        adminLevelPage.enterName(name);
        adminLevelPage.pressSubmitButton();
        adminLevelPage.verifyAdded();
        adminLevelPage.pressBackButton();
        adminLevelPage.deleteByCriteria(name);
    }

    @Test
    public void canViewNewlyAddedAdminLevelsInAdministrators() {
        name = "Custom Auto Admin Level";
        loginPage.login();
        adminLevelPage.deleteByCriteria(name);
        adminLevelPage.add(name);
        adminLevelPage.verifyAdded();
        adminLevelPage.pressBackButton();
        administratorsPage.gotoPage();
        administratorsPage.gotoAddAdministrator();
        //Check it is visible among levels
        Assertions.assertThat(administratorsPage.getAllLevels()).as("Admin Levels").contains(name);
        adminLevelPage.deleteByCriteria(name);
    }

    @Test
    public void cantAddDuplicateAdminLevel() {
        name = "Duplicate";
        loginPage.login();
        adminLevelPage.deleteByCriteria(name);
        adminLevelPage.add(name);
        adminLevelPage.verifyAdded();
        adminLevelPage.add(name);
        adminLevelPage.verifyNotAdded();
        adminLevelPage.verifyDuplicate();
        adminLevelPage.deleteByCriteria(name);
    }
}

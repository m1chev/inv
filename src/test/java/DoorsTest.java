import org.fest.assertions.Assertions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.login.LoginPage;
import pages.structures.DoorsPage;

public class DoorsTest extends AbstractTest {
    private LoginPage loginPage;
    private DoorsPage doorsPage;

    @BeforeMethod
    public void beforeTest() {
        startBrowser();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        doorsPage = PageFactory.initElements(driver, DoorsPage.class);
    }

    @Test
    public void canViewAllTimeSchedules() {
        loginPage.login();
        doorsPage.gotoPage();
        doorsPage.pressAddButton();
        Assertions.assertThat(doorsPage.getAllTimeScheduleOptions()).as("Time Schedules Available").contains("Access 24/7");
        doorsPage.pressBackButton();
    }

    @Test
    public void canAddDeleteDoor() {
        String name = "Front Door - Auto";
        loginPage.login();
        doorsPage.gotoPage();
        doorsPage.deleteByCriteria(name);
        doorsPage.pressAddButton();
        doorsPage.enterName(name);
        doorsPage.selectTimeSchedule("Access 24/7");
        doorsPage.pressSubmitButton();
        doorsPage.verifyAdded();
        doorsPage.gotoPage();
        doorsPage.search(name);
        doorsPage.deleteByCriteria(name);
        doorsPage.verifyDeleted();
    }

    @Test
    public void cantAddDuplicateDoor() {
        String name = "Duplicate";
        loginPage.login();
        doorsPage.deleteByCriteria(name);
        doorsPage.createDoor(name);
        doorsPage.gotoPage();
        doorsPage.pressAddButton();
        doorsPage.enterName(name);
        doorsPage.pressSubmitButton();
        doorsPage.verifyNotAdded();
        doorsPage.verifyDuplicate();
        doorsPage.deleteByCriteria(name);
        doorsPage.verifyDeleted();
    }

    @Test
    public void canEditDoor() {
        String name = "Front Door - Auto";
        loginPage.login();
        doorsPage.deleteByCriteria(name);
        doorsPage.pressAddButton();
        doorsPage.enterName(name);
        doorsPage.pressSubmitButton();
        doorsPage.verifyAdded();
        doorsPage.gotoPage();
        doorsPage.search(name);
        doorsPage.editByCriteria(name);
        doorsPage.selectTimeSchedule("Access 24/7");
        doorsPage.pressSubmitButton();
        doorsPage.verifyUpdated();
        doorsPage.deleteByCriteria(name);
        doorsPage.verifyDeleted();
    }

    @Test
    public void tableInfoTextIsCorrectWhenNoDoorsAreFound() {
        loginPage.login();
        doorsPage.gotoPage();
        doorsPage.search("Non-existing user");
        doorsPage.verifyTableInfoContains("Show records 0 up to 0 from a total of 0");
    }

}

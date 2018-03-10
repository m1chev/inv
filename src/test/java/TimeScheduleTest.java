import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.access.TimeSchedulesPage;
import pages.login.LoginPage;

public class TimeScheduleTest extends AbstractTest {
    private LoginPage loginPage;
    private TimeSchedulesPage timeSchedulesPage;

    @BeforeMethod
    public void beforeTest() {
        startBrowser();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        timeSchedulesPage = PageFactory.initElements(driver, TimeSchedulesPage.class);
    }

    @Test
    public void canGoToTimeSchedulesPage() {
        String name = "Auto Schedule";
        loginPage.login();
        timeSchedulesPage.gotoPage();
        //Check that record does not exists
        timeSchedulesPage.deleteByCriteria(name);
        timeSchedulesPage.createTimeSchedule(name);
        timeSchedulesPage.gotoPage();
        //Clean created schedule
        timeSchedulesPage.deleteByCriteria(name);
    }

    @Test
    public void cantAddDuplicateTimeSchedules() {
        String name = "Duplicate";
        loginPage.login();
        timeSchedulesPage.gotoPage();
        timeSchedulesPage.deleteByCriteria(name);
        timeSchedulesPage.createTimeSchedule(name);
        timeSchedulesPage.gotoAdd();
        timeSchedulesPage.enterName(name);
        timeSchedulesPage.pressSubmitButton();
        timeSchedulesPage.verifyNotAdded();
        timeSchedulesPage.verifyDuplicate();
        timeSchedulesPage.gotoPage();
        timeSchedulesPage.deleteByCriteria(name);
    }
}

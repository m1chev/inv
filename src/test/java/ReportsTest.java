import org.fest.assertions.Assertions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.login.LoginPage;
import pages.reports.ReportsPage;
import util.Wait;

/**
 * Created by Pragmatic on 2/2/2018.
 */
public class ReportsTest extends AbstractTest {
    private LoginPage loginPage;
    private ReportsPage reportsPage;
    private ReportsPage.AdministratorsLog administratorsLog;


    @BeforeMethod
    public void beforeTest() {
        startBrowser();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        reportsPage = PageFactory.initElements(driver, ReportsPage.class);
        administratorsLog = reportsPage.new AdministratorsLog();
    }

    @Test
    public void canGoToReportsPage() {
        loginPage.login();
        reportsPage.gotoPage();
        reportsPage.gotoTimeRegistrationPeriodTab();
        reportsPage.gotoTimeRegistrationGroupTab();
        reportsPage.gotoTimeRegistrationUserTab();
        // reportsPage.gotoAttendanceOverviewTab();
        reportsPage.gotoUserAndTemplatesTab();
        reportsPage.gotoAccessLogTab();
        reportsPage.clickReportsViewButton();
    }

    @Test
    public void canViewReportLogsForAdministrators() {
        loginPage.login();
        reportsPage.gotoPage();
        reportsPage.gotoAdministratorsLogTab();
        Assertions.assertThat(administratorsLog.getAllAdmins()).as("Admins").isNotEmpty();
        administratorsLog.adminLogPopulateFromDate("Feb", "2018", "1");
        administratorsLog.adminLogPopulateToDate("Feb", "2018", "20");
        administratorsLog.pressShowLogButton();
        Wait.wait(2);
        Assertions.assertThat(reportsPage.isLogTableDisplayed()).as("Logs Table").isTrue();

    }
}

import enums.ShowRecords;
import org.fest.assertions.Assertions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.access.AntiPassBackPage;
import pages.login.DashboardPage;
import pages.login.LoginPage;
import util.Constants;

public class AntiPassBackTest extends AbstractTest {
    private LoginPage loginPage;
    private AntiPassBackPage antiPassBackPage;
    private DashboardPage dashboardPage;

    @BeforeMethod
    public void beforeMethod() {
        startBrowser();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
        antiPassBackPage = PageFactory.initElements(driver, AntiPassBackPage.class);
    }

    @Test
    public void canGoToAntiPassBackPage() {
        loginPage.login();
        antiPassBackPage.gotoPage();
        antiPassBackPage.pressAddButton();
        Assertions.assertThat(antiPassBackPage.getAllTypeOptions()).as("Type Options").contains("TIMED APB");
    }

    @Test
    public void filtersClearedAfterLogout(){
        loginPage.login();
        antiPassBackPage.gotoPage();
        antiPassBackPage.selectShowRecords(ShowRecords.TEN);
        antiPassBackPage.search("Text to search");
        dashboardPage.logout();
        loginPage.login();
        antiPassBackPage.gotoPage();
        Assertions.assertThat(antiPassBackPage.getSearchFieldText()).as("Search Text").contains(Constants.BLANK);
        Assertions.assertThat(antiPassBackPage.getShowRecordsText()).as("Show Records Dropdown Default Value").contains("50");
    }
}

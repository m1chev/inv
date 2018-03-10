import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.login.LoginPage;
import pages.reports.OverviewPage;
import pages.structures.LocationsPage;

public class OverviewTest extends AbstractTest {
    private LoginPage loginPage;
    private OverviewPage overviewPage;
    private LocationsPage locationsPage;

    @BeforeMethod
    public void beforeTest() {
        startBrowser();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        overviewPage = PageFactory.initElements(driver, OverviewPage.class);
        locationsPage = PageFactory.initElements(driver, LocationsPage.class);
    }

    @Test
    public void canListOverviewForSpecificLocation() {
        String location = "Overview Location";
        loginPage.login();
        locationsPage.gotoPage();
        locationsPage.search(location);
        locationsPage.deleteByCriteria(location);
        //Create new location
        locationsPage.createNewLocation(location, "", "");
        //Make sure it is displayed
        overviewPage.gotoPage();
        overviewPage.selectLocation(location);
        overviewPage.clickViewButton();
        overviewPage.verifyOverviewVisible();
        //remove newly added location
        locationsPage.gotoPage();
        locationsPage.search(location);
        locationsPage.deleteByCriteria(location);
    }

}

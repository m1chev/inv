import org.fest.assertions.Assertions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.login.LoginPage;
import pages.structures.LocationsPage;

public class LocationsTest extends AbstractTest {
    private LoginPage loginPage;
    private LocationsPage locationsPage;

    @BeforeMethod
    public void beforeTest() {
        startBrowser();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        locationsPage = PageFactory.initElements(driver, LocationsPage.class);
    }

    //TODO: Add test tag when I3-104 is fixed
    public void cantAddLocationWithInvalidName() {
        loginPage.login();
        locationsPage.createLocation("<script></script>");
        locationsPage.verifyNotAdded();
        locationsPage.createLocation("#@*&*#$&*^");
        locationsPage.verifyNotAdded();
    }

    @Test
    public void canCreateAndDeleteLocation() {
        String location = "CRUD Auto Location";
        loginPage.login();
        locationsPage.gotoPage();
        locationsPage.gotoAddLocation();
        locationsPage.enterName(location);
        locationsPage.pressSubmitButton();
        locationsPage.gotoPage();
        locationsPage.deleteByCriteria(location);
        locationsPage.verifyDeleted();
    }

    @Test
    public void canSearchForLocations() {
        String groupName = "Search Group";
        loginPage.login();
        locationsPage.gotoPage();
        locationsPage.deleteByCriteria(groupName);
        locationsPage.gotoAddLocation();
        locationsPage.enterName(groupName);
        locationsPage.pressSubmitButton();
        locationsPage.gotoPage();
        locationsPage.search(groupName);
        Assertions.assertThat(locationsPage.getTableInfoText()).as("Table Info").contains("Show records 1 up to 1 from a total of");
        locationsPage.gotoPage();
        locationsPage.deleteByCriteria(groupName);
        locationsPage.verifyDeleted();
    }

}

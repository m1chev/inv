import org.fest.assertions.Assertions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.login.LoginPage;
import pages.structures.ZonesPage;

public class ZonesTest extends AbstractTest {
    private LoginPage loginPage;
    private ZonesPage zonesPage;

    @BeforeMethod
    public void beforeClass() {
        startBrowser();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        zonesPage = PageFactory.initElements(driver, ZonesPage.class);
    }

    @Test
    public void canCreateAndDeleteZone() {
        String zone = "Automated Zone";
        loginPage.login();
        zonesPage.gotoPage();
        zonesPage.gotoAddZones();
        zonesPage.enterName(zone);
        zonesPage.pressSubmitButton();
        zonesPage.gotoPage();
        zonesPage.deleteByCriteria(zone);
        zonesPage.verifyDeleted();
    }

    @Test
    public void canSearchZone() {
        String zone = "Search Zone";
        loginPage.login();
        zonesPage.gotoPage();
        zonesPage.gotoAddZones();
        zonesPage.enterName(zone);
        zonesPage.pressSubmitButton();
        zonesPage.gotoPage();
        zonesPage.search(zone);
        Assertions.assertThat(zonesPage.getTableInfoText()).as("Table Info").contains("Show records 1 up to 1 from a total of");
        zonesPage.deleteByCriteria(zone);
        zonesPage.verifyDeleted();
    }

    @Test
    public void tableInfoTextIsCorrectWhenNoZonesAreFound() {
        loginPage.login();
        zonesPage.gotoPage();
        zonesPage.search("Non-existing user");
        zonesPage.verifyTableInfoContains("Show records 0 up to 0 from a total of 0");
    }
}
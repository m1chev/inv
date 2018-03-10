import com.google.common.collect.Lists;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.access.AccessControlPage;
import pages.access.AntiPassBackPage;
import pages.access.DoorManagementPage;
import pages.access.TimeSchedulesPage;
import pages.administration.AdminLevelPage;
import pages.administration.AdministratorsPage;
import pages.login.DashboardPage;
import pages.login.LoginPage;
import pages.reports.OverviewPage;
import pages.reports.ReportsPage;
import pages.settings.DeviceSettingsPage;
import pages.settings.FunctionKeySettingsPage;
import pages.settings.PrivacySettingsPage;
import pages.settings.ReportSettingsPage;
import pages.structures.DoorsPage;
import pages.structures.LocationsPage;
import pages.structures.ScannersPage;
import pages.structures.ZonesPage;
import pages.users.GroupsPage;
import pages.users.UsersPage;
import util.IPage;

import java.util.ArrayList;
import java.util.List;

public class PageVisitorTest extends AbstractTest {
    private List<IPage> pages = new ArrayList<>();
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private TimeSchedulesPage timeSchedulesPage;
    private DoorManagementPage doorManagementPage;
    private AntiPassBackPage antiPassBackPage;
    private AccessControlPage accessControlPage;
    private AdministratorsPage administratorsPage;
    private AdminLevelPage adminLevelPage;
    private OverviewPage overviewPage;
    private ReportsPage reportsPage;
    private DeviceSettingsPage deviceSettingsPage;
    private FunctionKeySettingsPage functionKeySettingsPage;
    private ReportSettingsPage reportSettingsPage;
    private DoorsPage doorsPage;
    private LocationsPage locationsPage;
    private ScannersPage scannersPage;
    private ZonesPage zonesPage;
    private GroupsPage groupsPage;
    private UsersPage usersPage;
    private PrivacySettingsPage privacySettingsPage;


    @BeforeMethod
    public void beforeTest() {
        startBrowser();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
        doorManagementPage = PageFactory.initElements(driver, DoorManagementPage.class);
        timeSchedulesPage = PageFactory.initElements(driver, TimeSchedulesPage.class);
        antiPassBackPage = PageFactory.initElements(driver, AntiPassBackPage.class);
        accessControlPage = PageFactory.initElements(driver, AccessControlPage.class);
        administratorsPage = PageFactory.initElements(driver, AdministratorsPage.class);
        adminLevelPage = PageFactory.initElements(driver, AdminLevelPage.class);
        overviewPage = PageFactory.initElements(driver, OverviewPage.class);
        reportsPage = PageFactory.initElements(driver, ReportsPage.class);
        deviceSettingsPage = PageFactory.initElements(driver, DeviceSettingsPage.class);
        functionKeySettingsPage = PageFactory.initElements(driver, FunctionKeySettingsPage.class);
        reportSettingsPage = PageFactory.initElements(driver, ReportSettingsPage.class);
        doorsPage = PageFactory.initElements(driver, DoorsPage.class);
        locationsPage = PageFactory.initElements(driver, LocationsPage.class);
        scannersPage = PageFactory.initElements(driver, ScannersPage.class);
        zonesPage = PageFactory.initElements(driver, ZonesPage.class);
        usersPage = PageFactory.initElements(driver, UsersPage.class);
        groupsPage = PageFactory.initElements(driver, GroupsPage.class);
        privacySettingsPage = PageFactory.initElements(driver, PrivacySettingsPage.class);

    }

    @Test
    public void canLoadAllPages() {
        loginPage.login();
        pages = Lists.newArrayList(dashboardPage, privacySettingsPage, groupsPage, zonesPage, timeSchedulesPage, accessControlPage, usersPage, doorManagementPage, scannersPage, locationsPage, doorsPage, antiPassBackPage, functionKeySettingsPage, administratorsPage, adminLevelPage, overviewPage, reportsPage, reportSettingsPage, deviceSettingsPage);
        pages.forEach(IPage::gotoPage);
        dashboardPage.logout();
    }
}

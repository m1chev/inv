import enums.ErrorMessages;
import enums.ShowRecords;
import org.fest.assertions.Assertions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.login.DashboardPage;
import pages.login.LoginPage;
import pages.users.GroupsPage;
import util.Constants;

public class GroupsTest extends AbstractTest {
    private LoginPage loginPage;
    private GroupsPage groupsPage;
    private DashboardPage dashboardPage;
    private final String groupName = "Automated Group";
    private final String duplicateGroupName = "Duplicate Group";

    @BeforeMethod
    public void beforeTest() {
        startBrowser();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        groupsPage = PageFactory.initElements(driver, GroupsPage.class);
        dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
    }

    @Test
    public void cantAddGroupWithInvalidName() {
        loginPage.login();
        groupsPage.createGroup("<script>alert()</script>");
        groupsPage.verifyNotAdded();
        groupsPage.verifyContains(ErrorMessages.BAD_GROUP_NAME.getMessage(), groupsPage.getBadFieldMessage());
        groupsPage.createGroup(";Invalid Group");
        groupsPage.verifyNotAdded();
        groupsPage.verifyContains(ErrorMessages.BAD_GROUP_NAME.getMessage(), groupsPage.getBadFieldMessage());
        groupsPage.createGroup("#$!@$");
        groupsPage.verifyNotAdded();
        groupsPage.verifyContains(ErrorMessages.BAD_GROUP_NAME.getMessage(), groupsPage.getBadFieldMessage());
    }

    @Test
    public void cantAddGroupWithDuplicateName() {
        loginPage.login();
        groupsPage.deleteByCriteria(duplicateGroupName);
        groupsPage.gotoAddGroup();
        groupsPage.enterName(duplicateGroupName);
        groupsPage.pressSubmitButton();
        groupsPage.gotoAddGroup();
        groupsPage.enterName(duplicateGroupName);
        groupsPage.pressSubmitButton();
        groupsPage.verifyDuplicate();
        groupsPage.deleteByCriteria(duplicateGroupName);
    }

    @Test
    public void filtersClearedAfterLogout(){
        loginPage.login();
        groupsPage.gotoPage();
        groupsPage.selectShowRecords(ShowRecords.TEN);
        groupsPage.search("Text to search");
        dashboardPage.logout();
        loginPage.login();
        groupsPage.gotoPage();
        Assertions.assertThat(groupsPage.getSearchFieldText()).as("Search Text").contains(Constants.BLANK);
        Assertions.assertThat(groupsPage.getShowRecordsText()).as("Show Records Dropdown Default Value").contains("50");
    }

    @Test
    public void canAddAndDeleteGroup() {
        loginPage.login();
        groupsPage.deleteByCriteria(groupName);
        groupsPage.gotoAddGroup();
        groupsPage.enterName(groupName);
        groupsPage.pressSubmitButton();
        groupsPage.verifyAdded();
        groupsPage.deleteByCriteria(groupName);
    }

    @Test
    public void canViewCorrectAmountOfGroupsByDefault() {
        loginPage.login();
        groupsPage.gotoPage();
        Assertions.assertThat(groupsPage.getShowRecordsText()).as("Show Records Dropdown Default Value").contains("50");
    }

    @Test
    public void searchReturnsCorrectAmountOfGroups() {
        String groupName = "Group 1 - Auto";
        String groupName2 = "Group 2 - Auto";
        loginPage.login();
        groupsPage.deleteByCriteria("Auto");
        groupsPage.createGroup(groupName);
        groupsPage.verifyAdded();
        groupsPage.createGroup(groupName2);
        groupsPage.verifyAdded();
        groupsPage.gotoPage();
        groupsPage.search("Group");
        groupsPage.verifyTableInfoContains("Show records 1 up to 2 from a total of");
        groupsPage.deleteByCriteria("Auto");
    }

 //TODO: implement test
    public void create5Groups() {
        String groupName = "AutoGroup";
        loginPage.login();
        groupsPage.gotoPage();
        for (int i = 0; i < 5; i++) {
            groupsPage.gotoAddGroup();
            groupsPage.enterName(groupName + i);
            groupsPage.pressSubmitButton();
            groupsPage.verifyAdded();
            groupsPage.gotoPage();
        }
    }

    @Test
    public void showRecordsContainsCorrectAmountOfOptions(){
        loginPage.login();
        groupsPage.gotoPage();
        Assertions.assertThat(groupsPage.getAllShowRecordsOptions()).as("Show Records").contains("10", "25", "50", "100");
    }

    @Test
    public void tableInfoTextIsCorrectWhenNoGroupsAreFound() {
        loginPage.login();
        groupsPage.gotoPage();
        groupsPage.search("Non-existing user");
        groupsPage.verifyTableInfoContains("Show records 0 up to 0 from a total of 0");
    }


}

import entities.User;
import enums.ErrorMessages;
import enums.ShowRecords;
import enums.SuccessMessages;
import org.fest.assertions.Assertions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.login.DashboardPage;
import pages.login.LoginPage;
import pages.users.GroupsPage;
import pages.users.UsersPage;
import util.Constants;

public class UsersTest extends AbstractTest {
    private LoginPage loginPage;
    private UsersPage usersPage;
    private GroupsPage groupsPage;
    private DashboardPage dashboardPage;

    @BeforeMethod
    public void beforeTest() {
        startBrowser();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        usersPage = PageFactory.initElements(driver, UsersPage.class);
        groupsPage = PageFactory.initElements(driver, GroupsPage.class);
        dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
    }


    @Test
    public void canViewCorrectAmountOfUsersByDefault() {
        loginPage.login();
        usersPage.gotoPage();
        Assertions.assertThat(usersPage.getShowRecordsText()).as("Show Records Dropdown Default Value").contains("50");
    }

    @Test
    public void canAddUserWithStartEndDate() {
        loginPage.login();
        usersPage.deleteByCriteria("Auto");
        usersPage.gotoAddUser();
        usersPage.enterFirstName("Auto");
        usersPage.enterLastName("Added");
        usersPage.selectStartDate("2018", "Feb", "26");
        usersPage.selectEndDate("2019", "Nov", "13");
        usersPage.pressSubmitButton();
        usersPage.verifyAdded();
        usersPage.deleteByCriteria("Auto");
    }

    @Test
    public void createUserAndAssignItToGroup() {
        String groupName = "Auto Group";
        loginPage.login();
        //Create group to assign to
        groupsPage.deleteByCriteria(groupName);
        groupsPage.createGroup(groupName);
        groupsPage.verifyAdded();
        //Create user and assign to group
        usersPage.gotoAddUser();
        usersPage.enterFirstName("Auto");
        usersPage.enterLastName("User Assigned");
        usersPage.pressAssignToAllGroupsButton();
        usersPage.pressSubmitButton();
        usersPage.verifyAdded();
        usersPage.gotoPage();
        Assertions.assertThat(usersPage.getTextFromAllRowsByCriteria("Auto")).as("Text").contains("Auto Group");
        usersPage.deleteByCriteria("Auto");
    }

    @Test
    public void canAddNewUserWithoutPinAndDeleteIt() {
        loginPage.login();
        usersPage.create(new User("Automated", "No PIN"));
        usersPage.verifyAdded();
        Assertions.assertThat(usersPage.getPinCardWarnText()).as("User Add Success").contains(SuccessMessages.USER_PIN_WARN.getMessage());
        usersPage.deleteByCriteria("Automated");
        usersPage.verifyDeleted();
    }

    @Test
    public void canAddNewUserWithPin() {
        loginPage.login();
        User user = new User("Automated", "User");
        user.setInsertion("Insertion");
        user.setPin("123456789");
        usersPage.create(user);
        usersPage.verifyAdded();
        usersPage.deleteByCriteria("Automated");
    }

    @Test
    public void canAddUserWithInfoFields() {
        loginPage.login();
        String firstName = "InfoUser";
        String lastName = "Family";
        usersPage.deleteByCriteria(firstName);
        User user = new User(firstName, lastName);
        user.setInsertion("Insertion");
        user.setPin("123456789");
        user.setInfo1("Info 1");
        user.setInfo2("Info 2");
        usersPage.create(user);
        usersPage.verifyAdded();
        usersPage.deleteByCriteria(firstName);
    }

    @Test
    public void cantAddUserWithMissingMandatoryFields() {
        loginPage.login();
        usersPage.gotoPage();
        usersPage.createUser("", "Weinberg");
        Assertions.assertThat(usersPage.getFirstNameMissingError()).as("Missing FirstName").contains(ErrorMessages.USER_REQUIRED_FIELD_MISSING.getMessage());
        usersPage.createUser("Jerry", "");
        Assertions.assertThat(usersPage.getLastNameMissingError()).as("Missing FirstName").contains(ErrorMessages.USER_REQUIRED_FIELD_MISSING.getMessage());
    }


    @Test
    public void canDeleteAllExistingUsers() {
        loginPage.login();
        usersPage.deleteByCriteria("Auto");
    }

    @Test
    public void filtersClearedAfterLogout(){
        loginPage.login();
        usersPage.gotoPage();
        usersPage.selectShowRecords(ShowRecords.TEN);
        usersPage.search("Text to search");
        dashboardPage.logout();
        loginPage.login();
        usersPage.gotoPage();
        Assertions.assertThat(usersPage.getSearchFieldText()).as("Search Text").contains(Constants.BLANK);
        Assertions.assertThat(usersPage.getShowRecordsText()).as("Show Records Dropdown Default Value").contains("50");
    }

    @Test
    public void tableInfoTextIsCorrectWhenNoUsersAreFound() {
        loginPage.login();
        usersPage.gotoPage();
        usersPage.selectShowRecords(ShowRecords.TEN);
        usersPage.search("Non-existing user");
        usersPage.verifyTableInfoContains("Show records 0 up to 0 from a total of 0");
    }

    @Test
    public void showRecordsContainsCorrectAmountOfOptions(){
        loginPage.login();
        usersPage.gotoPage();
        Assertions.assertThat(usersPage.getAllShowRecordsOptions()).as("Show Records").contains("10", "25", "50", "100");
    }





}

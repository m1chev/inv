package pages.users;

import entities.User;
import enums.Pages;
import org.fest.assertions.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import util.BasePage;
import util.IPage;

import java.util.List;

public class UsersPage extends BasePage implements IPage {
    @FindBy(how = How.ID, using = "firstname")
    private WebElement firstnameField;

    @FindBy(how = How.ID, using = "gag")
    private WebElement insertionField;

    @FindBy(how = How.ID, using = "lastname")
    private WebElement lastNameField;

    @FindBy(how = How.ID, using = "id")
    private WebElement userIdField;

    @FindBy(how = How.ID, using = "pin")
    private WebElement pinField;

    @FindBy(how = How.ID, using = "info1")
    private WebElement info1Field;

    @FindBy(how = How.ID, using = "info2")
    private WebElement info2Field;

    @FindBy(how = How.ID, using = "info3")
    private WebElement info3Field;

    @FindBy(how = How.ID, using = "firstname-error")
    private WebElement firstNameMissingError;

    @FindBy(how = How.ID, using = "lastname-error")
    private WebElement lastNameMissingError;

    @FindBy(how = How.NAME, using = "start_date")
    private WebElement startDatePicker;

    @FindBy(how = How.NAME, using = "end_date")
    private WebElement endDatePicker;


    @FindBy(how = How.XPATH, using = "//div[@class='alert alert-warning alert-dismissable']")
    private WebElement pinCardWarnMessage;

    @FindBy(how = How.XPATH, using = "//button[@class='btn moveall btn-white btn-bold btn-info']")
    private WebElement assignAllGroupsButton;

    @FindBy(how = How.XPATH, using = "//button[@class='btn removeall btn-white btn-bold btn-info']")
    private WebElement unAssignFromAllGroupsButton;


    public UsersPage(WebDriver driver) {
        super(driver);
    }

    public void gotoPage() {
        action.gotoPage(Pages.USERS_PAGE.getUrl());
        Assertions.assertThat(getBreadcrumbText()).as("Users in breadcrumb").contains("Users");
        Assertions.assertThat(isTableDisplayed()).as("Table Displayed").isTrue();
    }

    public void pressAssignToAllGroupsButton() {
        action.clickButton(assignAllGroupsButton);
    }

    public void pressUnAssignFromAllGroupsButton() {
        action.clickButton(unAssignFromAllGroupsButton);
    }


    public void deleteUsers(List<String> userIds) {
        action.printInfo("Users to be deleted:" + userIds.toString());
        userIds.forEach(userId -> deleteUser(userId));
    }

    public void deleteByCriteria(String criteria) {
        gotoPage();
        search(criteria);
        List<String> ids = getAllRecordIdsByCriteria(criteria);
        deleteUsers(ids);
    }


    public void gotoAddUser() {
        action.gotoPage(Pages.ADD_USER_PAGE.getUrl());
    }

    public void deleteUser(String userId) {
        action.gotoPage(Pages.DELETE_USER_PAGE.getUrl() + "/" + userId);
    }

    public void enterFirstName(String firstName) {
        action.typeText(firstnameField, firstName);
    }

    public void enterInfo1(String text) {
        action.typeText(info1Field, text);
    }

    public void enterInfo2(String text) {
        action.typeText(info2Field, text);
    }

    public void enterInfo3(String text) {
        action.typeText(info3Field, text);
    }

    public void enterInsertion(String insertion) {
        action.typeText(insertionField, insertion);
    }

    public void enterLastName(String lastName) {
        action.typeText(lastNameField, lastName);
    }

    public void enterPin(String pin) {
        action.typeText(pinField, pin);
    }

    public String getPinCardWarnText() {
        return action.getText(pinCardWarnMessage);
    }

    public String getFirstNameMissingError() {
        return action.getText(firstNameMissingError);
    }

    public String getLastNameMissingError() {
        return action.getText(lastNameMissingError);
    }

    public String getUserId() {
        return action.getValueAttribute(userIdField);
    }

    public void createUser(String firstName, String lastName) {
        gotoAddUser();
        enterFirstName(firstName);
        enterInsertion(lastName);
        pressSubmitButton();
    }


    public void create(User user) {
        gotoAddUser();
        enterFirstName(user.getFirstName());
        enterInsertion(user.getInsertion());
        enterLastName(user.getLastName());
        enterPin(user.getPin());
        enterInfo1(user.getInfo1());
        enterInfo2(user.getInfo2());
        pressSubmitButton();
    }

    public void selectStartDate(String year, String month, String day) {
        selectDate(startDatePicker, month, year, day);
    }

    public void selectEndDate(String year, String month, String day) {
        selectDate(endDatePicker, month, year, day);
    }


}

package pages.administration;

import enums.Pages;
import org.fest.assertions.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import util.BasePage;
import util.IPage;

import java.util.ArrayList;
import java.util.List;

public class AdministratorsPage extends BasePage implements IPage {

    @FindBy(how = How.ID, using = "username")
    private WebElement usernameField;

    @FindBy(how = How.ID, using = "email")
    private WebElement emailField;

    @FindBy(how = How.ID, using = "password")
    private WebElement passwordField;

    @FindBy(how = How.ID, using = "password-re")
    private WebElement confirmPasswordField;

    @FindBy(how = How.ID, using = "id")
    private WebElement userIdField;

    @FindBy(how = How.ID, using = "adminlevel-id")
    private WebElement levelDropdown;

    @FindBy(how = How.NAME, using = "language")
    private WebElement languageDropdown;

    @FindBy(how = How.ID, using = "firstname-error")
    private WebElement firstNameMissingError;

    @FindBy(how = How.ID, using = "lastname-error")
    private WebElement lastNameMissingError;

    @FindBy(how = How.XPATH, using = "//div[@class='error-message']")
    private WebElement passwordError;


    public AdministratorsPage(WebDriver driver) {
        super(driver);
    }


    public List<String> getAllUserIdsForDeletion(String criteria) {
        List<String> ids = new ArrayList<>();
        List<WebElement> rowsForDeletion = getAllRowsForDeletion(criteria);
        rowsForDeletion.forEach(row -> ids.add(getCell(row, 1)));
        return ids;
    }

    public List<String> getAllLevels() {
        Select select = new Select(levelDropdown);
        return action.getAllDropdownOptions(select);
    }

    public List<String> getAllLanguages() {
        Select select = new Select(languageDropdown);
        return action.getAllDropdownOptions(select);
    }

    public void selectLevel(String visibleText) {
        Select select = new Select(levelDropdown);
        action.selectByVisibleText(select, visibleText);
    }


    public void delete(List<String> userIds) {
        System.out.println("Users to be deleted:" + userIds.toString());
        userIds.forEach(userId -> delete(userId));
    }

    public void deleteByCriteria(String criteria) {
        gotoPage();
        List<String> ids = getAllRecordIdsByCriteria(criteria);
        delete(ids);
    }

    public String getPasswordError() {
        return action.getText(passwordError);
    }


    public void gotoAddAdministrator() {
        action.gotoPage(Pages.ADD_ADMINISTRATORS_PAGE.getUrl());
    }

    public void delete(String userId) {
        action.gotoPage(Pages.DELETE_ADMINISTRATORS_PAGE.getUrl() + "/" + userId);
    }

    public void enterUsername(String username) {
        action.typeText(usernameField, username);
    }

    public void enterEmail(String email) {
        action.typeText(emailField, email);
    }

    public void enterPassword(String password) {
        action.typeText(passwordField, password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        action.typeText(confirmPasswordField, confirmPassword);
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

    public void createAdministrator(String username, String email, String password, String confirmPassword) {
        gotoPage();
        gotoAddAdministrator();
        enterUsername(username);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        pressSubmitButton();
    }


    public void gotoPage() {
        action.gotoPage(Pages.ADMINISTRATORS_PAGE.getUrl());
        Assertions.assertThat(getBreadcrumbText()).as("Administrators in breadcrumb").contains("Administrators");
    }


}

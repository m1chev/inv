package pages.login;

import enums.ErrorMessages;
import enums.Pages;
import org.fest.assertions.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import util.BasePage;
import util.Constants;
import util.Wait;


public class LoginPage extends BasePage {
    @FindBy(how = How.ID, using = "company")
    private WebElement companyField;

    @FindBy(how = How.ID, using = "username")
    private WebElement usernameField;

    @FindBy(how = How.ID, using = "loginWithFinger")
    private WebElement biometricLoginButton;

    @FindBy(how = How.ID, using = "password")
    private WebElement passwordField;

    @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(how = How.XPATH, using = "//div[@class='alert alert-danger alert-dismissable']")
    private WebElement badLoginPopup;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void gotoPage() {
        action.gotoPage(Pages.LOGIN_PAGE.getUrl());
    }

    private boolean isBiometricLoginButtonVisible() {
        return action.isVisible(biometricLoginButton);
    }


    public void enterUsername(String username) {
        action.typeText(usernameField, username);
    }

    public void enterCompany(String company) {
        action.typeText(companyField, company);
    }

    public void enterPassword(String password) {
        action.typeText(passwordField, password);
    }

    public void pressLoginButton() {
        action.clickButton(loginButton);
    }

    public void login(String company, String username, String password) {
        gotoPage();
        enterCompany(company);
        enterUsername(username);
        enterPassword(password);
        pressLoginButton();
        Wait.wait(1);
        action.acceptAlert();
    }

    public void login() {
        login(Constants.COMPANY, Constants.USERNAME, Constants.PASSWORD);
    }

    public void verifyBadCompanyMessage() {
        Assertions.assertThat(getBadLoginMessage()).as("Bad Login Message").contains(ErrorMessages.BAD_LOGIN_COMPANY.getMessage());
    }

    public void verifyBadCredentialsMessage() {
        Assertions.assertThat(getBadLoginMessage()).as("Bad Login Message").contains(ErrorMessages.BAD_LOGIN_CREDENTIALS.getMessage());
    }

    public String getBadLoginMessage() {
        return action.getText(badLoginPopup);
    }


}

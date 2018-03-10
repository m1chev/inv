package inv;

import org.fest.assertions.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import util.Constants;
import util.PageAction;
import util.Pages;

public class LoginPage {
    private PageAction action;

    @FindBy(how = How.ID, using = "loginusername")
    private WebElement usernameField;

    @FindBy(how = How.ID, using = "loginWithFinger")
    private WebElement biometricLoginButton;

    @FindBy(how = How.ID, using = "loginpassword")
    private WebElement passwordField;

    @FindBy(how = How.ID, using = "loginsubmit")
    private WebElement loginButton;

    @FindBy(how = How.XPATH, using = "//div[@class='selenium-error-block']")
    private WebElement badLoginPopup;


    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        action = new PageAction(driver);
    }

    public void gotoPage() {
        action.gotoPage(Pages.INV_URL.getPath(), Pages.INV_LOGIN_PAGE.getPath());
    }


    public void enterUsername(String username) {
        action.typeText(usernameField, username);
    }

    public void enterPassword(String password) {
        action.typeText(passwordField, password);
    }

    public void pressLoginButton() {
        action.clickButton(loginButton);
    }

    public void login(String username, String password) {
        gotoPage();
        enterUsername(username);
        enterPassword(password);
        pressLoginButton();
        action.acceptAlert();
    }

    public void login() {
        login(Constants.USERNAME, Constants.PASSWORD);
    }

    public void verifyMessage(String message) {
        Assertions.assertThat(getBadLoginMessage()).as("Bad Login Message").contains(message);
    }


    public String getBadLoginMessage() {
        return action.getText(badLoginPopup);
    }
}

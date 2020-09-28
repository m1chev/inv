package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ForgottenPasswordPage {
    private PageAction action;

    @FindBy(how = How.XPATH, using = "//h4")
    private WebElement pageTitle;

    @FindBy(how = How.XPATH, using = "//p[@class='control-label error']")
    private WebElement errorMessage;

    @FindBy(how = How.ID, using = "submit")
    private WebElement submitButton;

    @FindBy(how = How.ID, using = "email")
    private WebElement emailField;


    public ForgottenPasswordPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        action = new PageAction(driver);
    }

    public String getPageTitle(){
        return action.getText(pageTitle);
    }

    public void clickSendButton() {
        submitButton.click();
    }

    public void enterEmail (String email){
        action.typeText(emailField, email);
    }

    public String getErrorMessage() {
        return action.getText(errorMessage);

    }
}

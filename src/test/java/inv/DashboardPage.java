package inv;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import util.PageAction;

public class DashboardPage {
    private PageAction action;

    @FindBy(how = How.ID, using = "loginusername")
    private WebElement usernameField;

    @FindBy(how = How.XPATH, using = "//div[@class='userpanel-header']")
    private WebElement userPanel;


    public DashboardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        action = new PageAction(driver);
    }

    public String getUserPanelText(){
       return action.getText(userPanel);
    }
}

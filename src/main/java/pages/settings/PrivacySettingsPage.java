package pages.settings;

import enums.Pages;
import org.fest.assertions.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import util.BasePage;
import util.IPage;

import java.util.List;

public class PrivacySettingsPage extends BasePage implements IPage {
    @FindBy(how = How.NAME, using = "pw_reset_interval")
    private WebElement passwordResetIntervalDropdown;

    @FindBy(how = How.NAME, using = "delete_log_interval")
    private WebElement logDeleteIntervalDropdown;

    @FindBy(how = How.NAME, using = "delete_user_interval")
    private WebElement deleteUserIntervalDropdown;

    public PrivacySettingsPage(WebDriver driver) {
        super(driver);
    }

    public void selectPassResetInterval(String interval) {
        action.selectByVisibleText(passwordResetIntervalDropdown, "Never");
    }

    public List<String> getAllResetPasswordOptions(){
        return action.getAllDropdownOptions(passwordResetIntervalDropdown);
    }

    public List<String> getAllLogDeleteIntervalOptions(){
        return action.getAllDropdownOptions(logDeleteIntervalDropdown);
    }

    public List<String> getAllUserDeleteIntervalOptions(){
        return action.getAllDropdownOptions(deleteUserIntervalDropdown);
    }


    public void gotoPage() {
        action.gotoPage(Pages.PRIVACY_SETTINGS_PAGE.getUrl());
        Assertions.assertThat(getBreadcrumbText()).as("Privacy Settings in breadcrumb").contains("Privacy Settings");
    }
}

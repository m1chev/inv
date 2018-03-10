package pages.settings;

import enums.Pages;
import org.fest.assertions.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import util.BasePage;
import util.IPage;

/**
 * Created by Pragmatic on 2/2/2018.
 */
public class ReportSettingsPage extends BasePage implements IPage {
    @FindBy(how = How.XPATH, using = "//div[@class='alert alert-danger alert-dismissable']")
    private WebElement defaulWarningMessage;

    public ReportSettingsPage(WebDriver driver) {
        super(driver);
    }

    public void gotoPage() {
        action.gotoPage(Pages.REPORT_SETTINGS_PAGE.getUrl());
        Assertions.assertThat(getBreadcrumbText()).as("Report Settings in breadcrumb").contains("Report Settings");
    }

    public String getDefaultWarningMessage() {
        return action.getText(defaulWarningMessage);
    }

}

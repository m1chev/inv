package pages.reports;

import enums.Pages;
import org.fest.assertions.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import util.BasePage;
import util.IPage;

/**
 * Created by Pragmatic on 2/2/2018.
 */
public class OverviewPage extends BasePage implements IPage {
    @FindBy(how = How.ID, using = "overviewReport")
    public WebElement viewButton;

    @FindBy(how = How.ID, using = "overviewReport")
    public WebElement exportButton;

    @FindBy(how = How.ID, using = "locations")
    private WebElement locationsDropDown;

    @FindBy(how = How.XPATH, using = "//div[@class='overview']")
    private WebElement overviewTree;

    public OverviewPage(WebDriver driver) {
        super(driver);
    }

    public void gotoPage() {
        action.gotoPage(Pages.OVERVIEW_PAGE.getUrl());
        Assertions.assertThat(getBreadcrumbText()).as("Overview in breadcrumb").contains("Overview");
    }

    public void selectLocation(String location) {
        Select showRecordDropDown = new Select(locationsDropDown);
        action.selectByVisibleText(showRecordDropDown, location);
    }

    public void clickViewButton() {
        action.clickButton(viewButton);
    }

    public void clickExportButton() {
        action.clickButton(exportButton);
    }

    public void verifyOverviewVisible() {
        Assertions.assertThat(action.isVisible(overviewTree)).as("Overview tree is rendered").isTrue();
    }

}

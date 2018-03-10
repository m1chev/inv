package pages.login;

import enums.Pages;
import org.fest.assertions.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import util.BasePage;
import util.IPage;

public class DashboardPage extends BasePage implements IPage {
    @FindBy(how = How.XPATH, using = "//div[@class='widget-header']")
    private WebElement planHeader;

    @FindBy(how = How.ID, using = "breadcrumbs")
    public WebElement breadcrumb;

    @FindBy(how = How.XPATH, using = "//div[@class='col-xs-12 col-md-12 col-lg-6 no-padding-mobile']")
    private WebElement dashboardTable;

    @FindBy(how = How.XPATH, using = "//span[@class='menu-text']")
    private WebElement dashboardLink;

    @FindBy(how = How.XPATH, using = "//canvas[@class='flot-overlay']")
    private WebElement logCanvas;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void logout() {
        action.gotoPage(Pages.LOGOUT.getUrl());
    }

    public void gotoPage() {
        action.gotoPage(Pages.HOME_PAGE.getUrl());
        Assertions.assertThat(getBreadcrumbText()).as("Index in breadcrumb").contains("Index");
        Assertions.assertThat(getPlanHeaderText()).as("Plan Info").contains("Subscription plan ( IdentySoft Unlimited )");
    }

    public void clickDashboardLink() {
        action.clickButton(dashboardLink);
    }

    public String getPlanHeaderText() {
        return action.getText(planHeader);
    }

    public boolean isTableDisplayed() {
        return action.isVisible(dashboardTable);
    }

    public boolean isLogCanvasVisible() {
        return action.isVisible(logCanvas);
    }


}

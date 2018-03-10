package pages.access;

import enums.Pages;
import org.fest.assertions.Assertions;
import org.openqa.selenium.WebDriver;
import util.BasePage;
import util.IPage;

public class AccessControlPage extends BasePage implements IPage {

    public AccessControlPage(WebDriver driver) {
        super(driver);
    }

    public void gotoPage() {
        action.gotoPage(Pages.ACCESS_CONTROL_PAGE.getUrl());
        Assertions.assertThat(getBreadcrumbText()).as("Access Control in breadcrumb").contains("Access Control");
    }

}

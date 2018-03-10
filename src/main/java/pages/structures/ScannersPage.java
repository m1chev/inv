package pages.structures;

import enums.Pages;
import org.fest.assertions.Assertions;
import org.openqa.selenium.WebDriver;
import util.BasePage;
import util.IPage;

public class ScannersPage extends BasePage implements IPage {
    public ScannersPage(WebDriver driver) {
        super(driver);
    }

    public void gotoPage() {
        action.gotoPage(Pages.SCANNERS_PAGE.getUrl());
        Assertions.assertThat(getBreadcrumbText()).as("Scanners in breadcrumb").contains("Scanners");
    }

}

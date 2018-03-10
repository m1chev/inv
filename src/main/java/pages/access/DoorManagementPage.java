package pages.access;

import enums.Pages;
import org.fest.assertions.Assertions;
import org.openqa.selenium.WebDriver;
import util.BasePage;
import util.IPage;

/**
 * Created by Pragmatic on 2/2/2018.
 */
public class DoorManagementPage extends BasePage implements IPage {

    public DoorManagementPage(WebDriver driver) {
        super(driver);
    }

    public void gotoPage() {
        action.gotoPage(Pages.DOOR_MANAGEMENT_PAGE.getUrl());
        Assertions.assertThat(getBreadcrumbText()).as("Door Management in breadcrumb").contains("Door Management");
    }

}

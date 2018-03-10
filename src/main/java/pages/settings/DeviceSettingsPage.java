package pages.settings;

import enums.Pages;
import org.fest.assertions.Assertions;
import org.openqa.selenium.WebDriver;
import util.BasePage;
import util.IPage;

/**
 * Created by Pragmatic on 2/2/2018.
 */
public class DeviceSettingsPage extends BasePage implements IPage {

    public DeviceSettingsPage(WebDriver driver) {
        super(driver);
    }


    public void gotoPage() {
        action.gotoPage(Pages.DEVICE_SETTINGS_PAGE.getUrl());
        Assertions.assertThat(getBreadcrumbText()).as("Device Settings in breadcrumb").contains("Device Settings");
    }

}

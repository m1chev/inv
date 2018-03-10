package pages.settings;

import enums.Pages;
import org.fest.assertions.Assertions;
import org.openqa.selenium.WebDriver;
import util.BasePage;
import util.IPage;

public class FunctionKeySettingsPage extends BasePage implements IPage {
    public FunctionKeySettingsPage(WebDriver driver) {
        super(driver);
    }

    public void gotoPage() {
        action.gotoPage(Pages.FUNCTION_KEY_SETTINGS_PAGE.getUrl());
        Assertions.assertThat(getBreadcrumbText()).as("Function Key Settings in breadcrumb").contains("Function Key Settings");
    }

}

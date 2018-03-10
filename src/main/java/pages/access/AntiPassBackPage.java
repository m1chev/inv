package pages.access;

import enums.Pages;
import org.fest.assertions.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import util.BasePage;
import util.IPage;

import java.util.List;

/**
 * Created by Pragmatic on 2/2/2018.
 */
public class AntiPassBackPage extends BasePage implements IPage {
    @FindBy(how = How.ID, using = "type")
    private WebElement typeDropdown;

    public AntiPassBackPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getAllTypeOptions(){
        return action.getAllDropdownOptions(typeDropdown);
    }

    public void gotoPage() {
        action.gotoPage(Pages.ANTIPASSBACK_PAGE.getUrl());
        Assertions.assertThat(getBreadcrumbText()).as(" Anti PassBack in breadcrumb").contains(" Anti Passback");
    }


}

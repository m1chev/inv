package pages.structures;

import enums.Pages;
import org.fest.assertions.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import util.BasePage;
import util.IPage;

import java.util.List;

public class ZonesPage extends BasePage implements IPage {
    @FindBy(how = How.ID, using = "name")
    public WebElement nameField;


    public ZonesPage(WebDriver driver) {
        super(driver);
    }

    public void gotoPage() {
        action.gotoPage(Pages.ZONES_PAGE.getUrl());
        Assertions.assertThat(getBreadcrumbText()).as("Zones in breadcrumb").contains("Zones");
    }

    public void gotoAddZones() {
        action.gotoPage(Pages.ADD_ZONES_PAGE.getUrl());
    }


    public void delete(String id) {
        action.gotoPage(Pages.DELETE_ZONES_PAGE.getUrl() + "/" + id);
    }

    public void delete(List<String> ids) {
        ids.forEach(id -> delete(id));
    }

    public void deleteByCriteria(String criteria) {
        List<String> tobeDeleted = getAllRecordIdsByCriteria(criteria);
        delete(tobeDeleted);
    }


    public void enterName(String name) {
        action.typeText(nameField, name);
    }

    public void createNewZone(String name, String timeZone, String syncType) {
        gotoPage();
        gotoAddZones();
        enterName(name);
        pressSubmitButton();
        verifyAdded();
    }


}

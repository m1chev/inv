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

public class LocationsPage extends BasePage implements IPage {
    @FindBy(how = How.ID, using = "name")
    public WebElement nameField;

    public LocationsPage(WebDriver driver) {
        super(driver);
    }

    public void enterName(String name) {
        action.typeText(nameField, name);
    }

    public void gotoPage() {
        action.gotoPage(Pages.LOCATIONS_PAGE.getUrl());
        Assertions.assertThat(getBreadcrumbText()).as("Locations in breadcrumb").contains("Locations");
    }

    public void gotoAddLocation() {
        action.gotoPage(Pages.ADD_LOCATIONS_PAGE.getUrl());
    }


    public void delete(String id) {
        action.gotoPage(Pages.DELETE_LOCATIONS_PAGE.getUrl() + "/" + id);
    }

    public void delete(List<String> ids){
        ids.forEach(id -> delete(id));
    }

    public void deleteByCriteria(String criteria){
        search(criteria);
        List<String> tobeDeleted = getAllRecordIdsByCriteria(criteria);
        delete(tobeDeleted);
    }

    public void createLocation(String name){
        createNewLocation(name, "", "");
    }


    public void createNewLocation(String name, String timeZone, String syncType) {
        gotoPage();
        gotoAddLocation();
        enterName(name);
        //TODO: add timezone/syncType
        pressSubmitButton();
    }


}

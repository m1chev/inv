package pages.structures;

import enums.Pages;
import org.fest.assertions.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import util.BasePage;
import util.IPage;

import java.util.List;

public class DoorsPage extends BasePage implements IPage {
    @FindBy(how = How.ID, using = "name")
    public WebElement nameField;

    @FindBy(how = How.ID, using = "timeschedule-id")
    public WebElement timeScheduleDropdown;

    public DoorsPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getAllTimeScheduleOptions(){
        Select select = new Select(timeScheduleDropdown);
        return action.getAllDropdownOptions(select);
    }

    public void selectTimeSchedule(String name){
        Select select = new Select(timeScheduleDropdown);
        action.selectByVisibleText(select, name);
    }

    public void enterName(String name){
        action.typeText(nameField, name);
    }

    public void delete(String id) {
        action.gotoPage(Pages.DELETE_DOORS_PAGE.getUrl() + "/" + id);
    }

    public void edit(String id) {
        action.gotoPage(Pages.EDIT_DOORS_PAGE.getUrl() + "/" + id);
    }

    public void editByCriteria(String name){
        List<String> ids = getAllRecordIdsByCriteria(name);
        edit(ids.get(0));
    }

    public void delete(List<String> ids) {
        ids.forEach(id -> delete(id));
    }

    public void deleteByCriteria(String criteria) {
        gotoPage();
        search(criteria);
        List<String> tobeDeleted = getAllRecordIdsByCriteria(criteria);
        delete(tobeDeleted);
    }

    public void gotoPage() {
        action.gotoPage(Pages.DOORS_PAGE.getUrl());
        Assertions.assertThat(getBreadcrumbText()).as("Doors in breadcrumb").contains("Doors");
    }

    public void createDoor(String name){
        gotoPage();
        pressAddButton();
        enterName(name);
        pressSubmitButton();
        verifyAdded();
    }

}

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
public class TimeSchedulesPage extends BasePage implements IPage {
    @FindBy(how = How.ID, using = "name")
    private WebElement nameField;

    public TimeSchedulesPage(WebDriver driver) {
        super(driver);
    }

    public void gotoPage() {
        action.gotoPage(Pages.TIME_SCHEDULES_PAGE.getUrl());
        Assertions.assertThat(getBreadcrumbText()).as("Time Schedules in breadcrumb").contains("Time Schedules"); //TODO: update when bug fixed
    }

    public void gotoAdd() {
        action.gotoPage(Pages.ADD_TIME_SCHEDULES_PAGE.getUrl());
    }

    public void delete(String scheduleId) {
        action.gotoPage(Pages.DELETE_TIME_SCHEDULES_PAGE.getUrl() + "/" + scheduleId);
    }

    public void delete(List<String> scheduleIds) {
        action.printInfo("Deleting ids:"+scheduleIds.toString());
        scheduleIds.forEach(scheduleId -> delete(scheduleId));
    }

    public void enterName(String name) {
        action.typeText(nameField, name);
    }

    public void deleteByCriteria(String criteria){
        List<String> groupIds = getAllRecordIdsByCriteria(criteria);
        delete(groupIds);
    }

    public void createTimeSchedule(String name){
        gotoAdd();
        enterName(name);
        pressSubmitButton();
        verifyAdded();
        gotoPage();
    }

}

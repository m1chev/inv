package pages.administration;

import enums.Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import util.BasePage;
import util.IPage;

import java.util.List;

public class AdminLevelPage extends BasePage implements IPage {
    @FindBy(how = How.ID, using = "name")
    private WebElement nameField;


    public AdminLevelPage(WebDriver driver) {
        super(driver);
    }

    public void delete(String id) {
        action.gotoPage(Pages.DELETE_ADMIN_LEVEL_PAGE.getUrl() + "/" + id);
        verifyDeleted();
        action.gotoPage(Pages.ADMIN_LEVEL_PAGE.getUrl());
    }

    public void deleteByCriteria(String criteria){
        gotoPage();
        search(criteria);
        List<String> groupIds = getAllRecordIdsByCriteria(criteria);
        delete(groupIds);
    }

    public void delete(List<String> ids) {
       ids.forEach(id -> delete(id));
    }


    public void gotoPage() {
        action.gotoPage(Pages.ADMIN_LEVEL_PAGE.getUrl());
    }


    public void enterName(String name) {
        action.typeText(nameField, name);
    }

    public void gotoAddAdminLevelPage() {
        action.gotoPage(Pages.ADD_ADMIN_LEVEL_PAGE.getUrl());
    }

    public void add(String levelName){
        gotoAddAdminLevelPage();
        enterName(levelName);
        pressSubmitButton();
    }



}

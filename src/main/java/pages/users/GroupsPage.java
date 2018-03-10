package pages.users;

import enums.Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import util.BasePage;
import util.IPage;

import java.util.List;

public class GroupsPage extends BasePage implements IPage {
    @FindBy(how = How.ID, using = "name")
    private WebElement nameField;


    public GroupsPage(WebDriver driver) {
        super(driver);
    }

    public void gotoPage() {
        action.gotoPage(Pages.GROUPS_PAGE.getUrl());
        action.checkContains(getBreadcrumbText(), "Groups", "Groups is contained in breadcrumb");
    }

    public void gotoAddGroup() {
        action.gotoPage(Pages.ADD_GROUP_PAGE.getUrl());
    }

    public void delete(String groupId) {
        action.gotoPage(Pages.DELETE_GROUP_PAGE.getUrl() + "/" + groupId);
        verifyDeleted();
        action.gotoPage(Pages.GROUPS_PAGE.getUrl());
    }

    public void delete(List<String> groupIds) {
        System.out.println("Deleting ids:"+groupIds.toString());
        groupIds.forEach(groupId -> delete(groupId));
    }

    public void enterName(String name) {
        action.typeText(nameField, name);
    }

    public void createGroup(String groupName){
        gotoAddGroup();
        enterName(groupName);
        pressSubmitButton();
    }

    private void create(String groupName){
        gotoAddGroup();
        enterName(groupName);
        pressSubmitButton();
    }

    public void deleteByCriteria(String criteria){
        gotoPage();
        search(criteria);
        List<String> groupIds = getAllRecordIdsByCriteria(criteria);
        delete(groupIds);
    }


}

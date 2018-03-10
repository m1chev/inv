package pages.reports;

import enums.Pages;
import org.fest.assertions.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import util.BasePage;
import util.IPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pragmatic on 2/2/2018.
 */
public class ReportsPage extends BasePage implements IPage {
    @FindBy(how = How.ID, using = "adminLogsFrom")
    public WebElement fromDate;

    @FindBy(how = How.ID, using = "adminLogsTo")
    public WebElement toDate;

    @FindBy(how = How.ID, using = "adminLogsAdmins")
    public WebElement administratorsDropdown;
    @FindBy(how = How.ID, using = "adminLogsView")
    public WebElement showLogButton;

    @FindBy(how = How.ID, using = "reportsView")
    public WebElement reportsViewButton;

    @FindBy(how = How.XPATH, using = "//a[@href='#tab1-3']")
    private WebElement timeRegistrationPeriodTab;

    @FindBy(how = How.XPATH, using = "//a[@href='#tab1-1']")
    private WebElement accessLogTab;

    @FindBy(how = How.XPATH, using = "//a[@href='#tab1-4']")
    private WebElement timeRegistrationGroupTab;

    @FindBy(how = How.XPATH, using = "//a[@href='#tab1-8']")
    private WebElement timeRegistrationUserTab;

    @FindBy(how = How.XPATH, using = "//a[@href='#tab1-2']")
    private WebElement overviewAccessRightsTab;

    @FindBy(how = How.XPATH, using = "//a[@href='#tab1-5']")
    private WebElement administratorsLogTab;

    @FindBy(how = How.XPATH, using = "//a[@href='#tab1-9']")
    private WebElement userAndTemplatesTab;

    @FindBy(how = How.XPATH, using = "//a[@href='/trackings']")
    private WebElement attendanceOverviewTab;

    public ReportsPage(WebDriver driver) {
        super(driver);
    }

    public void gotoPage() {
        action.gotoPage(Pages.REPORTS_PAGE.getUrl());
        Assertions.assertThat(getBreadcrumbText()).as("Reports in breadcrumb").contains("Reports");
    }

    public void gotoAdministratorsLogTab() {
        action.clickButton(administratorsLogTab);
    }

    public void gotoTimeRegistrationPeriodTab() {
        action.clickButton(timeRegistrationPeriodTab);
    }

    public void gotoTimeRegistrationGroupTab() {
        action.clickButton(timeRegistrationGroupTab);
    }

    public void gotoTimeRegistrationUserTab() {
        action.clickButton(timeRegistrationUserTab);
    }

    public void gotoAttendanceOverviewTab() {
        action.clickButton(attendanceOverviewTab);
    }

    public void gotoUserAndTemplatesTab() {
        action.clickButton(userAndTemplatesTab);
    }

    public void gotoAccessLogTab() {
        action.clickButton(accessLogTab);
    }

    public class AdministratorsLog {

        public void pressShowLogButton(){
            action.clickButton(showLogButton);
        }

        public void adminLogPopulateFromDate(String month, String year, String day) {
            fromDate.click();
            selectDate(fromDate, month, year, day);
        }


        public void adminLogPopulateToDate(String month, String year, String day) {
            selectDate(toDate, month, year, day);
        }

        public List<String> getAllAdmins(){
            List<String> admins = new ArrayList<>();
            Select select = new Select(administratorsDropdown);
            return action.getAllDropdownOptions(select);
        }
    }

    public void clickReportsViewButton() {
        action.clickButton(reportsViewButton);
    }


}

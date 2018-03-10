package util;

import enums.ErrorMessages;
import enums.ShowRecords;
import enums.SuccessMessages;
import org.apache.commons.lang3.Validate;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BasePage {
    private WebDriver driver;
    protected PageAction action;

    public static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);

    @FindBy(how = How.XPATH, using = "//input[@type = 'search']")
    private WebElement searchField;

    @FindBy(how = How.XPATH, using = "//a[@class = 'btn btn-sm btn-info']")
    private WebElement addButtonLink;

    @FindBy(how = How.XPATH, using = "//div[@class='error-message']")
    private WebElement badFieldError;


    @FindBy(how = How.XPATH, using = "//div[@class='alert alert-success alert-dismissable']")
    private WebElement operationSuccessMessage;

    @FindBy(how = How.XPATH, using = "//div[@class='alert alert-danger alert-dismissable']")
    private WebElement unableToAddMessage;

    @FindBy(how = How.ID, using = "dynamic-table")
    private WebElement table;

    @FindBy(how = How.XPATH, using = "//div[@class='col-xs-12']")
    private WebElement logTable;

    @FindBy(how = How.XPATH, using = "//select[@class='form-control input-sm']")
    private WebElement showRecordsDropdown;


    @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(how = How.XPATH, using = "//a[@class='btn btn-sm btn-default']")
    private WebElement backButton;

    @FindBy(how = How.ID, using = "breadcrumbs")
    private WebElement breadcrumb;


    @FindBy(how = How.ID, using = "dynamic-table_info")
    private WebElement tableInfo;

    @FindBy(how = How.XPATH, using = "//input[@type='search']")
    private WebElement searchUserField;

    @FindBy(how = How.XPATH, using = "//td[@class='sorting_1']")
    private WebElement userid;

    @FindBy(how = How.XPATH, using = "//select[@class='ui-datepicker-month']")
    private WebElement monthPickerDropdown;
    @FindBy(how = How.XPATH, using = "//select[@class='ui-datepicker-year']")
    private WebElement yearPickerDropdown;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        action = new PageAction(driver);
    }

    public void search(String criteria) {
        action.typeText(searchField, criteria);
        searchField.sendKeys(Keys.ENTER);
        Wait.wait(2);
    }

    public void verifyAdded() {
        action.checkContains(getOperationSuccessMessage(), SuccessMessages.RECORD_ADD_SUCCESS.getMessage(), "Record Add Success");
    }


    public void verifyUpdated() {
        action.checkContains(getOperationSuccessMessage(), SuccessMessages.RECORD_UPDATE_SUCCESS.getMessage(), "Record Update Success");
    }

    public String getSearchFieldText(){
        return action.getText(searchField);
    }

    public void verifyTableInfoContains(String expectedText) {
        action.checkContains(action.getText(tableInfo), expectedText, "Table Info");
    }

    public String getBadFieldErrorMessage() {
        return action.getText(badFieldError);
    }


    public void verifyContains(String expected, String actual) {
        action.checkContains(expected, actual, "Message Text");
    }

    public void verifyNotAdded() {
        action.checkContains(getUnableToAddMessage(), ErrorMessages.RECORD_NOT_ADDED.getMessage(), "Record Not Added");
    }

    public void verifyDuplicate() {
        action.checkContains(getBadFieldMessage(), ErrorMessages.RECORD_DUPLICATE.getMessage(), "Record Duplicate");
    }

    public void verifyDeleted() {
        action.checkContains(getSuccessMessage(), SuccessMessages.RECORD_DELETE_SUCCESS.getMessage(), "Record Deleted");
    }

    public String getBadFieldMessage() {
        return action.getText(badFieldError);
    }


    public String getSuccessMessage() {
        return action.getText(operationSuccessMessage);
    }

    public String getUnableToAddMessage() {
        return action.getText(unableToAddMessage);
    }


    public List<WebElement> getTableRows(String criteria) {
        return getRows(table, criteria);
    }


    public String getOperationSuccessMessage() {
        return action.getText(operationSuccessMessage);
    }


    public void pressSubmitButton() {
        action.clickButton(submitButton);
    }

    public void pressBackButton() {
        action.clickButton(backButton);
    }

    public List<WebElement> getAllRowsForDeletion(String criteria) {
        List<WebElement> rows = getTableRows();
        List<WebElement> rowsForDeletion = rows.stream()
                .filter(x -> x.getText().contains(criteria))
                .collect(Collectors.toList());
        LOGGER.info("Rows found for deletion:");
        rows.forEach(row -> row.getText());
        return rowsForDeletion;
    }

    public String getTextFromAllRowsByCriteria(String criteria) {
        List<String> rowsText = new ArrayList<>();
        List<WebElement> rows = getTableRows();
        List<WebElement> rowsForDeletion = rows.stream()
                .filter(x -> x.getText().contains(criteria))
                .collect(Collectors.toList());
        rowsForDeletion.forEach(row -> rowsText.add(row.getText()));
        return rowsText.toString();
    }

    public String getShowRecordsText() {
        Select select = new Select(showRecordsDropdown);
        return select.getAllSelectedOptions().get(0).getText();
    }

    public List<String> getAllRecordIdsByCriteria(String criteria) {
        List<String> ids = new ArrayList<>();
        List<WebElement> rowsForDeletion = getAllRowsForDeletion(criteria);
        rowsForDeletion.forEach(row -> ids.add(getCell(row, 1)));
        LOGGER.info("Ids found for deletion:" + ids.toString());
        return ids;
    }

    public List<String> getColumnNames(WebElement table) {
        Validate.notNull(table, "Table element should not be null");
        List<String> columnNames = table.findElements(By.tagName("th"))   // get table headers
                .stream()
                .map(WebElement::getText)        // get the text
                .map(String::trim)               // trim - no space
                .collect(Collectors.toList());
        LOGGER.info("Columns:" + columnNames.toString());
        return columnNames;
    }

    public List<WebElement> getRows(WebElement table) {
        Validate.notNull(table, "Table element should not be null");
        Wait.wait(1);
        List<WebElement> rows = table.findElements(By.tagName("tr"))   // get table rows
                .stream()
                .collect(Collectors.toList());
        rows.forEach(row -> LOGGER.info("Row found:" + row.getText()));
        return rows;
    }

    public List<WebElement> getRows(WebElement table, String criteria) {
        Validate.notNull(table, "Table element should not be null");
        Wait.wait(1);
        List<WebElement> rows = table.findElements(By.tagName("tr"))   // get table rows
                .stream()
                .filter(x -> x.getText().contains(criteria)) //filter by criteria
                .collect(Collectors.toList());
        rows.forEach(row -> LOGGER.info("Row:" + row.getText()));
        return rows;
    }

    public List<String> getCellsFromRow(WebElement row) {
        Validate.notNull(row, "Row element should not be null");
        Wait.wait(1);
        List<String> cells = row.findElements(By.xpath("td"))   // get table rows
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .collect(Collectors.toList());
        cells.forEach(cell -> LOGGER.info("Cell:" + cell));
        return cells;
    }

    public String getCell(WebElement row, int columnIndex) {
        Validate.notNull(row, "Row element should not be null");
        String cell = "";
        if (row == null) {
            LOGGER.info("No rows available");
        } else {
            cell = row.findElement(By.xpath("td[" + columnIndex + "]")).getText();  // get columns by index
            LOGGER.info("Cell value:" + cell);
        }
        return cell;
    }


    public List<String> getColumnNames() {
        return getColumnNames(table);
    }

    public List<WebElement> getTableRows() {
        return getRows(table);
    }

    public List<String> getRowCells(WebElement row) {
        return getCellsFromRow(row);
    }

    public String getRowCell(WebElement row, int columnIndex) {
        return getCell(row, columnIndex);
    }

    public boolean isTableDisplayed() {
        return action.isVisible(table);
    }

    public boolean isLogTableDisplayed() {
        return action.isVisible(logTable);
    }

    protected void selectDate(WebElement dateField, String month, String year, String day) {
        dateField.click();
        Select selectMonth = new Select(monthPickerDropdown);
        Select selectYear = new Select(yearPickerDropdown);
        action.selectByVisibleText(selectMonth, month);
        action.selectByVisibleText(selectYear, year);
        action.clickLinkByText(day);
        Wait.wait(1);
    }


    public String getTableInfoText() {
        return action.getText(tableInfo);
    }


    public String getBreadcrumbText() {
        return action.getText(breadcrumb);
    }

    public void selectShowRecords(ShowRecords records) {
        Select showRecordDropDown = new Select(showRecordsDropdown);
        showRecordDropDown.selectByVisibleText(records.getCount());
    }

    public void pressAddButton() {
        action.clickButton(addButtonLink);
    }

    public List<String> getAllShowRecordsOptions() {
        return action.getAllDropdownOptions(showRecordsDropdown);
    }


}

package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import util.Constants;
import util.enums.Pages;

public class CashBoxPage {
    private PageAction action;

    @FindBy(how = How.ID, using = "okmsg")
    private WebElement addSuccessMessage;

    @FindBy(how = How.NAME, using = "value")
    private WebElement valueField;

    @FindBy(how = How.NAME, using = "submit")
    private WebElement submitButton;

    @FindBy(how = How.NAME, using = "category")
    private WebElement categoryDropdown;

    @FindBy(how = How.ID, using = "cb_cats_1")
    private WebElement newCategoryNameField;

    @FindBy(how = How.NAME, using = "title")
    private WebElement titleField;

    @FindBy(how = How.NAME, using = "notes")
    private WebElement notesField;

    @FindBy(how = How.ID, using = "client_firmname")
    private WebElement firmNameField;

    @FindBy(how = How.XPATH, using = "//a[@class='btn-cashbox-add btn-green selenium-add-chashbox-in']")
    private WebElement newAddIncomeLink;

    @FindBy(how = How.XPATH, using = "//a[@class='btn-cashbox-add btn-red selenium-add-chashbox-out']")
    private WebElement addNewExpenseLink;

    @FindBy(how = How.ID, using = "cshbx_delbtn")
    private WebElement deleteItemButton;

    @FindBy(how = How.ID, using = "handle_all")
    private WebElement selectAllItemsCheckbox;


    public CashBoxPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        action = new PageAction(driver);
    }

    public void pressAddNewIncomeButton() {
        action.clickButton(newAddIncomeLink);
    }

    public void pressAddNewExpenseButton() {
        action.clickButton(addNewExpenseLink);
    }

    public String getNewExpenseButtonText() {
        return action.getText(addNewExpenseLink);
    }


    public String getNewIncomeButtonText() {
        return action.getText(newAddIncomeLink);
    }

    public void enterFirmName(String firmName) {
        action.typeText(firmNameField, firmName);
    }

    public void enterValue(String value) {
        action.typeText(valueField, value);
    }

    public void enterNotes(String notes) {
        action.typeText(notesField, notes);
    }

    public void enterTitle(String title) {
        action.typeText(titleField, title);
    }

    public void enterCategoryName(String categoryName) {
        action.typeText(newCategoryNameField, categoryName);
    }

    public void pressSubmitButton() {
        action.clickButton(submitButton);
    }

    public String getSuccessAddMessage() {
        return action.getText(addSuccessMessage);
    }

    public void createExpense(String value, String title, String firmName, String categoryName, String notes) {
        pressAddNewExpenseButton();
        enterValue(value);
        enterTitle(title);
        enterFirmName(firmName);
        selectCategory(categoryName);
        enterNotes(notes);
        pressSubmitButton();
    }

    public void createIncome(String value, String title, String firmName, String categoryName, String notes) {
        pressAddNewIncomeButton();
        enterValue(value);
        enterTitle(title);
        enterFirmName(firmName);
        selectCategory(categoryName);
        enterNotes(notes);
        pressSubmitButton();
    }

    public void selectCategory(String categoryName) {
        action.selectByVisibleText(categoryDropdown, categoryName);
    }


    public void deleteAllItems() {
        gotoPage();
        checkAllItems();
        pressDeleteItemButton();
        action.acceptAlert();
    }

    public void checkAllItems() {
        action.clickButton(selectAllItemsCheckbox);
    }

    public void pressDeleteItemButton() {
        action.clickButton(deleteItemButton);
    }

    public void gotoPage() {
        action.gotoPage(Pages.INV_CASH_BOX_PAGE.getPath());
    }
}

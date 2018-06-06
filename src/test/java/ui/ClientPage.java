package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import util.Constants;
import util.enums.Pages;

import java.util.Date;

public class ClientPage {
    private PageAction action;

    @FindBy(how = How.ID, using = "searchbtn")
    private WebElement searchButton;

    @FindBy(how = How.ID, using = "okmsg")
    private WebElement addSuccessMessage;

    @FindBy(how = How.NAME, using = "firm_name")
    private WebElement firmNameField;

    @FindBy(how = How.NAME, using = "firm_bulstat")
    private WebElement firmVatField;

    @FindBy(how = How.NAME, using = "firm_addr")
    private WebElement firmAddressField;

    @FindBy(how = How.NAME, using = "firm_town")
    private WebElement firmTownField;

    @FindBy(how = How.NAME, using = "price_quantity")
    private WebElement priceQuantityField;

    @FindBy(how = How.NAME, using = "do_submit")
    private WebElement addItemButton;

    @FindBy(how = How.ID, using = "cl_delbtn")
    private WebElement deleteClientButton;

    @FindBy(how = How.ID, using = "handle_all")
    private WebElement selectAllItemsCheckbox;

    @FindBy(how = How.XPATH, using = "//a[@class='newbtn selenium-add-client-button']")
    private WebElement addNewClientLink;


    public ClientPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        action = new PageAction(driver);
    }

    public void enterFirmName(String name) {
        action.typeText(firmNameField, name);
    }

    public void enterFirmVat(String vat) {
        action.typeText(firmVatField, vat);
    }

    public void enterFirmTown(String town) {
        action.typeText(firmTownField, town);
    }

    public void enterFirmAddress(String address) {
        action.typeText(firmAddressField, address);
    }

    public String getSuccessAddMessage() {
        return action.getText(addSuccessMessage);
    }


    public void checkAllItems() {
        action.clickButton(selectAllItemsCheckbox);
    }

    public void pressDeleteClientButton() {
        action.clickButton(deleteClientButton);
    }

    public void deleteAllClients() {
        gotoPage();
        checkAllItems();
        pressDeleteClientButton();
        action.acceptAlert();
    }


    public void pressAddItemButton() {
        action.clickButton(addItemButton);
    }

    public void clickAddNewClientLink() {
        action.clickButton(addNewClientLink);
    }

    public void createClient(String name, String vat, String address, String town) {
        gotoPage();
        clickAddNewClientLink();
        enterFirmName(name + "-" + new Date().toString());
        enterFirmVat(vat + "-" + new Date().toString());
        enterFirmAddress(address);
        enterFirmTown(town);
        pressAddItemButton();
    }


    public void gotoPage() {
        action.gotoPage(Pages.INV_CLIENT_PAGE.getPath());
    }

    public String getNewClientLinkText() {
        return action.getText(addNewClientLink);
    }

}

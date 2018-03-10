package inv;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import util.PageAction;
import util.Pages;

public class ItemPage {
    private PageAction action;

    @FindBy(how = How.ID, using = "searchbtn")
    private WebElement searchButton;

    @FindBy(how = How.ID, using = "okmsg")
    private WebElement addSuccessMessage;

    @FindBy(how = How.NAME, using = "name")
    private WebElement nameField;

    @FindBy(how = How.NAME, using = "name_en")
    private WebElement nameENField;

    @FindBy(how = How.NAME, using = "price")
    private WebElement priceField;

    @FindBy(how = How.NAME, using = "price_quantity")
    private WebElement priceQuantityField;

    @FindBy(how = How.NAME, using = "do_submit")
    private WebElement addItemButton;

    @FindBy(how = How.ID, using = "delbtn")
    private WebElement deleteItemButton;

    @FindBy(how = How.ID, using = "handle_all")
    private WebElement selectAllItemsCheckbox;

    @FindBy(how = How.XPATH, using = "//a[@class='newbtn selenium-add-item']")
    private WebElement addNewItemLink;


    public ItemPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        action = new PageAction(driver);
    }

    public void enterName(String name) {
        action.typeText(nameField, name);
    }

    public void enterNameENG(String name) {
        action.typeText(nameENField, name);
    }

    public void enterPrice(String price) {
        action.typeText(priceField, price);
    }

    public String getSuccessAddMessage(){
        return action.getText(addSuccessMessage);
    }

    public void enterPriceForQuantity(String priceQuantity) {
        action.typeText(priceQuantityField, priceQuantity);
    }

    public void checkAllItems() {
        action.clickButton(selectAllItemsCheckbox);
    }

    public void pressDeleteItemButton() {
        action.clickButton(deleteItemButton);
    }

    public void deleteAllItems(){
        gotoPage();
        checkAllItems();
        pressDeleteItemButton();
        action.acceptAlert();
    }


    public void pressAddItemButton() {
        action.clickButton(addItemButton);
    }

    public void clickAddNewItemLink() {
        action.clickButton(addNewItemLink);
    }

    public void createItem(String name, String nameENG, String price, String priceQuantity){
        gotoPage();
        clickAddNewItemLink();
        enterName(name);
        enterNameENG(nameENG);
        enterPrice(price);
        enterPriceForQuantity(priceQuantity);
        pressAddItemButton();
    }




    public void gotoPage() {
        action.gotoPage(Pages.INV_URL.getPath(), Pages.INV_ITEM_PAGE.getPath());
    }

    public String getNewItemLinkText(){
        return action.getText(addNewItemLink);
    }



}

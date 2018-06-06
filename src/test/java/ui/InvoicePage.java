package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import util.Constants;
import util.enums.Pages;

public class InvoicePage {
    private PageAction action;


    @FindBy(how = How.XPATH, using = "//a[@class='new selenium-new-invoice-button']")
    private WebElement newInvoiceButton;


    public InvoicePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        action = new PageAction(driver);
    }

    public void gotoPage() {
        action.gotoPage(Pages.INV_INVOICE_PAGE.getPath());
    }

    public String getNewInvoiceLinkText() {
        return action.getText(newInvoiceButton);
    }

}

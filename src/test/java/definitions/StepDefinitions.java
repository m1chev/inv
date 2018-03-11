package definitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.fest.assertions.Assertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.*;
import util.Constants;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class StepDefinitions {
    private static final Logger LOGGER = LoggerFactory.getLogger(StepDefinitions.class);
    //Drivers location
    private static final String chromeDriverLocation = "C:\\webdrivers\\chromedriver.exe";
    private static final String firefoxDriverLocation = "C:\\webdrivers\\geckodriver.exe";
    private static final String ieDriverLocation = "C:\\webdrivers\\IEDriverServer.exe";
    private static final String chromeProperty = "webdriver.chrome.driver";
    private static final String firefoxProperty = "webdriver.gecko.driver";
    private static final String ieProperty = "webdriver.ie.driver";
    //Page objects
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private ItemPage itemPage;
    private ClientPage clientPage;
    private CashBoxPage cashBoxPage;
    WebDriver driver;


    private void startBrowser(String browser) {
        if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty(firefoxProperty, firefoxDriverLocation);
            driver = new FirefoxDriver();
            configureBrowser(browser);
        }
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty(chromeProperty, chromeDriverLocation);
            driver = new ChromeDriver();
            configureBrowser(browser);
        }
        if (browser.equalsIgnoreCase("ie")) {
            System.setProperty(ieProperty, ieDriverLocation);
            driver = new InternetExplorerDriver();
            configureBrowser(browser);
        }

    }

    private void configureBrowser(String browser) {
        LOGGER.info("Starting browser:" + browser);
        driver.manage().deleteAllCookies(); //delete cookies
        driver.manage().window().maximize(); //To maximize browser
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);   //Implicit wait
    }


    @Before
    public void before() {
        startBrowser("chrome");
    }

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                TakesScreenshot screenshot = (TakesScreenshot) driver;
                File src = screenshot.getScreenshotAs(OutputType.FILE); //capture screenshot
                String fileName = scenario.getName() + ".png";
                FileUtils.copyFile(src, new File("E:\\screenshots\\" + fileName)); //copy file to location
                LOGGER.info("Successfully captured a screenshot");
                LOGGER.info("Stored image:" + fileName + " at:" + "E:\\screenshots\\");
            } catch (Exception e) {
                LOGGER.info("Exception while taking screenshot " + e.getMessage());
            }
        }
        driver.quit();
    }


    @Given("^user is on Login Page$")
    public void user_is_on_Home_Page() {
        loginPage = new LoginPage(driver);
        loginPage.gotoPage();
    }

    @When("^I navigate to Items Page$")
    public void gotoItemsPage() {
        itemPage = new ItemPage(driver);
        itemPage.gotoPage();
    }

    @When("^I navigate to CashBox Page$")
    public void gotoCashBoxPage() {
        cashBoxPage = new CashBoxPage(driver);
        cashBoxPage.gotoPage();
    }

    @When("^I navigate to Clients Page$")
    public void gotoClientsPage() {
        clientPage = new ClientPage(driver);
        clientPage.gotoPage();
    }

    @Given("^user is logged in the system$")
    public void userLoggedIn() {
        loginPage = new LoginPage(driver);
        loginPage.gotoPage();
        loginPage.enterUsername(Constants.USERNAME);
        loginPage.enterPassword(Constants.PASSWORD);
        loginPage.pressLoginButton();
    }


    @When("^I enter username \"([^\"]*)\"$")
    public void enterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @And("^I enter password \"([^\"]*)\"$")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @And("^I enter item name \"([^\"]*)\"$")
    public void enterItemName(String name) {
        itemPage.enterName(name);
    }

    @When("^I create new item with name:\"(.*)\" and nameENG:\"(.*)\" and price:\"(.*)\" and priceQuantity:\"(.*)\"$")
    public void createNewItem(String name, String nameENG, String price, String priceQuantity) {
        itemPage.createItem(name, nameENG, price, priceQuantity);
    }

    @When("^I create new client with name:\"(.*)\" and vat:\"(.*)\" and address:\"(.*)\" and town:\"(.*)\"$")
    public void createNewClient(String name, String vat, String address, String town) {
        clientPage.createClient(name, vat, address, town);
    }

    @When("^I create new expense with value:\"(.*)\" and title:\"(.*)\" and firmName:\"(.*)\" and category:\"(.*)\" and notes:\"(.*)\"$")
    public void createNewExpense(String value, String title, String firmName, String category, String notes) {
        cashBoxPage.createExpense(value, title, firmName, category, notes);
    }

    @When("^I create new income with value:\"(.*)\" and title:\"(.*)\" and firmName:\"(.*)\" and category:\"(.*)\" and notes:\"(.*)\"$")
    public void createNewIncome(String value, String title, String firmName, String category, String notes) {
        cashBoxPage.createIncome(value, title, firmName, category, notes);
    }

    @And("^I press Login button$")
    public void iPressLoginButton() {
        loginPage.pressLoginButton();
    }

    @When("^I delete all items")
    public void deleteAllItems() {
        itemPage.deleteAllItems();
    }

    @When("^I delete all expenses")
    public void deleteAllExpenses() {
        cashBoxPage.deleteAllItems();
    }

    @When("^I delete all clients")
    public void deleteAllClients() {
        clientPage.deleteAllClients();
    }

    @Then("^login error message with text should be displayed:\"(.*)\"$")
    public void loginErrorMessage(String errorMessage) {
        loginPage.verifyMessage(errorMessage);
    }

    @Then("^item message with text should be displayed:\"(.*)\"$")
    public void itemSuccessMessage(String successMessage) {
        Assertions.assertThat(itemPage.getSuccessAddMessage()).as("Item Added").contains(successMessage);
    }

    @Then("^client message with text should be displayed:\"([^\"]*)\"$")
    public void clientSuccessMessage(String successMessage) {
        Assertions.assertThat(clientPage.getSuccessAddMessage()).as("Item Added").contains(successMessage);
    }


    @Then("^user panel should contain text:\"([^\"]*)\"$")
    public void userPanelShouldContain(String text) {
        dashboardPage = new DashboardPage(driver);
        Assertions.assertThat(dashboardPage.getUserPanelText()).as("User Panel").contains(text);
    }

    @Then("^Add New Item button should contain text:\"([^\"]*)\"$")
    public void addNewItemVisible(String text) {
        Assertions.assertThat(itemPage.getNewItemLinkText()).as("Add Item Link").contains(text);
    }

    @Then("^Add New Expense button should contain text:\"(.*)\"$")
    public void addNewExpenseVisible(String text) {
        Assertions.assertThat(cashBoxPage.getNewExpenseButtonText()).as("Add Expense Link").contains(text);
    }

    @Then("^Add New Client button should contain text:\"([^\"]*)\"$")
    public void addNewClientVisible(String text) {
        Assertions.assertThat(clientPage.getNewClientLinkText()).as("Add Item Link").contains(text);
    }

}

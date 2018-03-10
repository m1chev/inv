package inv;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.fest.assertions.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import util.Constants;

import java.util.concurrent.TimeUnit;

public class InvStepDefinitions {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ItemPage itemPage;
    ClientPage clientPage;
    WebDriver driver;

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @After
    public void after() {
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

    @And("^I press Login button$")
    public void iPressLoginButton() {
        loginPage.pressLoginButton();
    }

    @When("^I delete all items")
    public void deleteAllItems() {
        itemPage.deleteAllItems();
    }

    @When("^I delete all clients")
    public void deleteAllClients() {
        clientPage.deleteAllClients();
    }

    @Then("^login error message with text should be displayed:\"([^\"]*)\"$")
    public void loginErrorMessage(String errorMessage) {
        loginPage.verifyMessage(errorMessage);
    }

    @Then("^item message with text should be displayed:\"([^\"]*)\"$")
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

    @Then("^Add New Client button should contain text:\"([^\"]*)\"$")
    public void addNewClientVisible(String text) {
        Assertions.assertThat(clientPage.getNewClientLinkText()).as("Add Item Link").contains(text);
    }

}

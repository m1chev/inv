package battleship;

import org.fest.assertions.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.PageAction;
import util.Pages;


public class BattleShipPage {
    private PageAction action;
    private static final int SLEEP_TIME = 0;
    private static final Logger LOGGER = LoggerFactory.getLogger(BattleShipPage.class);

    @FindBy(how = How.NAME, using = "coord")
    private WebElement coordinatesField;

    @FindBy(how = How.XPATH, using = "//input[@type='submit']")
    private WebElement submitButton;

    @FindBy(how = How.TAG_NAME, using = "pre")
    private WebElement table;

    By coordinatesLocator = By.xpath("//input[@type='submit']");
    By tableLocator = By.tagName("pre");

    public BattleShipPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        action = new PageAction(driver);
    }

    public void enterCoordinate(String coordinate) {
        action.typeText(coordinatesField, coordinate);
    }

    public void clickSubmitButton() {
       action.clickButton(submitButton);
    }

    public void gotoPage() {
        action.gotoPage(Pages.BATTLESHIPS_URL.getPath(), "");
    }

    private void sleep(int seconds) {
        try {
            LOGGER.info("Sleeping for:" + seconds + " seconds");
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getTableText() {
        return action.getText(table);
    }


    public void hitCoordinateAndCheckMessage(String coordinate){
        enterCoordinate(coordinate);
        clickSubmitButton();
        verifyMessage();
    }

    public void verifyMessage() {
        String tableText = getTableText();
        int previousHitCount = Counter.hitCount;
        int previousMissCount = Counter.missCount;
        LOGGER.info("Previous HIT Count:" + previousHitCount);
        LOGGER.info("Previous MISS Count:" + previousMissCount);
        Counter.countAll(tableText);
        if (Counter.missCount > previousMissCount) {
            Assertions.assertThat(tableText).as("MISS").containsIgnoringCase("Miss");
        } else {
            Assertions.assertThat(true).as("HIT/SUNK").isEqualTo(tableText.contains("Hit") || tableText.contains("Sunk"));
        }
    }
}

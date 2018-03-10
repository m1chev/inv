package inv;

import battleship.BattleShipPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BattleShipStepDefinitions {
    BattleShipPage battleShipPage;
    WebDriver driver;




    @Given("^user is on BattleShips Page$")
    public void user_is_on_Home_Page() {
        battleShipPage = new BattleShipPage(driver);
        battleShipPage.gotoPage();
    }
}

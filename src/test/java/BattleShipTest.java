import battleship.BattleShipPage;
import battleship.Counter;
import com.google.common.collect.Lists;
import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;


import java.util.List;

public class BattleShipTest  {
    private BattleShipPage battleShipPage;



    @Test
    public void canHitCoordinate() {
        battleShipPage.gotoPage();
        battleShipPage.enterCoordinate("B5");
        battleShipPage.clickSubmitButton();
        battleShipPage.verifyMessage();
    }

    @Test
    public void hitAllBattleShips() {
        List<String> coordinates = Lists.newArrayList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
        battleShipPage.gotoPage();
        coordinates.forEach(letter -> {
                    for (int i = 1; i <= 10; i++) {
                        battleShipPage.hitCoordinateAndCheckMessage(letter + i);
                    }
                }
        );

    }

    @Test
    public void showBattleShips() {
        battleShipPage.gotoPage();
        battleShipPage.enterCoordinate("show");
        battleShipPage.clickSubmitButton();
        Assertions.assertThat(battleShipPage.getTableText()).as("Table").contains("x");
        Counter.countAll(battleShipPage.getTableText());
        Assertions.assertThat(Counter.hitCount).as("HIT COUNT").isEqualTo(13);
    }

    @Test
    public void resetGame() {
        battleShipPage.gotoPage();
        battleShipPage.enterCoordinate("reset");
        battleShipPage.clickSubmitButton();
        Counter.countAll(battleShipPage.getTableText());
        Assertions.assertThat(Counter.hitCount).as("HIT COUNT").isEqualTo(0);
        Assertions.assertThat(Counter.missCount).as("MISS COUNT").isEqualTo(0);
    }


}

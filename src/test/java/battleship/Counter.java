package battleship;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Counter {
    private static final Logger LOGGER = LoggerFactory.getLogger(Counter.class);
    public static int missCount;
    public static int hitCount;
    public static int remainCount;



    public static int countMissed(String text){
       missCount = text.split("-").length-1;
       LOGGER.info("Missed Count:"+missCount);
       return missCount;
    }



    public static int countHit(String text){
        hitCount = text.split("x").length-1;
        LOGGER.info("Hit Count:"+hitCount);
        return hitCount;
    }

    public static int countRemaining(){
        remainCount = 100 - (missCount+hitCount);
        LOGGER.info("Remained Count:"+remainCount);
        return remainCount;
    }

    public static void countAll(String text){
        countMissed(text);
        countHit(text);
        countRemaining();
    }

}

package util;


public class Wait {

    public static void wait(int seconds) {
        try {
            PageAction.LOGGER.info("Waiting for:" + seconds + " seconds");
            Thread.sleep(seconds * 1000);

        } catch (InterruptedException e) {
            System.out.println("thread  interrupted");
        }
    }
}

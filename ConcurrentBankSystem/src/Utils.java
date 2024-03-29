import java.util.Random;

/**
 * A set of helpers to be used on another classes.
 */
public class Utils
{
    // This function will trigger a Thread.sleep() for a random amount of seconds between two float values.
    public static void sleepRandSecs(float start, float end) throws InterruptedException
    {
        start *= 1000;
        end *= 1000;
        int timeToSleep = (int) (new Random().nextInt(((int) end - (int) start) + 1) + start);
        Thread.sleep(timeToSleep);
    }

    public static void logThread(String message)
    {
        Utils.log("[THREAD] " + message);
    }

    /**
     * This will log to the Thread's state.
     *
     * @param thread The thread information.
     */
    public static void logThreadState(Thread thread)
    {
        Utils.logThread("state \"" + thread.getState().toString() + "\" for \"" + thread.getName() + "\"");
    }

    /**
     * This is used when a Thread's run() method is first called.
     *
     * @param thread The thread information.
     */
    public static void logThreadStart(Thread thread)
    {
        Utils.logThread(thread.getName() + " >> START <<");
    }

    /**
     * This is used at the end of a Thread's run() method.
     *
     * @param thread The thread information.
     */
    public static void logThreadFinish(Thread thread)
    {
        Utils.logThread(thread.getName() + " >> END <<");
    }

    public static void logInfo(String message)
    {
        Utils.log("[INFO] " + message);
    }

    public static void logWarning(String message)
    {
        Utils.log("[WARN] " + message);
    }

    public static void logError(String message)
    {
        Utils.log("[ERROR] " + message);
    }

    /**
     * Basic logging function all other methods use this.
     *
     * @param message The message to log.
     */
    private static void log(String message)
    {
        System.out.println(message);
    }

    public static void logSeparator()
    {
        System.out.println("================================================");
    }
}

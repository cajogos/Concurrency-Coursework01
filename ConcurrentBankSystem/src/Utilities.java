import java.util.Random;

public class Utilities
{
	public static void sleepForRandomAmountOfSeconds(float start, float end) throws InterruptedException
	{
		start *= 1000;
		end *= 1000;
		int timeToSleep = (int) (new Random().nextInt(((int) end - (int) start) + 1) + start);
		Thread.sleep(timeToSleep);
	}
}

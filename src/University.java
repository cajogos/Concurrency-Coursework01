import java.util.ArrayList;

public class University extends Thread
{
	// Registrations are the connections to a student's current bank account
	private ArrayList<CurrentAccount> registrations;

	public University(ThreadGroup threadGroup, String name)
	{
		super(threadGroup, name);
		this.registrations = new ArrayList<CurrentAccount>();
	}

	public void addRegistration(CurrentAccount registration)
	{
		this.registrations.add(registration);
	}

	public void run()
	{
		System.out.println(this.getName() + " will now take payment for the tuition fees.");
		int[] tuitionFees = { 1200, 1200, 1350 };

		// TODO: print out messages when actions performed
		// TODO: Make 3 appropriate course withdrawals from Rik's account and 3 from
		// Sue's account
		// TODO: Sleep for a random amount of time.

		for (int i = 0; i < tuitionFees.length; i++)
		{
			int fee = tuitionFees[i];
			System.out.println(this.getName() + " performing tuition fee of " + fee);
			for (CurrentAccount registration : registrations)
			{
				registration.withdrawal(new Transaction(this.getName(), fee));
			}
			try
			{
				Utilities.sleepForRandomAmountOfSeconds(0.5f, 3f);
			} catch (InterruptedException e)
			{
			}
		}
	}
}

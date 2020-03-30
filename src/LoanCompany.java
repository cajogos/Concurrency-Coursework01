import java.util.ArrayList;

public class LoanCompany extends Thread
{
	private final String companyName;
	private ArrayList<CurrentAccount> lenders;

	public LoanCompany(ThreadGroup threadGroup, String name)
	{
		super(threadGroup, name);
		this.companyName = name;
		this.lenders = new ArrayList<CurrentAccount>();
	}

	public String getCompanyName()
	{
		return this.companyName;
	}

	public void addLender(CurrentAccount lender)
	{
		lenders.add(lender);
	}

	public void run()
	{
		int[] depositAmounts = { 1200, 1200, 1350 };
		// TODO: Print out messages of all actions performed (starts, terminates, makes
		// deposit)
		// TODO: Perform 3 student loan deposits in each student's account.
		// TODO: Sleep for a random amount of time between each transaction
		for (int depositAmount : depositAmounts)
		{
			for (CurrentAccount lender : lenders)
			{
				lender.deposit(new Transaction(this.getName(), depositAmount));
				try
				{
					Utilities.sleepForRandomAmountOfSeconds(0.1f, 1f);
				} catch (InterruptedException e)
				{
				}
			}

		}
	}
}

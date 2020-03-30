import java.util.ArrayList;

public class LoanCompany extends Thread
{
	// Lenders are the connections to a student's current account
	private ArrayList<CurrentAccount> lenders;

	public LoanCompany(ThreadGroup threadGroup, String name)
	{
		super(threadGroup, name);
		this.lenders = new ArrayList<CurrentAccount>();
	}

	public void addLender(CurrentAccount lender)
	{
		lenders.add(lender);
	}

	public void run()
	{
		System.out.println(this.getName() + " will now perform the financing loan deposits.");
		int[] loanAmounts = { 2300, 2300, 2500 };
		for (int i = 0; i < loanAmounts.length; i++)
		{
			int loan = loanAmounts[i];
			System.out.println(this.getName() + " depositing loan " + (i + 1) + " of " + loan);
			for (CurrentAccount lender : lenders)
			{
				lender.deposit(new Transaction(this.getName(), loan));
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

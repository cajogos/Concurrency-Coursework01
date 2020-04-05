public class LoanCompany extends Thread
{
	private String companyName;
	private BankAccount riksAccount;
	private BankAccount suesAccount;

	private int[] loanAmounts = { 1800, 1800, 1900 };

	public LoanCompany(ThreadGroup threadGroup, String companyName, BankAccount riksAccount, BankAccount suesAccount)
	{
		super(threadGroup, ("Thread.LoanCompany." + companyName.replace(" ", "")));
		this.companyName = companyName;
		this.riksAccount = riksAccount;
		this.suesAccount = suesAccount;
	}

	public String getCompanyName()
	{
		return this.companyName;
	}

	public void run()
	{
		Utils.logThreadStart(this);
		for (int i = 0; i < loanAmounts.length; i++)
		{
			try
			{
				Utils.sleepRandSecs(0.5f, 3f);
			} catch (InterruptedException e)
			{
			}

			int loan = loanAmounts[i];

			Utils.logInfo(this.getName() + " depositing loan " + (i + 1) + " of " + loan);

			this.riksAccount.deposit(new Transaction(this.getCompanyName(), loan));
			this.suesAccount.deposit(new Transaction(this.getCompanyName(), loan));
		}
		Utils.logThreadFinish(this);
	}
}

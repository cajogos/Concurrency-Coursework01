public class University extends Thread
{
	private String universityName;
	private BankAccount riksAccount;
	private BankAccount suesAccount;

	private int[] tuitionFees = { 1200, 1200, 1350 };

	public University(ThreadGroup threadGroup, String universityName, BankAccount riksAccount, BankAccount suesAccount)
	{
		super(threadGroup, ("Thread.University." + universityName.replace(" ", "")));
		this.universityName = universityName;
		this.riksAccount = riksAccount;
		this.suesAccount = suesAccount;
	}

	public String getUniversityName()
	{
		return this.universityName;
	}

	public void run()
	{
		Utils.logThreadStart(this);
		for (int i = 0; i < tuitionFees.length; i++)
		{
			try
			{
				Utils.sleepRandSecs(0.5f, 3f);
			} catch (InterruptedException e)
			{
			}

			int fee = tuitionFees[i];

			Utils.logInfo(this.getUniversityName() + " performing tuition fee " + (i + 1) + " of " + fee);

			this.riksAccount.withdrawal(new Transaction(this.getUniversityName(), fee));
			this.suesAccount.withdrawal(new Transaction(this.getUniversityName(), fee));
		}
		Utils.logThreadFinish(this);
	}
}

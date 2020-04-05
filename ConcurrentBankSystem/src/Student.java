import java.util.ArrayList;

public class Student extends Thread
{
	private int studentID;
	private String studentName;
	private BankAccount currentAccount;

	/**
	 * A list of transactions to make it easier to process different transactions
	 * for different students.
	 */
	private ArrayList<SmartTransaction> transactions;

	public Student(ThreadGroup threadGroup, String studentName, int studentID, BankAccount currentAccount)
	{
		super(threadGroup, ("Thread.Student." + studentName.replace(" ", "")));
		this.studentID = studentID;
		this.studentName = studentName;
		this.currentAccount = currentAccount;

		this.transactions = new ArrayList<SmartTransaction>();
	}

	public int getStudentID()
	{
		return this.studentID;
	}

	public String getStudentName()
	{
		return this.studentName;
	}

	public BankAccount getCurrentAccount()
	{
		return this.currentAccount;
	}

	public void addWithdrawal(Transaction transaction)
	{
		this.transactions.add(SmartTransaction.newWithdrawal(transaction));
	}

	public void addDeposit(Transaction transaction)
	{
		this.transactions.add(SmartTransaction.newDeposit(transaction));
	}

	public void run()
	{
		Utils.logThreadStart(this);
		for (SmartTransaction t : this.transactions)
		{
			try
			{
				Utils.sleepRandSecs(0.5f, 3f);
			} catch (InterruptedException e)
			{
			}

			Utils.logInfo(this.getStudentName() + " performed a transaction of " + t.getTransaction().getAmount()
					+ " \"" + t.getTransaction().getCustomerID() + "\"");

			if (t.isWithdrawal())
			{
				this.currentAccount.withdrawal(t.getTransaction());
			} else if (t.isDeposit())
			{
				this.currentAccount.deposit(t.getTransaction());
			}
		}
		Utils.logThreadFinish(this);
	}
}

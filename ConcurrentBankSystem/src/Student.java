import java.util.ArrayList;

public class Student extends Thread
{
	private int studentID;
	private String studentName;
	private CurrentAccount currentAccount;

	private ArrayList<StudentTransaction> transactions;

	public Student(ThreadGroup threadGroup, String name, int studentID, CurrentAccount currentAccount)
	{
		super(threadGroup, name);
		this.studentID = studentID;
		this.studentName = name;
		this.currentAccount = currentAccount;

		this.transactions = new ArrayList<StudentTransaction>();
	}

	public int getStudentID()
	{
		return this.studentID;
	}

	public String getStudentName()
	{
		return this.studentName;
	}

	public CurrentAccount getCurrentAccount()
	{
		return this.currentAccount;
	}

	public void addWithdrawal(Transaction transaction)
	{
		this.transactions.add(new StudentTransaction(StudentTransaction.Type.WITHDRAWAL, transaction));
	}

	public void addDeposit(Transaction transaction)
	{
		this.transactions.add(new StudentTransaction(StudentTransaction.Type.DEPOSIT, transaction));
	}

	public void run()
	{
		for (StudentTransaction t : this.transactions)
		{
			System.out.println(this.getName() + " performing a transaction of " + t.transaction.getAmount());
			if (t.isWithdrawal())
			{
				this.currentAccount.withdrawal(t.transaction);
			} else if (t.isDeposit())
			{
				this.currentAccount.deposit(t.transaction);
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

// Class used to hold different transactions for each of the students
class StudentTransaction
{
	enum Type
	{
		DEPOSIT, WITHDRAWAL
	};

	public Type type;
	public Transaction transaction;

	public StudentTransaction(Type type, Transaction transaction)
	{
		this.type = type;
		this.transaction = transaction;
	}

	public boolean isWithdrawal()
	{
		return (this.type == Type.WITHDRAWAL);
	}

	public boolean isDeposit()
	{
		return (this.type == Type.DEPOSIT);
	}
}
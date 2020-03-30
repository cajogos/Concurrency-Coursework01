public class CurrentAccount implements BankAccount
{
	private final String accountHolder;
	private final int accountNumber;
	private int balance;
	private Statement statement;

	public CurrentAccount(String accountHolder, int accountNumber)
	{
		this.accountHolder = accountHolder;
		this.accountNumber = accountNumber;
		this.balance = 0;
		this.statement = new Statement(this.accountHolder, this.accountNumber);
	}

	public String getAccountHolder()
	{
		return this.accountHolder;
	}

	public int getAccountNumber()
	{
		return this.accountNumber;
	}

	public int getBalance()
	{
		return this.balance;
	}

	public synchronized void deposit(Transaction transaction)
	{
		System.out.println("[Deposit ] Account: " + this.getAccountNumber() + " - " + this.getAccountHolder()
				+ " | Amount: " + transaction.getAmount()
				+ " | From: " + transaction.getCustomerID());
		this.balance += transaction.getAmount();
		this.statement.addTransaction(transaction.getCustomerID(), transaction.getAmount(), this.balance);
		notifyAll();
	}

	public synchronized void withdrawal(Transaction transaction)
	{
		System.out.println("[Withdraw] Account: " + this.getAccountNumber() + " - " + this.getAccountHolder()
				+ " | Amount: " + transaction.getAmount()
				+ " | From: " + transaction.getCustomerID());
		while (this.balance < transaction.getAmount() || this.isOverdrawn())
		{
			System.out.println(this.getAccountHolder() + " has insufficient funds (" + this.getBalance()
					+ "), waiting for deposit...");
			try
			{
				wait();
			} catch (InterruptedException e)
			{
			}
		}
		this.balance -= transaction.getAmount();
		this.statement.addTransaction(transaction.getCustomerID(), (transaction.getAmount() * -1), this.balance);
		notifyAll();
	}

	public boolean isOverdrawn()
	{
		return (this.balance < 0);
	}

	public void printStatement()
	{
		this.statement.print();
	}

	public String toString()
	{
		return "Current Account for [" + this.getAccountHolder() + "] (ID:" + this.getAccountNumber() + ") - Balance: "
				+ this.getBalance();
	}
}

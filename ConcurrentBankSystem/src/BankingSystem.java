public class BankingSystem
{
	CurrentAccount riksAccount;
	CurrentAccount suesAccount;

	ThreadGroup studentsGroup;
	ThreadGroup financesGroup;

	private void go()
	{
		System.out.println("Starting up Banking system...");

		// Creating the bank account objects
		System.out.println("Creating the Current Bank Accounts for Rik and Sue...");
		riksAccount = new CurrentAccount("Rik", 1001);
		suesAccount = new CurrentAccount("Sue", 1002);

		// Creating the thread groups
		System.out.println("Creating the thread groups necessary...");
		studentsGroup = new ThreadGroup("students");
		financesGroup = new ThreadGroup("finances");

		// Creating the Loan Company thread
		System.out.println("Creating the Loan Company thread...");
		LoanCompany studentFinance = new LoanCompany(financesGroup, "Student Finance");
		studentFinance.addLender(riksAccount);
		studentFinance.addLender(suesAccount);

		// Creating the University thread
		System.out.println("Creating the University thread...");
		University university = new University(financesGroup, "Uni of London");
		university.addRegistration(riksAccount);
		university.addRegistration(suesAccount);
		
		// Creating the Student thread for Rik and Sue
		System.out.println("Creating the Student threads...");
		Student rik = new Student(studentsGroup, "Rik", 2001, riksAccount);
		Student sue = new Student(studentsGroup, "Sue", 2002, suesAccount);
		
		// Rik's personal transactions // TODO: A total of 6 transactions
		rik.addWithdrawal(new Transaction("Tablet Computer", 750));
		rik.addWithdrawal(new Transaction("Huts of Pizza", 56));
		rik.addWithdrawal(new Transaction("Concurrent Chicken", 20));
		rik.addDeposit(new Transaction("ATM Deposit", 100));
		rik.addWithdrawal(new Transaction("Parking Ticket", 120));
		rik.addWithdrawal(new Transaction("TV's R Us", 1200));
		
		// Sue's personal transactions // TODO: A total of 6 transactions
		sue.addDeposit(new Transaction("Scratch Card Win", 500));
		sue.addWithdrawal(new Transaction("Sainsco's Groceries", 120));
		sue.addWithdrawal(new Transaction("Concurrency Museum", 30));
		sue.addWithdrawal(new Transaction("ATM Withdrawal", 100));
		sue.addWithdrawal(new Transaction("Pharmacy", 12));
		sue.addDeposit(new Transaction("Work Inc.", 1200));
		

		// Start the threads
		rik.start();
		sue.start();
		studentFinance.start();
		university.start();

		// Wait for the threads
		try
		{
			rik.join();
			sue.join();
			studentFinance.join();
			university.join();
		} catch (InterruptedException e)
		{

		}

		// Print statements and final account's information
		this.riksAccount.printStatement();
		System.out.println(this.riksAccount.toString());

		this.suesAccount.printStatement();
		System.out.println(this.suesAccount.toString());
	}

	public static void main(String[] args)
	{
		(new BankingSystem()).go();
	}
}

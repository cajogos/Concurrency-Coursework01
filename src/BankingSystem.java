public class BankingSystem
{
	CurrentAccount riksAccount;
	CurrentAccount suesAccount;

	ThreadGroup studentsGroup;
	ThreadGroup financesGroup;

	// TODO: Print out all the actions

	private void init()
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

		// Creating the Student thread for Rik and Sue
		System.out.println("Creating the Student threads...");
		Student rik = new Student(studentsGroup, "Rik", 2001, riksAccount);
		Student sue = new Student(studentsGroup, "Sue", 2002, suesAccount);

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

		// Print statements
		this.riksAccount.printStatement();
		System.out.println(this.riksAccount.toString());
		this.suesAccount.printStatement();
		System.out.println(this.suesAccount.toString());
	}

	public static void main(String[] args)
	{
		BankingSystem system = new BankingSystem();
		system.init();
	}
}

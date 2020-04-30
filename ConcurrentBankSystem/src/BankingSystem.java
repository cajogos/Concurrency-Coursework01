public class BankingSystem
{
    CurrentAccount riksAccount;
    CurrentAccount suesAccount;

    ThreadGroup studentsGroup;
    ThreadGroup financesGroup;

    public static void main(String[] args)
    {
        Utils.logSeparator();
        Utils.logInfo("Starting up Banking System...");

        // Create new instance of BankingSystem and start it up!
        (new BankingSystem()).go();

        Utils.logInfo("All done!");
        Utils.logSeparator();
    }

    private void go()
    {
        // Creating the bank account objects
        Utils.logInfo("Creating the Current Bank Accounts for Rik and Sue...");
        riksAccount = new CurrentAccount("Rik", 1001);
        suesAccount = new CurrentAccount("Sue", 1002);

        // Creating the thread groups
        Utils.logInfo("Creating the thread groups necessary...");
        studentsGroup = new ThreadGroup("students");
        financesGroup = new ThreadGroup("finances");

        // Creating the Loan Company thread
        Utils.logInfo("Creating the Loan Company thread...");
        LoanCompany studentFinance = new LoanCompany(financesGroup, "Student Finance", riksAccount, suesAccount);

        // Creating the University thread
        Utils.logInfo("Creating the University thread...");
        University university = new University(financesGroup, "Uni of London", riksAccount, suesAccount);

        // Creating the Student threads for Rik and Sue
        Utils.logInfo("Creating the Student threads...");
        Student rik = new Student(studentsGroup, "Rik", 2001, riksAccount);
        Student sue = new Student(studentsGroup, "Sue", 2002, suesAccount);

        // Rik's personal transactions
        rik.addWithdrawal(new Transaction("Tablet Computer", 750));
        rik.addWithdrawal(new Transaction("Huts of Pizza", 56));
        rik.addWithdrawal(new Transaction("Concurrent Chicken", 20));
        rik.addDeposit(new Transaction("ATM Deposit", 100));
        rik.addWithdrawal(new Transaction("Parking Ticket", 120));
        rik.addWithdrawal(new Transaction("TV's R Us", 800));

        // Sue's personal transactions
        sue.addDeposit(new Transaction("Scratch Card Win", 500));
        sue.addWithdrawal(new Transaction("Sainsco's Groceries", 120));
        sue.addWithdrawal(new Transaction("Concurrency Museum", 30));
        sue.addWithdrawal(new Transaction("ATM Withdrawal", 100));
        sue.addWithdrawal(new Transaction("Pharmacy", 12));
        sue.addDeposit(new Transaction("Work Inc.", 1200));

        // Threads in NEW state
        Utils.logSeparator();
        Utils.logThreadState(rik);
        Utils.logThreadState(sue);
        Utils.logThreadState(studentFinance);
        Utils.logThreadState(university);
        Utils.logSeparator();

        // Start the threads
        rik.start();
        sue.start();
        studentFinance.start();
        university.start();

        // Threads in RUNNABLE (or BLOCKED) state
        Utils.logSeparator();
        Utils.logThreadState(rik);
        Utils.logThreadState(sue);
        Utils.logThreadState(studentFinance);
        Utils.logThreadState(university);
        Utils.logSeparator();

        // Wait for the threads
        try
        {
            rik.join();
            sue.join();
            studentFinance.join();
            university.join();
        }
        catch (InterruptedException ignored)
        {
        }

        // Threads in TERMINATED state
        Utils.logSeparator();
        Utils.logThreadState(rik);
        Utils.logThreadState(sue);
        Utils.logThreadState(studentFinance);
        Utils.logThreadState(university);
        Utils.logSeparator();

        // Print statements and final account's information
        riksAccount.printStatement();
        riksAccount.printAccountInformation();
        suesAccount.printStatement();
        suesAccount.printAccountInformation();
    }
}

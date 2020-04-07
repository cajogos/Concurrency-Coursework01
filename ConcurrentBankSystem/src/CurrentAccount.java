public class CurrentAccount implements BankAccount
{
    enum TransactionType
    {
        WITHDRAWAL, DEPOSIT
    }

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

    public synchronized String getAccountHolder()
    {
        return this.accountHolder;
    }

    public synchronized int getAccountNumber()
    {
        return this.accountNumber;
    }

    public synchronized int getBalance()
    {
        return this.balance;
    }

    public synchronized boolean isOverdrawn()
    {
        return (this.balance < 0);
    }

    public synchronized void deposit(Transaction transaction)
    {
        this.logTransaction(TransactionType.DEPOSIT, transaction);

        this.balance += transaction.getAmount();
        this.statement.addTransaction(transaction.getCustomerID(), transaction.getAmount(), this.balance);

        notifyAll();
    }

    public synchronized void withdrawal(Transaction transaction)
    {
        int tries = 0;
        int maxTries = 3;
        while (this.balance < transaction.getAmount() || this.isOverdrawn())
        {
            Utils.logWarning(this.getAccountHolder() + " has insufficient funds! (BAL: " + this.getBalance()
                    + ") Waiting for deposit...");
            try
            {
                wait(5000); // Wait for a max of 5 seconds
            }
            catch (InterruptedException e)
            {
            }

            /**
             * To avoid deadlock a timeout has been set on the wait for this thread.
             * Whenever a bank account does not get enough deposits in time to process all
             * the withdrawal transactions. A failover has been implemented to log the error
             * and ignore the transaction after a given max number of tries.
             */
            ++tries;
            if (tries >= maxTries)
            {
                Utils.logError("Failed to process withdrawal after " + maxTries + " tries " + this.getAccountHolder()
                        + " funds were never available...");
                break;
            }
        }

        if (this.balance >= transaction.getAmount())
        {
            this.logTransaction(TransactionType.WITHDRAWAL, transaction);

            this.balance -= transaction.getAmount();
            this.statement.addTransaction(transaction.getCustomerID(), (transaction.getAmount() * -1), this.balance);
        }

        notifyAll();
    }

    private void logTransaction(TransactionType type, Transaction transaction)
    {
        String message = (type == TransactionType.DEPOSIT) ? "[ DEPOSIT  ]" : "[WITHDRAWAL]";
        message += " ACC: " + this.getAccountNumber() + " [" + this.getAccountHolder() + "] | Val: "
                + transaction.getAmount() + "\t| \"" + transaction.getCustomerID() + "\"";
        System.out.println(message);
    }

    public synchronized void printStatement()
    {
        this.statement.print();
    }

    public synchronized void printAccountInformation()
    {
        Utils.logSeparator();
        Utils.logInfo(this.toString());
        Utils.logSeparator();
    }

    public synchronized String toString()
    {
        return "Bank Account [" + this.getAccountHolder() + "] (ID:" + this.getAccountNumber() + ") - BAL: "
                + this.getBalance();
    }
}

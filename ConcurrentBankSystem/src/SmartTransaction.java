/**
 * Class that is able to hold the type of transaction and its information.
 */
public class SmartTransaction
{
    private Transaction transaction;
    private CurrentAccount.TransactionType type;

    /**
     * Only allow the creation of these transactions via static methods.
     *
     * @param type
     * @param transaction
     */
    private SmartTransaction(CurrentAccount.TransactionType type, Transaction transaction)
    {
        this.type = type;
        this.transaction = transaction;
    }

    public Transaction getTransaction()
    {
        return this.transaction;
    }

    public boolean isWithdrawal()
    {
        return (this.type == CurrentAccount.TransactionType.WITHDRAWAL);
    }

    public boolean isDeposit()
    {
        return (this.type == CurrentAccount.TransactionType.DEPOSIT);
    }

    public static SmartTransaction newWithdrawal(Transaction transaction)
    {
        return new SmartTransaction(CurrentAccount.TransactionType.WITHDRAWAL, transaction);
    }

    public static SmartTransaction newDeposit(Transaction transaction)
    {
        return new SmartTransaction(CurrentAccount.TransactionType.DEPOSIT, transaction);
    }

}
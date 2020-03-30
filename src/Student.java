public class Student extends Thread
{
	private int studentID;
	private String studentName;
	private CurrentAccount currentAccount;

	public Student(ThreadGroup threadGroup, String name, int studentID, CurrentAccount currentAccount)
	{
		super(threadGroup, name);
		this.studentID = studentID;
		this.studentName = name;
		this.currentAccount = currentAccount;
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
	
	public void run()
	{
		// TODO: Perform series of transactions on bank account
		// TODO: Print messages on all actions performed
		// TODO: Perform 6 different transactions
		// TODO: Sleep a random amount of time
		System.out.println("I am a thread!!!" + this.getName());
	}
}

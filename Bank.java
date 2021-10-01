import java.util.Scanner;

public class Bank {
    static String getNumericString(int n)
	{

		// chose an integer randomly from this String
		String NumericString = "0123456789";

		// create StringBuffer size of ID
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index
				= (int)(NumericString.length()
						* Math.random());

			// add Character one by one in end of sb
			sb.append(NumericString
						.charAt(index));
		}

		return sb.toString();
	}
    // Entry point of program
    public static void main(String [] args) {

        Scanner keyboard = new Scanner(System.in);
        
        // Create array of Accounts
        Account accounts [] = new Account[10];
        int numAccounts = 0; 

        int choice;

        do {
            choice = menu(keyboard);
            System.out.println();
            
            if(choice == 1) {
                accounts[numAccounts++] = createAccount(keyboard);
            } else if(choice == 2) {
                doDeposit(accounts, numAccounts, keyboard);
            } else if(choice == 3) {
                doWithdraw(accounts, numAccounts, keyboard);
            } else if(choice == 4) {
                applyInterest1(accounts, numAccounts, keyboard);
            } else if(choice == 5) {
                applyInterest2(accounts, numAccounts, keyboard);
            } 
            else {
                System.out.println("Thank you for using our bank!");
            }
            System.out.println();
        } while(choice != 7);
    }

    /**
     * Account choice
     * 
     * @param keyboard 
     * @return choice
     */
    public static int accountMenu(Scanner keyboard) {
        System.out.println("Select Account Type");
        System.out.println("1. Checking Account");
        System.out.println("2. Savings Account");
        System.out.println("3. College Savings Account");

        int choice;
        do {
            System.out.print("Enter choice: ");
            choice = keyboard.nextInt();
        }while(choice < 1 || choice > 3);
        
        return choice;
    } 

    public static int searchAccount(Account accounts [], int count, int accountNumber) {

        for(int i=0; i<count; i++) {
            if(accounts[i].getAccountNumber() == accountNumber) {
                return i;
            }
        }

        return -1; 
    }

    /**
     * Function to perform Deposit on a selected account
     */
    public static void doDeposit(Account accounts [], int count, Scanner keyboard) {
        // Get the account number
        System.out.print("\nPlease enter account number: ");
        int accountNumber = keyboard.nextInt();

        // search for account
        int index = searchAccount(accounts, count, accountNumber);

        if(index >= 0) {
            // Amount
            System.out.print("Please enter Deposit Amount: ");
            double amount = keyboard.nextDouble();

            accounts[index].deposit(amount);
        } else {
            System.out.println("No account exist with AccountNumber: " + accountNumber);
        }
    }
    /**
     * Function to perform withdrawal on a selected account
     */
    public static void doWithdraw(Account accounts [], int count, Scanner keyboard) {
        // Get the account number
        System.out.print("\nPlease enter account number: ");
        int accountNumber = keyboard.nextInt();

        // search for account
        int index = searchAccount(accounts, count, accountNumber);

        if(index >= 0) {
            // Amount
            System.out.print("Please enter Withdraw Amount: ");
            double amount = keyboard.nextDouble();
            accounts[index].withdraw(amount);
        } else {
            System.out.println("No account exist with AccountNumber: " + accountNumber);
        }
    }
    /**
     * Function to Apply Interest on Savings Account
     */
    public static void applyInterest1(Account accounts [], int count, Scanner keyboard) {
        // Get the account number
        System.out.print("\nPlease enter account number: ");
        int accountNumber = keyboard.nextInt();

        // search for account
        int index = searchAccount(accounts, count, accountNumber);

        if(index >= 0) {
            
            // must be instance of savings account
            if(accounts[index] instanceof SavingsAccount) {
                ((SavingsAccount)accounts[index]).applyInterest1();
            }
        } else 
        {
            System.out.println("No account exist with AccountNumber: " + accountNumber);
        }
        }
        
    /**
     * Function to Apply Interest on Checking Account
     */
    public static void applyInterest2(Account accounts [], int count, Scanner keyboard) {
        // Get the account number
        System.out.print("\nPlease enter account number: ");
        int accountNumber = keyboard.nextInt();

        // search for account
        int index = searchAccount(accounts, count, accountNumber);

        if(index >= 0) {
            
            // must be instance of checking account
            if(accounts[index] instanceof CheckingAccount) {
                ((CheckingAccount)accounts[index]).applyInterest2();
            }
        } else 
        {
            System.out.println("No account exist with AccountNumber: " + accountNumber);
        }
        }    
    

    /**
     * Function to create a new Account
     */
    public static Account createAccount(Scanner keyboard) {

        Account account = null; 
        int choice = accountMenu(keyboard);

        int accountNumber;
        System.out.print("Enter Account Number: ");
        accountNumber = keyboard.nextInt();


        if(choice == 1)  { // checking account
            
            System.out.print("KEY IN 0 and press enter to continue: ");
            double ir = keyboard.nextDouble();
            account = new CheckingAccount(accountNumber, ir);
        } else if(choice == 2) { // Savings account
            
            System.out.print("KEY IN 0 and press enter to continue: ");
            double ir = keyboard.nextDouble();
            account = new SavingsAccount(accountNumber, ir);
        } else if(choice == 3) { // College Savings account
            
            System.out.print("Proceed to Deposit Money to your College Savings Account");
            
        }
        return account;
    }

    /**
     * Menu to display options and get the user's selection
     * 
     * @param keyboard
     * @return choice
     */
    public static int menu(Scanner keyboard) {
        System.out.println("Bank Account Menu");
        System.out.println("1. Create New Account");
        System.out.println("2. Deposit Funds");
        System.out.println("3. Withdraw Funds");
        System.out.println("4. Apply Interest for Savings Account");
        System.out.println("5. Apply Interest for Checking Account");
        System.out.println("6. Withdraw from College Savings Account");
        System.out.println("7. Quit");

        int choice;

        do {
            System.out.print("Enter choice: ");
            choice = keyboard.nextInt();
        } while(choice < 1 || choice > 7);

        return choice;
    }    
}
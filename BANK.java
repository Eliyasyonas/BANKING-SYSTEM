import java.util.Scanner;

/* =========================
   ABSTRACT BASE CLASS
   ========================= */
abstract class BankAccount {

    protected String username;
    protected String accountNumber;
    protected double balance;

    public BankAccount(String username, String accountNumber, double balance) {
        this.username = username;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // abstract methods (must be implemented)
    public abstract void withdraw(double amount);
    public abstract void interest();

    // shared method (inherited by all subclasses)
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
        } else if (amount < 10) {
            System.out.println("Minimum deposit is 10.");
        } else {
            balance += amount;
            System.out.println("Deposit successful. New balance: " + balance);
        }
    }

    public void checkBalance() {
        System.out.println("Current balance: " + balance);
    }
}

/* =========================
   SAVINGS ACCOUNT
   ========================= */
class SavingsAccount extends BankAccount {

    public SavingsAccount(String u, String a, double b) {
        super(u, a, b);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
        } else if (amount > 10000) {
            System.out.println("Daily limit exceeded.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        }
    }

    @Override
    public void interest() {
        balance += balance * 0.05;
        System.out.println("Interest added. New balance: " + balance);
    }
}

/* =========================
   CURRENT ACCOUNT
   ========================= */
class CurrentAccount extends BankAccount {

    public CurrentAccount(String u, String a, double b) {
        super(u, a, b);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
        } else if (amount > 5000) {
            System.out.println("Daily limit exceeded.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        }
    }

    @Override
    public void interest() {
        balance += balance * 0.02;
        System.out.println("Interest added. New balance: " + balance);
    }
}

/* =========================
   MAIN SYSTEM CONTROLLER
   ========================= */
 class BankSystem {

    static String name;
    static int password;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("===== WELCOME TO JAVA BANK SYSTEM =====");

        // LOGIN INPUT
        System.out.print("Enter username: ");
        name = input.nextLine();

        System.out.print("Enter password: ");
        password = input.nextInt();

        // AUTHENTICATION
        if (accountCheck()) {
            System.out.println("Login successful.");
            startSystem(input);
        } else {
            System.out.println("Login failed.");
        }

        input.close(); // ONLY ONCE (VERY IMPORTANT)
    }

    // LOGIN CHECK
    static boolean accountCheck() {
        return name.equalsIgnoreCase("admin") && password == 1234;
    }

    // SYSTEM FLOW CONTROLLER
    static void startSystem(Scanner input) {

        System.out.println("\nSelect Account Type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");

        int type = input.nextInt();

        BankAccount account;

        if (type == 1) {
            account = new SavingsAccount("admin", "SAV001", 1000);
        } else {
            account = new CurrentAccount("admin", "CUR001", 1000);
        }

        int choice;

        // MAIN MENU LOOP (FINITE → algorithm property)
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Interest");
            System.out.println("5. Exit");

            choice = input.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter amount: ");
                    account.withdraw(input.nextDouble());
                    break;

                case 2:
                    System.out.print("Enter amount: ");
                    account.deposit(input.nextDouble());
                    break;

                case 3:
                    account.checkBalance();
                    break;

                case 4:
                    account.interest();
                    break;

                case 5:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);
    }
}
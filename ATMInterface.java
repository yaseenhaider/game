import java.util.Scanner;

// ATM Account
class Account {
    private String accountHolder;
    private int pin;
    private double balance;

    public Account(String accountHolder, int pin, double balance) {
        this.accountHolder = accountHolder;
        this.pin = pin;
        this.balance = balance;
    }

    public boolean validatePin(int enteredPin) {
        return this.pin == enteredPin;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Invalid withdraw amount or Insufficient balance!");
        }
    }

    public void checkBalance() {
        System.out.println("Current Balance: $" + balance);
    }

    public String getAccountHolder() {
        return accountHolder;
    }
}


class ATM {
    private Account account;

    public ATM(Account account) {
        this.account = account;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your PIN: ");
        int enteredPin = sc.nextInt();

        if (account.validatePin(enteredPin)) {
            System.out.println("Welcome " + account.getAccountHolder() + "!");
            int choice;
            do {
                System.out.println("\n--- ATM Menu ---");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        account.checkBalance();
                        break;
                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = sc.nextDouble();
                        account.deposit(depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = sc.nextDouble();
                        account.withdraw(withdrawAmount);
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice, try again.");
                }
            } while (choice != 4);
        } else {
            System.out.println("Incorrect PIN. Access Denied.");
        }

        sc.close();
    }
}

      // Main
public class ATMInterface {
    public static void main(String[] args) {

        Account myAccount = new Account("Yaseen Haider", 5843, 5000000000.0);


        ATM atm = new ATM(myAccount);

        atm.start();
    }
}  

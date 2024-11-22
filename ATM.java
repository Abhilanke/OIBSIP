package intership;

import java.util.ArrayList;
import java.util.Scanner;

class User {
    private String userId;
    private String userPin;
    private double balance;
    private ArrayList<String> transactionHistory;

    public User(String userId, String userPin, double balance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPin() {
        return userPin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    public void printTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}

// ATMOperations class to perform ATM functionalities
class ATMOperations {
    public static void deposit(User user, double amount) {
        if (amount > 0) {
            user.setBalance(user.getBalance() + amount);
            user.addTransaction("Deposited: Rs " + amount);
            System.out.println("Successfully deposited Rs " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public static void withdraw(User user, double amount) {
        if (amount > 0 && amount <= user.getBalance()) {
            user.setBalance(user.getBalance() - amount);
            user.addTransaction("Withdrew: Rs " + amount);
            System.out.println("Successfully withdrew Rs " + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public static void transfer(User user, User receiver, double amount) {
        if (amount > 0 && amount <= user.getBalance()) {
            user.setBalance(user.getBalance() - amount);
            receiver.setBalance(receiver.getBalance() + amount);
            user.addTransaction("Transferred: Rs " + amount + " to User ID: " + receiver.getUserId());
            receiver.addTransaction("Received: Rs " + amount + " from User ID: " + user.getUserId());
            System.out.println("Successfully transferred Rs " + amount);
        } else {
            System.out.println("Transfer failed. Check balance or amount.");
        }
    }

    public static void printTransactionHistory(User user) {
        System.out.println("Transaction History:");
        user.printTransactionHistory();
    }
}

class ATMInterface {
    private User currentUser;

    public void start() {
        Scanner scanner = new Scanner(System.in);

        User user1 = new User("12345", "1234", 10000.00);
        User user2 = new User("54321", "1234", 5000.00);

        // Authenticate user
        System.out.println("Welcome to the ATM!");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String userPin = scanner.nextLine();

        if (authenticate(userId, userPin, user1)) {
            currentUser = user1;
        } else if (authenticate(userId, userPin, user2)) {
            currentUser = user2;
        } else {
            System.out.println("Invalid credentials. Exiting...");
            return;
        }

        // Main menu
        while (true) {
            System.out.println("\nATM Main Menu:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    ATMOperations.printTransactionHistory(currentUser);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    ATMOperations.withdraw(currentUser, withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    ATMOperations.deposit(currentUser, depositAmount);
                    break;
                case 4:
                    System.out.print("Enter receiver User ID: ");
                    scanner.nextLine(); // consume newline
                    String receiverId = scanner.nextLine();
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    if (receiverId.equals(user1.getUserId())) {
                        ATMOperations.transfer(currentUser, user1, transferAmount);
                    } else if (receiverId.equals(user2.getUserId())) {
                        ATMOperations.transfer(currentUser, user2, transferAmount);
                    } else {
                        System.out.println("Receiver not found.");
                    }
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private boolean authenticate(String userId, String userPin, User user) {
        return user.getUserId().equals(userId) && user.getUserPin().equals(userPin);
    }
}

public class ATM {
    public static void main(String[] args) {
        ATMInterface atmInterface = new ATMInterface();
        atmInterface.start();
    }
}


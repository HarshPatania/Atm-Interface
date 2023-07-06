import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static int userId = 5846;
    private static int userPin = 5846;
    private static int noTrial = 3;
    private static int totalBalance = 500000;
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<String> transactionHistory = new ArrayList<>();
    private static String transactionStr;
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static Date date = new Date();

    public static void addToHistory(String str) {
        transactionHistory.add(str);
    }

    public static void showTransactionHistory() {
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
        System.out.println();
    }

    public static void checkUserPinAndId() {
        if (noTrial >= 0) {
            System.out.print("Your user ID is: ");
            int inputUserId = scanner.nextInt();

            if (userId == inputUserId) {
                System.out.print("Type your user PIN here: ");
                int inputUserPin = scanner.nextInt();
                if (userPin == inputUserPin) {
                    mainMenu();
                } else {
                    System.out.println("Entering the wrong PIN.");
                    noTrial--;
                    checkUserPinAndId();
                }
            } else {
                System.out.println("user ID entered is incorrect.");
                noTrial--;
                checkUserPinAndId();
            }
        } else {
            System.out.println("Your access has been denied. Please stop by the neighbourhood bank.");
        }
    }

    public static void withdraw() {
        System.out.print("Type the withdrawal amount: ");
        int withdrawalAmount = scanner.nextInt();
        System.out.print("Type your user PIN here: ");
        int inputUserPin = scanner.nextInt();
        if (inputUserPin == userPin) {
            if (withdrawalAmount < totalBalance - 2000) {
                totalBalance -= withdrawalAmount;
                System.out.println("WITHDRAWAL SUCCESSFUL");
                System.out.println("Remaining balance: '" + totalBalance + "'");
                transactionStr = formatter.format(date) + " - WITHDRAW - '" + withdrawalAmount + "' - '" + totalBalance + "'";
                addToHistory(transactionStr);
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Incorrect PIN entered.");
        }

    }

    public static void deposit() {
        System.out.println("Enter the amount you want to deposit: ");
        int depositAmount = scanner.nextInt();
        totalBalance += depositAmount;
        System.out.println("DEPOSIT SUCCESSFUL");
        System.out.println("Total balance: '" + totalBalance + "'");

        transactionStr = formatter.format(date) + " - DEPOSIT - '" + depositAmount + "' - '" + totalBalance + "'";
        addToHistory(transactionStr);
    }

    public static void transfer() {
        System.out.print("Enter the user ID of the recipient of the transfer here: ");
        int transferUserId = scanner.nextInt();
        System.out.println("Enter the amount to transfer: ");
        int transferAmount = scanner.nextInt();
        totalBalance -= transferAmount;
        System.out.println("TRANSFER SUCCESSFUL");
        System.out.println("Money transferred to user ID: " + transferUserId);
        System.out.println("Transfer amount: " + transferAmount);
        System.out.println("Total remaining amount: " + totalBalance);

        transactionStr = formatter.format(date) + " - TRANSFER - to User ID: " + transferUserId + " - '" + transferAmount + "' - '" + totalBalance + "'";
        addToHistory(transactionStr);
    }

    public static void mainMenu() {
        boolean run = true;
        while (run) {
            int event;
            System.out.println("1)WITHDRAWAL");
            System.out.println("2)DEPOSIT");
            System.out.println("3)TRANSFER");
            System.out.println("4)TRANSACTION HISTORY");
            System.out.println("5)QUIT");
            event = scanner.nextInt();
            switch (event) {
                case 1: {
                    withdraw();
                    break;
                }
                case 2: {
                    deposit();
                    break;
                }
                case 3: {
                    transfer();
                    break;
                }
                case 4: {
                    showTransactionHistory();
                    break;
                }
                case 5: {
                    run = false;
                    break;
                }
                default: {
                    System.out.println("Please Select Right Option.");
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome Sir/Madam");
        checkUserPinAndId();
    }
}

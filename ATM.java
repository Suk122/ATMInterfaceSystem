import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

// Class 1
class
        Account {
    int accNumber;
    String name;
    double balance;
    int pin;

    ArrayList<String> History = new ArrayList<>();
}

// Class 2 (Main class)
public class ATM {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Create list
        ArrayList<Account> accounts = new ArrayList<>();

        // Add user 1
        Account a1 = new Account();
        a1.accNumber = 101;
        a1.name = "Rahul";
        a1.balance = 1000;
        a1.pin = 1234;

        // Add user 2
        Account a2 = new Account();
        a2.accNumber = 102;
        a2.name = "Amit";
        a2.balance = 2000;
        a2.pin = 1111;

        accounts.add(a1);
        accounts.add(a2);

        while (true) {
            System.out.println("\n--- ATM SYSTEM ---");
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Exit");

            int option = sc.nextInt();
            if (option == 1) {
                System.out.print("Enter Account Number: ");
                int accNo = sc.nextInt();

                System.out.print("Enter PIN: ");
                int pin = sc.nextInt();

                Account currentUser = null;

                for (Account acc : accounts) {
                    if (acc.accNumber == accNo && acc.pin == pin) {
                        currentUser = acc;
                        break;
                    }
                }

                if (currentUser == null) {
                    System.out.println("Invalid login");
                    return;
                }

                int choice;

                do {
                    System.out.println("\n--- ATM MENU ---");
                    System.out.println("1. Check Balance");
                    System.out.println("2. Deposit");
                    System.out.println("3. Withdraw");
                    System.out.println("4. Change Pin");
                    System.out.println("5. Transaction History");
                    System.out.println("6.Logout");

                    choice = sc.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.println("Your balance is: Rs." + currentUser.balance);
                            break;

                        case 2:
                            System.out.println("Enter amount: Rs." ); //Ask first
                            double amount = sc.nextDouble();  //take input
                            currentUser.balance += amount;

                            currentUser.History.add("Deposited: " + amount);
                            System.out.println("Amount Deposited Successfully");
                            break;

                        case 3:
                            System.out.println("Enter amount");
                            double amt = sc.nextDouble();
                            if (amt <= currentUser.balance) {
                                currentUser.balance -= amt;
                                currentUser.History.add("Withdrawn: " + amt);
                                System.out.println("Withdrawal Successful");
                            } else {
                                System.out.println("Insufficient Balance");
                            }
                            break;

                        case 4:
                            System.out.print("Enter old PIN: ");
                            int oldPin = sc.nextInt();

                            if (oldPin == currentUser.pin) {
                            System.out.print("Enter new PIN: ");
                            currentUser.pin = sc.nextInt();
                            System.out.println("PIN changed successfully");
                            } else {
                                System.out.println("Incorrect old PIN!");
                            }
                            break;

                        case 5:
                            if(currentUser.History.isEmpty()) {
                                System.out.println("No Transaction yet");
                            }else{
                                for(String h: currentUser.History){
                                    System.out.println(h);
                                }
                            }
                            break;

                        case 6:
                                System.out.println("Logged out");
                                break;

                        default:
                            System.out.println("Invalid choice");
                    }

                } while (choice != 6);
            } else if (option == 2) {

                Account newAcc = new Account();

                System.out.print("Enter Account Number: ");
                newAcc.accNumber = sc.nextInt();

                System.out.print("Enter Name: ");
                newAcc.name = sc.next();

                System.out.print("Enter Initial Balance: ");
                newAcc.balance = sc.nextDouble();

                System.out.print("Set PIN: ");
                newAcc.pin = sc.nextInt();

                accounts.add(newAcc);

                System.out.println("Account created successfully!");
            } else if (option == 3) {
                System.out.println("Thank you for using ATM");
                break;
            } else {
                System.out.println("Invalid option");
            }
        }
    }
}
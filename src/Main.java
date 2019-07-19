import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Atm atm = Atm.getInstance();
        User user = null;
        String fileName = "user.txt";

        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to ATM");
        while (true) {
            System.out.print("Please enter your card number: ");
            String cardNumber = in.nextLine();
            user = loadUser(fileName);

            try {
                user.enterCreditCard(atm, cardNumber);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                saveData(fileName, user, atm);
                return;
            }
        }

        menu: while (true) {
            System.out.flush();
            System.out.println("########################");
            System.out.println("What do you want to do?");
            System.out.println("1.Check current balance");
            System.out.println("2.Make deposit");
            System.out.println("3.Withdraw");
            System.out.println("4.Exit");
            String choice = in.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Your balance: " + user.checkBalance());
                    break;
                case "2":
                    while(true) {
                        System.out.println("Enter amount to deposit: ");
                        Double depositAmount = Double.parseDouble(in.nextLine());
                        if (depositAmount == 0){
                            System.out.println("Invalid number!");
                            continue;
                        }
                        else {
                            user.deposit(depositAmount);
                            break;
                        }
                    }
                    break;
                case "3":
                    while(true) {
                        System.out.println("Enter amount to withdraw: ");
                        Double withdrawalAmount = Double.parseDouble(in.nextLine());
                        if (withdrawalAmount == 0){
                            System.out.println("Invalid number!");
                            continue;
                        }
                        else {
                            user.withdraw(withdrawalAmount);
                            break;
                        }
                    }
                    break;
                case "4":
                    break menu;
                default:
                    System.out.println("No such a choice");
                    break;
            }
        }

        saveData(fileName, user, atm);
    }

    public static User loadUser(String fileName) {
        CreditCard creditCard = null;
        String userName = null;
        try(FileReader reader = new FileReader(fileName))
        {
            Scanner fileScanner = new Scanner(reader);
            var value = fileScanner.nextLine().split(" ");
            userName = value[0];
            creditCard = new CreditCard(
                    value[1], // Card number
                    value[2], // PIN
                    Double.parseDouble(value[3]), // Balance
                    Boolean.parseBoolean(value[4])); // Is card blocked
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new User(userName, creditCard);
    }

    public static void saveData(String fileName, User user, Atm atm) {
        try(FileWriter writer = new FileWriter(fileName, false))
        {
            writer.write(user.toString());
        }
        catch(IOException exc){
            System.out.println(exc.getMessage());
        }

        try(FileWriter writer = new FileWriter("AtmBalance.txt")){
            writer.write(atm.getBalance().toString());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

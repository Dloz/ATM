import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Atm {
    private static Atm instance; // Let's suppose that we have only one ATM
    private CreditCard currentCreditCard; // Credit card that is being processed with ATM
    private Double balance;

    private Atm(){
        currentCreditCard = null;
        balance = getBalanceFromFile("AtmBalance.txt");
    }

    public Double getBalance() {
        return balance;
    }

    public static Atm getInstance() {
        if (instance == null) {
            instance = new Atm();
        }
        return instance;
    }

    public void enterCreditCard(CreditCard creditCard) throws Exception {
        if (creditCard.isBlocked()) {
            throw new Exception("Your card is blocked!");
        }

        currentCreditCard = creditCard;
        Scanner in = new Scanner(System.in);
        Integer tryPin = 0;
        while (tryPin < 3) {
            System.out.print("Please enter your PIN: ");
            String pin = in.nextLine();
            if (creditCard.login(pin)) {
                System.out.println("Success!");
                return;
            }
            else {
                System.out.println("Invalid PIN!");
                tryPin++;
                continue;
            }
        }
        currentCreditCard.block();
        throw new Exception("Your card has blocked!");
    }

    public void withdraw(Double amount) {
        try {
            currentCreditCard.withdraw(amount);
            reduceBalance(amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Double checkBalance(){
        return currentCreditCard.getBalance();
    }

    public void deposit(Double amount) {
        try {
            currentCreditCard.deposit(amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void reduceBalance(Double amount) throws Exception {
        if (this.balance - amount < 0)
            throw new Exception("Withdrawal amount more than balance");
        else
            this.balance -= amount;
    }

    private Double getBalanceFromFile(String fileName) {
        Double balance = 0.0;
        try(FileReader fr = new FileReader(fileName)) {
            Scanner scan = new Scanner(fr);
            while(scan.hasNextDouble())
                balance = scan.nextDouble();
        }
        catch (IOException e) {

        }
        return balance;
    }
}

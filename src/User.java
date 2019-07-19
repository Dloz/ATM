public class User{
    private String userName;
    private CreditCard creditCard;
    private Atm currentAtm;

    public User(String userName, CreditCard creditCard){
        this.userName = userName;
        this.creditCard = creditCard;
    }

    public void enterCreditCard(Atm atm, String cardNumber) throws Exception {
        if (cardNumber.equals(this.creditCard.getNumber())) {
            currentAtm = atm;
            currentAtm.enterCreditCard(this.creditCard);
        }
        else
            throw new Exception("Wrong card number!");
    }

    public void withdraw(Double amount) {
        currentAtm.withdraw(amount);
    }

    public void deposit(Double amount) {
        currentAtm.deposit(amount);
    }

    public Double checkBalance() {
        return currentAtm.checkBalance();
    }

    @Override
    public String toString() {
        return userName + " " + creditCard.toString();
    }
}

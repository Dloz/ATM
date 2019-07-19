public class CreditCard{
    private String number; // Credit card number must be in format “ХХХХ-ХХХХ-ХХХХ-ХХХХ”
    private String pin; // PIN have 4 digits
    private Double balance;
    private boolean isBlocked;

    public boolean isBlocked() {
        return isBlocked;
    }


    public CreditCard(String number, String pin, Double balance, boolean isBlocked) throws Exception {
        setNumber(number);
        setPin(pin);
        this.isBlocked = isBlocked;
        this.balance = balance;
    }

    private void setPin(String pin) throws Exception {
        if(isValidPin(pin)) {
            this.pin = pin;
        } else {
            throw new Exception("Invalid PIN");
        }
    }

    public String getNumber() {
        return number;
    }
    private void setNumber(String cardNumber) throws Exception {
        if(isValidCardNumber(cardNumber)) {
            this.number = cardNumber;
        } else {
            throw new Exception("Invalid card number");
        }
    }

    public Double getBalance(){
        return this.balance;
    }

    public void deposit(Double amount) throws Exception {
        if (this.balance + amount > 1000000)
            throw new Exception("The limit of card reached");
        else
            this.balance += amount;
    }

    public void withdraw(Double amount) throws Exception {
        if (this.balance - amount < 0)
            throw new Exception("Withdrawal amount more than balance");
        else
            balance -= amount;
    }

    public void block(){
        this.isBlocked = true;
    }

    private boolean isValidPin(String pin){
        return CreditCardValidator.isValidPin(pin);
    }

    private boolean isValidCardNumber(String cardNumber){
        return CreditCardValidator.isValidCardNumber(cardNumber);
    }

    public boolean login(String pin){
        return this.pin.equals(pin);
    }


    @Override
    public String toString() {
        return number + " " + pin + " " + balance + " " + isBlocked;
    }
}

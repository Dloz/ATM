import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditCardValidator {
    public static boolean isValidPin(String pin){
        Pattern pinPattern = Pattern.compile("([0-9]{4})");
        Matcher pinMatcher = pinPattern.matcher(pin);
        return pinMatcher.matches();
    }

    public static boolean isValidCardNumber(String cardNumber){
        Pattern cardNumberPattern = Pattern.compile("([2-6]([0-9]{3})-?)(([0-9]{4}-?){3})");
        Matcher cardNumberMatcher = cardNumberPattern.matcher(cardNumber);
        return cardNumberMatcher.matches();
    }
}

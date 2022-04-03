package utilities.internal;
 import java.util.regex.Pattern;
public class ThrowablePrinter {


public void print(Throwable oops){
    assert oops != null;
    Throwable iterator;
    String message;
    int position;
    System.err.println();
    iterator = oops;
    while (iterator != null) {
        message = iterator.getMessage();
        if (message != null && Pattern.matches("^[a-zA-Z.]+:.*$", message)) {
            position = message.indexOf(':');
            message = message.substring(position + 1).trim();
        }
        System.err.printf("%s: %s%n", iterator.getClass().getName(), message);
        iterator = iterator.getCause();
    }
    System.err.println();
}


}
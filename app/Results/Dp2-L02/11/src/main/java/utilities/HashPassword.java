package utilities;
 import java.io.IOException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import utilities.internal.ConsoleReader;
import utilities.internal.ThrowablePrinter;
public class HashPassword {


public void main(String[] args){
    Md5PasswordEncoder encoder;
    ConsoleReader reader;
    String line, hash;
    try {
        System.out.println("HashPassword 1.18.2");
        System.out.println("-------------------");
        System.out.println();
        encoder = new Md5PasswordEncoder();
        reader = new ConsoleReader();
        line = reader.readLine();
        while (!line.equals("quit")) {
            hash = encoder.encodePassword(line, null);
            System.out.println(hash);
            line = reader.readLine();
        }
    } catch (final Throwable oops) {
        ThrowablePrinter.print(oops);
    }
}


}
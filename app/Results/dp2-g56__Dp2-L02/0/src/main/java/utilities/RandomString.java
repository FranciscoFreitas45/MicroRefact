package utilities;
 import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
public class RandomString {

 public  String upper;

 public  String lower;

 public  String digits;

 public  String alphanum;

 private  Random random;

 private  char[] symbols;

 private  char[] buf;

public RandomString(int length, Random random, String symbols) {
    if (length < 1)
        throw new IllegalArgumentException();
    if (symbols.length() < 2)
        throw new IllegalArgumentException();
    this.random = Objects.requireNonNull(random);
    this.symbols = symbols.toCharArray();
    this.buf = new char[length];
}/**
 * Create an alphanumeric string generator.
 */
public RandomString(int length, Random random) {
    this(length, random, RandomString.alphanum);
}/**
 * Create an alphanumeric strings from a secure generator.
 */
public RandomString(int length) {
    this(length, new SecureRandom());
}/**
 * Create session identifiers.
 */
public RandomString() {
    this(21);
}
public String nextString(){
    for (int idx = 0; idx < this.buf.length; ++idx) this.buf[idx] = this.symbols[this.random.nextInt(this.symbols.length)];
    return new String(this.buf);
}


}
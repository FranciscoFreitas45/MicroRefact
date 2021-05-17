import java.security.SecureRandom;
import java.util;
public class RandomString {

 public  String upper;

 public  String digits;

 public  String special;

 public  String alphanum;

 private  Random random;

 private  char[] symbols;

 private  char[] digSy;

 private  char[] spciSy;

 private  char[] buf;


public String nextString(){
    List<String> answersList = new ArrayList<>();
    String finalString = "";
    buf[0] = digSy[random.nextInt(digits.length())];
    buf[1] = spciSy[random.nextInt(special.length())];
    answersList.add(String.valueOf(buf[0]));
    answersList.add(String.valueOf(buf[1]));
    // System.out.println(buf);
    for (int idx = 2; idx < buf.length; ++idx) {
        buf[idx] = symbols[random.nextInt(upper.length())];
        answersList.add(String.valueOf(buf[idx]));
    }
    Collections.shuffle(answersList);
    for (int i = 0; i < answersList.size(); i++) {
        finalString = finalString + answersList.get(i);
    }
    return finalString;
}


}
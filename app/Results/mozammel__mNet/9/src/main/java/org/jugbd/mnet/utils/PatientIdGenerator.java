package org.jugbd.mnet.utils;
 import org.jugbd.mnet.domain.Address;
import java.util.Calendar;
import java.util.Random;
public class PatientIdGenerator {

 public  int BASE_YEAR;

 private  Random random;

 private  String[] symbolPool;

 private  int lastMillis;

 private  int lastSecond;


public String calculateCheckDigit(String formId){
    char[] chars = formId.toCharArray();
    char hyphen = '-';
    int total = 0;
    int pos = 1;
    for (char c : chars) {
        if (c == hyphen)
            continue;
        total += (pos * Character.getNumericValue(c));
        pos++;
    }
    int check = total % 32;
    return base32(check, 0);
}


public String base32(int n,int width){
    StringBuilder val = new StringBuilder(10);
    int r;
    while (n > 0) {
        r = n % 32;
        n = n / 32;
        val.append(symbolPool[r]);
    }
    val.reverse();
    if (width == 0)
        return val.toString();
    StringBuilder v2 = new StringBuilder(10);
    for (int len = val.length(); len < width; len++) {
        v2.append(symbolPool[0]);
    }
    v2.append(val);
    return v2.toString();
}


public String generate(Address address){
    String divisionPref = address.getDivision().substring(0, 3).toUpperCase();
    String districtPrefix = address.getDistrict().getShortCode().toUpperCase();
    // the sequence
    String seq = nextSequence();
    String main = divisionPref + "-" + districtPrefix + "-" + seq;
    return main + calculateCheckDigit(main);
}


public String nextSequence(){
    Calendar cal = Calendar.getInstance();
    int y = cal.get(Calendar.YEAR) - BASE_YEAR;
    // In java January is 0
    int m = cal.get(Calendar.MONTH) + 1;
    // starts with 1
    int d = cal.get(Calendar.DAY_OF_MONTH);
    int s = cal.get(Calendar.HOUR_OF_DAY) * 3600 + cal.get(Calendar.MINUTE) * 60 + cal.get(Calendar.SECOND);
    int millis = cal.get(Calendar.MILLISECOND);
    // Generate 3 digits at max
    int random = PatientIdGenerator.random.nextInt(3);
    if (s <= lastSecond) {
        if (s < lastSecond) {
            s = lastSecond;
        }
        if (millis <= lastMillis) {
            millis = lastMillis + 1;
            if (millis >= 1024) {
                // There are 86400 seconds/day. The seconds value is converted to base 32 and truncated to 4 digits.
                // This gives a max of about 1 million. It's unlikely that our "s" will wrap around.
                s++;
                millis = 0;
            }
        }
    }
    lastSecond = s;
    lastMillis = millis;
    String y32 = base32(y, 0);
    String m32 = base32(m, 0);
    String d32 = base32(d, 0);
    String s32 = base32(s, 4);
    String millis32 = base32(millis, 2);
    // max 3 digits
    String random3Digit = base32(random, 0);
    return y32 + m32 + d32 + s32 + millis32 + random3Digit;
}


}
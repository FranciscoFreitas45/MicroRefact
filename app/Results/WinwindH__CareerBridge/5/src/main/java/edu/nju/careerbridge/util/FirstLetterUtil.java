package edu.nju.careerbridge.util;
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.UnsupportedEncodingException;
public class FirstLetterUtil {

 private  Logger log;


public String toJP(String c){
    char[] chars = c.toCharArray();
    StringBuffer sb = new StringBuffer("");
    for (int i = 0; i < chars.length; i++) {
        sb.append(getJP(chars[i]));
    }
    return sb.toString().toUpperCase();
}


public String getJP(char c){
    byte[] array = new byte[2];
    try {
        array = String.valueOf(c).getBytes("gbk");
    } catch (UnsupportedEncodingException e) {
        String message = "Unexpected NullPointerException in processing!";
        log.error(message, e);
    }
    if (array.length < 2)
        return String.valueOf(c);
    int i = (short) (array[0] - '\0' + 256) * 256 + ((short) (array[1] - '\0' + 256));
    if (i < 0xB0A1)
        return String.valueOf(c);
    if (i < 0xB0C5)
        return "a";
    if (i < 0xB2C1)
        return "b";
    if (i < 0xB4EE)
        return "c";
    if (i < 0xB6EA)
        return "d";
    if (i < 0xB7A2)
        return "e";
    if (i < 0xB8C1)
        return "f";
    if (i < 0xB9FE)
        return "g";
    if (i < 0xBBF7)
        return "h";
    if (i < 0xBFA6)
        return "j";
    if (i < 0xC0AC)
        return "k";
    if (i < 0xC2E8)
        return "l";
    if (i < 0xC4C3)
        return "m";
    if (i < 0xC5B6)
        return "n";
    if (i < 0xC5BE)
        return "o";
    if (i < 0xC6DA)
        return "p";
    if (i < 0xC8BB)
        return "q";
    if (i < 0xC8F6)
        return "r";
    if (i < 0xCBFA)
        return "s";
    if (i < 0xCDDA)
        return "t";
    if (i < 0xCEF4)
        return "w";
    if (i < 0xD1B9)
        return "x";
    if (i < 0xD4D1)
        return "y";
    if (i < 0xD7FA)
        return "z";
    return String.valueOf(c);
}


public void main(String[] args){
    String str = null;
    str = "大数据工程师ss6";
    System.out.println("Spell=" + toJP(str));
}


}
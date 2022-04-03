package com.ushahidi.swiftriver.core.util;
 import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.regex.Pattern;
import org.apache.commons.codec.binary.Hex;
public class TextUtil {


public String convertStringToHex(String str){
    return new String(Hex.encodeHex(str.getBytes()));
}


public String getURLSlug(String phrase){
    if (phrase == null || phrase.trim().length() == 0)
        return "";
    String nonWhitespace = Pattern.compile("[^\\w-]").matcher(phrase).replaceAll("-");
    String normalized = Normalizer.normalize(nonWhitespace, Form.NFD);
    String slug = Pattern.compile("[\\s]").matcher(normalized).replaceAll("");
    return slug.toLowerCase();
}


}
package org.jugbd.mnet.utils;
 public class StringUtils {

 private  String elegibleChars;


public String getTrimmedString(String text,int lengthToTrim){
    if (text.length() <= lengthToTrim) {
        return text;
    }
    return text.substring(0, lengthToTrim);
}


public String generateRandomString(int stringLength){
    char[] chars = elegibleChars.toCharArray();
    final StringBuilder finalString = new StringBuilder();
    for (int i = 0; i < stringLength; i++) {
        double randomValue = Math.random();
        int randomIndex = (int) Math.round(randomValue * (chars.length - 1));
        char characterToShow = chars[randomIndex];
        finalString.append(characterToShow);
    }
    return finalString.toString();
}


public boolean isEmpty(String str){
    return ((str == null) || (str.trim().length() == 0));
}


public boolean isNotEmpty(String str){
    return !isEmpty(str);
}


}
package com.tech.configurations.tools;
 public class NameCoder {


public String pathConverter(Long hsTag){
    Long userid;
    String str = Long.toString(hsTag);
    String tmStr = str.substring(0, 1) + str.substring(str.length() - Integer.parseInt(str.substring(1, 3)));
    userid = Long.parseLong(tmStr);
    String finalPath = Attr.IMAGES_OUTPUT_FOLDER.getData() + "\\" + userid + "\\" + hsTag + ".jpg";
    return finalPath;
}


public String invalidPathConvertrer(Long hsTag){
    String finalPath = Attr.IMAGES_OUTPUT_FOLDER.getData() + "\\" + "WrongName" + "\\" + hsTag + ".jpg";
    return finalPath;
}


public int hashCodeValidator(int hash){
    if (hash < 0) {
        return -hash;
    }
    return hash;
}


public Long nameConverter(Long userid,int tmstamp){
    String str = Long.toString(userid);
    String finalString;
    char firstChar = str.charAt(0);
    if (str.length() > 1) {
        String otherChars = str.substring(1);
        if (otherChars.length() > 7) {
            // finalString = firstChar + Integer.toString(otherChars.length()) + Integer.toString(hashCodeValidator(tmstamp)) + otherChars;
            throw new NumberFormatException("Number was too big");
        } else {
            finalString = firstChar + "0" + otherChars.length() + Integer.toString(hashCodeValidator(tmstamp)) + otherChars;
        }
    } else {
        finalString = firstChar + "00" + Integer.toString(hashCodeValidator(tmstamp));
    }
    return Long.parseLong(finalString);
}


}
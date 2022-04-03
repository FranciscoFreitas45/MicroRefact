package com.xwtec.xwserver.util.json.util;
 import org.apache.commons.lang.StringUtils;
import com.xwtec.xwserver.util.json.JSONException;
public class JavaIdentifierTransformer {

 public  JavaIdentifierTransformer CAMEL_CASE;

 public  JavaIdentifierTransformer NOOP;

 public  JavaIdentifierTransformer STRICT;

 public  JavaIdentifierTransformer UNDERSCORE;

 public  JavaIdentifierTransformer WHITESPACE;


public String transformToJavaIdentifier(String str){
    if (str == null) {
        return null;
    }
    String str2 = shaveOffNonJavaIdentifierStartChars(str);
    str2 = StringUtils.deleteWhitespace(str2);
    char[] chars = str2.toCharArray();
    int pos = 0;
    StringBuffer buf = new StringBuffer();
    while (pos < chars.length) {
        if (Character.isJavaIdentifierPart(chars[pos])) {
            buf.append(chars[pos]);
        }
        pos++;
    }
    return buf.toString();
}


public String shaveOffNonJavaIdentifierStartChars(String str){
    String str2 = str;
    // shave off first char if not valid
    boolean ready = false;
    while (!ready) {
        if (!Character.isJavaIdentifierStart(str2.charAt(0))) {
            str2 = str2.substring(1);
            if (str2.length() == 0) {
                throw new JSONException("Can't convert '" + str + "' to a valid Java identifier");
            }
        } else {
            ready = true;
        }
    }
    return str2;
}


}
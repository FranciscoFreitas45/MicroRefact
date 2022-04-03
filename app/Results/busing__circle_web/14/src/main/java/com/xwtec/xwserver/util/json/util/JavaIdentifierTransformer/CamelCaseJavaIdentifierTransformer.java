package com.xwtec.xwserver.util.json.util.JavaIdentifierTransformer;
 import org.apache.commons.lang.StringUtils;
import com.xwtec.xwserver.util.json.JSONException;
public class CamelCaseJavaIdentifierTransformer extends JavaIdentifierTransformer{


public String transformToJavaIdentifier(String str){
    if (str == null) {
        return null;
    }
    String str2 = shaveOffNonJavaIdentifierStartChars(str);
    char[] chars = str2.toCharArray();
    int pos = 0;
    StringBuffer buf = new StringBuffer();
    boolean toUpperCaseNextChar = false;
    while (pos < chars.length) {
        if (!Character.isJavaIdentifierPart(chars[pos]) || Character.isWhitespace(chars[pos])) {
            toUpperCaseNextChar = true;
        } else {
            if (toUpperCaseNextChar) {
                buf.append(Character.toUpperCase(chars[pos]));
                toUpperCaseNextChar = false;
            } else {
                buf.append(chars[pos]);
            }
        }
        pos++;
    }
    return buf.toString();
}


}
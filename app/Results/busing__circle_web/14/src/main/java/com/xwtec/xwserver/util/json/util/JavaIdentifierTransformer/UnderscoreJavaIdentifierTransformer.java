package com.xwtec.xwserver.util.json.util.JavaIdentifierTransformer;
 import org.apache.commons.lang.StringUtils;
import com.xwtec.xwserver.util.json.JSONException;
public class UnderscoreJavaIdentifierTransformer extends JavaIdentifierTransformer{


public String transformToJavaIdentifier(String str){
    if (str == null) {
        return null;
    }
    String str2 = shaveOffNonJavaIdentifierStartChars(str);
    char[] chars = str2.toCharArray();
    int pos = 0;
    StringBuffer buf = new StringBuffer();
    boolean toUnderScorePreviousChar = false;
    while (pos < chars.length) {
        if (!Character.isJavaIdentifierPart(chars[pos]) || Character.isWhitespace(chars[pos])) {
            toUnderScorePreviousChar = true;
        } else {
            if (toUnderScorePreviousChar) {
                buf.append("_");
                toUnderScorePreviousChar = false;
            }
            buf.append(chars[pos]);
        }
        pos++;
    }
    if (buf.charAt(buf.length() - 1) == '_') {
        buf.deleteCharAt(buf.length() - 1);
    }
    return buf.toString();
}


}
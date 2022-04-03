package com.xwtec.xwserver.util.json.util.JavaIdentifierTransformer;
 import org.apache.commons.lang.StringUtils;
import com.xwtec.xwserver.util.json.JSONException;
public class WhiteSpaceJavaIdentifierTransformer extends JavaIdentifierTransformer{


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


}
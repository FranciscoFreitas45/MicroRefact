package org.gliderwiki.util;
 import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.gliderwiki.util.parser.GliderTagParserMethodBasicTag;
import org.gliderwiki.util.parser.GliderTagParserMethodSpecialTag;
public class GliderTagPaserUtil {


public String replaceHtmlToParsing(String str){
    GliderTagParserMethodBasicTag basic = new GliderTagParserMethodBasicTag();
    GliderTagParserMethodSpecialTag tag = new GliderTagParserMethodSpecialTag();
    str = tag.getSYNTAX_TO_HTML(str, true);
    str = basic.getHTML_TAG(str);
    str = tag.getSYNTAX_TO_HTML(str, false);
    return str;
}


public String HTMLBREncode(String str){
    return str.replaceAll("\r\n", "\n<br class=\"br\"/>\n");
}


public String ReplaceHTMLSpecialChars(String str){
    str = str.replaceAll("<", "&lt;");
    str = str.replaceAll(">", "&gt;");
    // str = str.replaceAll("&", "&amp;");
    return str;
}


public String replaceFirstTag(String str,String patternTxt,String replacement,int flags){
    Pattern pattern = Pattern.compile(patternTxt, flags);
    Matcher match = pattern.matcher(str);
    str = match.replaceFirst(replacement);
    return str;
}


public String getFirstTag(String str,String patternTxt){
    Pattern pattern = Pattern.compile(patternTxt);
    Matcher match = pattern.matcher(str);
    if (match.find()) {
        str = match.group();
    } else {
        str = null;
    }
    return str;
}


public String ReplaceHTMLSpecialCharsDollar(String str){
    return str.replaceAll("[$]", "\\\\\\$");
}


public String getFirstReturnTag(String str,String patternTxt,String replacement,int flags){
    Pattern pattern = Pattern.compile(patternTxt, flags);
    Matcher match = pattern.matcher(str);
    if (match.find()) {
        str = match.group();
        match = pattern.matcher(str);
        str = match.replaceFirst(replacement);
    } else {
        str = "";
    }
    return str;
}


public boolean getMatchFind(String str,String patternTxt,int flags){
    Pattern pattern = Pattern.compile(patternTxt, flags);
    Matcher match = pattern.matcher(str);
    return match.find();
}


public String ReplaceHTMLAll(String str){
    str = HTMLBREncode(str);
    str = ReplaceHTMLSpecialChars(str);
    return str;
}


public String replaceAllTag(String str,String patternTxt,String replacement,int flags){
    Pattern pattern = Pattern.compile(patternTxt, flags);
    Matcher match = pattern.matcher(str);
    return match.replaceAll(replacement);
}


}
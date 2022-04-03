package com.xwtec.xwserver.util.json.regexp;
 import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class JdkRegexpMatcher implements RegexpMatcher{

 private  Pattern pattern;

public JdkRegexpMatcher(String pattern) {
    this(pattern, false);
}public JdkRegexpMatcher(String pattern, boolean multiline) {
    if (multiline) {
        this.pattern = Pattern.compile(pattern, Pattern.MULTILINE);
    } else {
        this.pattern = Pattern.compile(pattern);
    }
}
public String getGroupIfMatches(String str,int group){
    Matcher matcher = pattern.matcher(str);
    if (matcher.matches()) {
        return matcher.group(group);
    }
    return "";
}


public boolean matches(String str){
    return pattern.matcher(str).matches();
}


}
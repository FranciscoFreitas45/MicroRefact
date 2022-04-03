package com.xwtec.xwserver.util.json.regexp;
 public class RegexpUtils {

 private  String javaVersion;

private RegexpUtils() {
}
public RegexpMatcher getMatcher(String pattern,boolean multiline){
    if (isJDK13()) {
        return new Perl5RegexpMatcher(pattern, true);
    } else {
        return new JdkRegexpMatcher(pattern, true);
    }
}


public boolean isJDK13(){
    return javaVersion.indexOf("1.3") != -1;
}


}
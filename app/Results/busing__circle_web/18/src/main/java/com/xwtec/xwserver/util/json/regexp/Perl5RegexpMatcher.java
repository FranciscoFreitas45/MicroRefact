package com.xwtec.xwserver.util.json.regexp;
 import org.apache.commons.lang.exception.NestableRuntimeException;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
public class Perl5RegexpMatcher implements RegexpMatcher{

 private  Perl5Compiler compiler;

 private  Pattern pattern;

public Perl5RegexpMatcher(String pattern) {
    this(pattern, false);
}public Perl5RegexpMatcher(String pattern, boolean multiline) {
    try {
        if (multiline) {
            this.pattern = compiler.compile(pattern, Perl5Compiler.READ_ONLY_MASK | Perl5Compiler.MULTILINE_MASK);
        } else {
            this.pattern = compiler.compile(pattern, Perl5Compiler.READ_ONLY_MASK);
        }
    } catch (MalformedPatternException mpe) {
        throw new NestableRuntimeException(mpe);
    }
}
public String getGroupIfMatches(String str,int group){
    PatternMatcher matcher = new Perl5Matcher();
    if (matcher.matches(str, pattern)) {
        return matcher.getMatch().group(1);
    }
    return "";
}


public boolean matches(String str){
    return new Perl5Matcher().matches(str, pattern);
}


}
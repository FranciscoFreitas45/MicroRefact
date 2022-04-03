package com.xwtec.xwserver.util.json.util;
 import com.xwtec.xwserver.util.json.JSONArray;
import com.xwtec.xwserver.util.json.JSONException;
import com.xwtec.xwserver.util.json.JSONNull;
import com.xwtec.xwserver.util.json.JSONObject;
import com.xwtec.xwserver.util.json.JsonConfig;
import com.xwtec.xwserver.util.json.regexp.RegexpUtils;
public class JSONTokener {

 private  int myIndex;

 private  String mySource;

/**
 * Construct a JSONTokener from a string.
 *
 * @param s A source string.
 */
public JSONTokener(String s) {
    this.myIndex = 0;
    if (s != null) {
        s = s.trim();
    } else {
        s = "";
    }
    if (s.length() > 0) {
        char first = s.charAt(0);
        char last = s.charAt(s.length() - 1);
        if (first == '[' && last != ']') {
            throw syntaxError("Found starting '[' but missing ']' at the end.");
        }
        if (first == '{' && last != '}') {
            throw syntaxError("Found starting '{' but missing '}' at the end.");
        }
    }
    this.mySource = s;
}
public String next(int n){
    int i = this.myIndex;
    int j = i + n;
    if (j >= this.mySource.length()) {
        throw syntaxError("Substring bounds error");
    }
    this.myIndex += n;
    return this.mySource.substring(i, j);
}


public JSONException syntaxError(String message){
    return new JSONException(message + toString());
}


public boolean more(){
    return this.myIndex < this.mySource.length();
}


public int length(){
    if (this.mySource == null) {
        return 0;
    }
    return this.mySource.length();
}


public char skipTo(char to){
    char c;
    int index = this.myIndex;
    do {
        c = next();
        if (c == 0) {
            this.myIndex = index;
            return c;
        }
    } while (c != to);
    back();
    return c;
}


public void back(){
    if (this.myIndex > 0) {
        this.myIndex -= 1;
    }
}


public char nextClean(){
    for (; ; ) {
        char c = next();
        if (c == '/') {
            switch(next()) {
                case '/':
                    do {
                        c = next();
                    } while (c != '\n' && c != '\r' && c != 0);
                    break;
                case '*':
                    for (; ; ) {
                        c = next();
                        if (c == 0) {
                            throw syntaxError("Unclosed comment.");
                        }
                        if (c == '*') {
                            if (next() == '/') {
                                break;
                            }
                            back();
                        }
                    }
                    break;
                default:
                    back();
                    return '/';
            }
        } else if (c == '#') {
            do {
                c = next();
            } while (c != '\n' && c != '\r' && c != 0);
        } else if (c == 0 || c > ' ') {
            return c;
        }
    }
}


public String nextString(char quote){
    char c;
    StringBuffer sb = new StringBuffer();
    for (; ; ) {
        c = next();
        switch(c) {
            case 0:
            case '\n':
            case '\r':
                throw syntaxError("Unterminated string");
            case '\\':
                c = next();
                switch(c) {
                    case 'b':
                        sb.append('\b');
                        break;
                    case 't':
                        sb.append('\t');
                        break;
                    case 'n':
                        sb.append('\n');
                        break;
                    case 'f':
                        sb.append('\f');
                        break;
                    case 'r':
                        sb.append('\r');
                        break;
                    case 'u':
                        sb.append((char) Integer.parseInt(next(4), 16));
                        break;
                    case 'x':
                        sb.append((char) Integer.parseInt(next(2), 16));
                        break;
                    default:
                        sb.append(c);
                }
                break;
            default:
                if (c == quote) {
                    return sb.toString();
                }
                sb.append(c);
        }
    }
}


public int dehexchar(char c){
    if (c >= '0' && c <= '9') {
        return c - '0';
    }
    if (c >= 'A' && c <= 'F') {
        return c - ('A' - 10);
    }
    if (c >= 'a' && c <= 'f') {
        return c - ('a' - 10);
    }
    return -1;
}


public boolean matches(String pattern){
    String str = this.mySource.substring(this.myIndex);
    return RegexpUtils.getMatcher(pattern).matches(str);
}


public char peek(){
    if (more()) {
        char c = this.mySource.charAt(this.myIndex);
        return c;
    }
    return 0;
}


public void skipPast(String to){
    this.myIndex = this.mySource.indexOf(to, this.myIndex);
    if (this.myIndex < 0) {
        this.myIndex = this.mySource.length();
    } else {
        this.myIndex += to.length();
    }
}


public String nextTo(String delimiters){
    char c;
    StringBuffer sb = new StringBuffer();
    for (; ; ) {
        c = next();
        if (delimiters.indexOf(c) >= 0 || c == 0 || c == '\n' || c == '\r') {
            if (c != 0) {
                back();
            }
            return sb.toString().trim();
        }
        sb.append(c);
    }
}


public void reset(){
    this.myIndex = 0;
}


public String toString(){
    return " at character " + this.myIndex + " of " + this.mySource;
}


public Object nextValue(JsonConfig jsonConfig){
    char c = nextClean();
    String s;
    switch(c) {
        case '"':
        case '\'':
            return nextString(c);
        case '{':
            back();
            return JSONObject.fromObject(this, jsonConfig);
        case '[':
            back();
            return JSONArray.fromObject(this, jsonConfig);
        default:
    }
    /*
       * Handle unquoted text. This could be the values true, false, or null, or
       * it can be a number. An implementation (such as this one) is allowed to
       * also accept non-standard forms. Accumulate characters until we reach
       * the end of the text or a formatting character.
       */
    StringBuffer sb = new StringBuffer();
    char b = c;
    while (c >= ' ' && ",:]}/\\\"[{;=#".indexOf(c) < 0) {
        sb.append(c);
        c = next();
    }
    back();
    /*
       * If it is true, false, or null, return the proper value.
       */
    s = sb.toString().trim();
    if (s.equals("")) {
        throw syntaxError("Missing value.");
    }
    if (s.equalsIgnoreCase("true")) {
        return Boolean.TRUE;
    }
    if (s.equalsIgnoreCase("false")) {
        return Boolean.FALSE;
    }
    if (s.equals("null") || (jsonConfig.isJavascriptCompliant() && s.equals("undefined"))) {
        return JSONNull.getInstance();
    }
    /*
       * If it might be a number, try converting it. We support the 0- and 0x-
       * conventions. If a number cannot be produced, then the value will just
       * be a string. Note that the 0-, 0x-, plus, and implied string
       * conventions are non-standard. A JSON parser is free to accept non-JSON
       * forms as long as it accepts all correct JSON forms.
       */
    if ((b >= '0' && b <= '9') || b == '.' || b == '-' || b == '+') {
        if (b == '0') {
            if (s.length() > 2 && (s.charAt(1) == 'x' || s.charAt(1) == 'X')) {
                try {
                    return new Integer(Integer.parseInt(s.substring(2), 16));
                } catch (Exception e) {
                /* Ignore the error */
                }
            } else {
                try {
                    return new Integer(Integer.parseInt(s, 8));
                } catch (Exception e) {
                /* Ignore the error */
                }
            }
        }
        try {
            return new Integer(s);
        } catch (Exception e) {
            try {
                return new Long(s);
            } catch (Exception f) {
                try {
                    return new Double(s);
                } catch (Exception g) {
                    return s;
                }
            }
        }
    }
    if (JSONUtils.isFunctionHeader(s) || JSONUtils.isFunction(s)) {
        return s;
    }
    switch(peek()) {
        case ',':
        case '}':
        case '{':
        case '[':
        case ']':
            throw new JSONException("Unquotted string '" + s + "'");
    }
    return s;
}


}
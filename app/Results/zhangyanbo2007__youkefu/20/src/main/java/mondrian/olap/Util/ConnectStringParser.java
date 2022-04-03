package mondrian.olap.Util;
 import mondrian.mdx;
import mondrian.olap.fun.FunUtil;
import mondrian.olap.fun.Resolver;
import mondrian.olap.type.Type;
import mondrian.resource.MondrianResource;
import mondrian.rolap;
import mondrian.spi.UserDefinedFunction;
import mondrian.util;
import org.apache.commons.collections.keyvalue.AbstractMapEntry;
import org.apache.commons.vfs;
import org.apache.commons.vfs.provider.http.HttpFileObject;
import org.apache.log4j.Logger;
import org.eigenbase.xom.XOMUtil;
import org.olap4j.impl.Olap4jUtil;
import org.olap4j.mdx;
import java.io;
import java.lang.ref.Reference;
import java.lang.reflect;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql;
import java.sql.Connection;
import java.util;
import java.util.concurrent;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ConnectStringParser {

 private  String s;

 private  int n;

 private  int i;

 private  StringBuilder nameBuf;

 private  StringBuilder valueBuf;

private ConnectStringParser(String s) {
    this.s = s;
    this.i = 0;
    this.n = s.length();
    this.nameBuf = new StringBuilder(64);
    this.valueBuf = new StringBuilder(64);
}
public String parseName(){
    nameBuf.setLength(0);
    while (true) {
        char c = s.charAt(i);
        switch(c) {
            case '=':
                i++;
                if (i < n && (c = s.charAt(i)) == '=') {
                    // doubled equals sign; take one of them, and carry on
                    i++;
                    nameBuf.append(c);
                    break;
                }
                String name = nameBuf.toString();
                name = name.trim();
                return name;
            case ' ':
                if (nameBuf.length() == 0) {
                    // ignore preceding spaces
                    i++;
                    if (i >= n) {
                        // there is no name, e.g. trailing spaces after
                        // semicolon, 'x=1; y=2; '
                        return null;
                    }
                    break;
                } else {
                // fall through
                }
            default:
                nameBuf.append(c);
                i++;
                if (i >= n) {
                    return nameBuf.toString().trim();
                }
        }
    }
}


public void parsePair(PropertyList list){
    String name = parseName();
    if (name == null) {
        return;
    }
    String value;
    if (i >= n) {
        value = "";
    } else if (s.charAt(i) == ';') {
        i++;
        value = "";
    } else {
        value = parseValue();
    }
    list.put(name, value);
}


public PropertyList parse(){
    PropertyList list = new PropertyList();
    while (i < n) {
        parsePair(list);
    }
    return list;
}


public String parseValue(){
    char c;
    // skip over leading white space
    while ((c = s.charAt(i)) == ' ') {
        i++;
        if (i >= n) {
            return "";
        }
    }
    if (c == '"' || c == '\'') {
        String value = parseQuoted(c);
        // skip over trailing white space
        while (i < n && (c = s.charAt(i)) == ' ') {
            i++;
        }
        if (i >= n) {
            return value;
        } else if (s.charAt(i) == ';') {
            i++;
            return value;
        } else {
            throw new RuntimeException("quoted value ended too soon, at position " + i + " in '" + s + "'");
        }
    } else {
        String value;
        int semi = s.indexOf(';', i);
        if (semi >= 0) {
            value = s.substring(i, semi);
            i = semi + 1;
        } else {
            value = s.substring(i);
            i = n;
        }
        return value.trim();
    }
}


public String parseQuoted(char q){
    char c = s.charAt(i++);
    Util.assertTrue(c == q);
    valueBuf.setLength(0);
    while (i < n) {
        c = s.charAt(i);
        if (c == q) {
            i++;
            if (i < n) {
                c = s.charAt(i);
                if (c == q) {
                    valueBuf.append(c);
                    i++;
                    continue;
                }
            }
            return valueBuf.toString();
        } else {
            valueBuf.append(c);
            i++;
        }
    }
    throw new RuntimeException("Connect string '" + s + "' contains unterminated quoted value '" + valueBuf.toString() + "'");
}


}
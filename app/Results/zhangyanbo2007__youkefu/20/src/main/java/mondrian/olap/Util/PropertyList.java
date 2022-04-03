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
public class PropertyList implements Serializable,Iterable<Pair<String, String>>{

 private List<Pair<String,String>> list;

public PropertyList() {
    this.list = new ArrayList<Pair<String, String>>();
}private PropertyList(List<Pair<String, String>> list) {
    this.list = list;
}
public Iterator<Pair<String,String>> iterator(){
    return list.iterator();
}


public String get(String key,String defaultValue){
    for (int i = 0, n = list.size(); i < n; i++) {
        Pair<String, String> pair = list.get(i);
        if (pair.left.equalsIgnoreCase(key)) {
            return pair.right;
        }
    }
    return defaultValue;
}


@SuppressWarnings({ "CloneDoesntCallSuperClone" })
@Override
public PropertyList clone(){
    return new PropertyList(new ArrayList<Pair<String, String>>(list));
}


public String toString(){
    StringBuilder sb = new StringBuilder(64);
    for (int i = 0, n = list.size(); i < n; i++) {
        Pair<String, String> pair = list.get(i);
        if (i > 0) {
            sb.append("; ");
        }
        sb.append(pair.left);
        sb.append('=');
        final String right = pair.right;
        if (right == null) {
            sb.append("'null'");
        } else {
            // Quote a property value if is has a semi colon in it
            // 'xxx;yyy'. Escape any single-quotes by doubling them.
            final int needsQuote = right.indexOf(';');
            if (needsQuote >= 0) {
                // REVIEW: This logic leaves off the leading/trailing
                // quote if the property value already has a
                // leading/trailing quote. Doesn't seem right to me.
                if (right.charAt(0) != '\'') {
                    sb.append("'");
                }
                sb.append(replace(right, "'", "''"));
                if (right.charAt(right.length() - 1) != '\'') {
                    sb.append("'");
                }
            } else {
                sb.append(right);
            }
        }
    }
    return sb.toString();
}


public String put(String key,String value){
    for (int i = 0, n = list.size(); i < n; i++) {
        Pair<String, String> pair = list.get(i);
        if (pair.left.equalsIgnoreCase(key)) {
            String old = pair.right;
            if (key.equalsIgnoreCase("Provider")) {
            // Unlike all other properties, later values of
            // "Provider" do not supersede
            } else {
                pair.right = value;
            }
            return old;
        }
    }
    list.add(new Pair<String, String>(key, value));
    return null;
}


public boolean remove(String key){
    boolean found = false;
    for (int i = 0; i < list.size(); i++) {
        Pair<String, String> pair = list.get(i);
        if (pair.getKey().equalsIgnoreCase(key)) {
            list.remove(i);
            found = true;
            --i;
        }
    }
    return found;
}


}
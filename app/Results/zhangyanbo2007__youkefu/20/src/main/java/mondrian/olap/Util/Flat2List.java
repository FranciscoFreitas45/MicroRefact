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
public class Flat2List extends AbstractFlatList<T>{

 private  T t0;

 private  T t1;

Flat2List(T t0, T t1) {
    this.t0 = t0;
    this.t1 = t1;
    assert t0 != null;
    assert t1 != null;
}
public int lastIndexOf(Object o){
    if (t1.equals(o)) {
        return 1;
    }
    if (t0.equals(o)) {
        return 0;
    }
    return -1;
}


public int size(){
    return 2;
}


public int hashCode(){
    int h = 1;
    h = h * 31 + t0.hashCode();
    h = h * 31 + t1.hashCode();
    return h;
}


public T get(int index){
    switch(index) {
        case 0:
            return t0;
        case 1:
            return t1;
        default:
            throw new IndexOutOfBoundsException("index " + index);
    }
}


public boolean equals(Object o){
    if (o instanceof Flat2List) {
        Flat2List that = (Flat2List) o;
        return Util.equals(this.t0, that.t0) && Util.equals(this.t1, that.t1);
    }
    return Arrays.asList(t0, t1).equals(o);
}


public Object[] toArray(){
    return new Object[] { t0, t1 };
}


public String toString(){
    return "[" + t0 + ", " + t1 + "]";
}


public int indexOf(Object o){
    if (t0.equals(o)) {
        return 0;
    }
    if (t1.equals(o)) {
        return 1;
    }
    return -1;
}


}
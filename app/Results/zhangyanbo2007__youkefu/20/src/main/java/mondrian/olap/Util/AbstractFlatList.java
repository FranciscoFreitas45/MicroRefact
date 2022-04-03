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
public class AbstractFlatList implements List<T>,RandomAccess{


public List<T> asArrayList(){
    // noinspection unchecked
    return Arrays.asList((T[]) toArray());
}


public void add(int index,Object element){
    throw new UnsupportedOperationException();
}


public List<T> subList(int fromIndex,int toIndex){
    return asArrayList().subList(fromIndex, toIndex);
}


public T set(int index,Object element){
    throw new UnsupportedOperationException();
}


public boolean containsAll(Collection<?> c){
    Iterator<?> e = c.iterator();
    while (e.hasNext()) {
        if (!contains(e.next())) {
            return false;
        }
    }
    return true;
}


public boolean isEmpty(){
    return false;
}


public void clear(){
    throw new UnsupportedOperationException();
}


public boolean remove(Object o){
    throw new UnsupportedOperationException();
}


public Iterator<T> iterator(){
    return asArrayList().iterator();
}


public boolean removeAll(Collection<?> c){
    throw new UnsupportedOperationException();
}


public boolean contains(Object o){
    return indexOf(o) >= 0;
}


public boolean addAll(int index,Collection<? extends T> c){
    throw new UnsupportedOperationException();
}


public ListIterator<T> listIterator(int index){
    return asArrayList().listIterator(index);
}


public boolean retainAll(Collection<?> c){
    throw new UnsupportedOperationException();
}


}
package mondrian.olap.fun.FunUtil;
 import mondrian.calc;
import mondrian.calc.impl;
import mondrian.mdx;
import mondrian.olap;
import mondrian.olap.type;
import mondrian.resource.MondrianResource;
import mondrian.rolap;
import mondrian.util;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.log4j.Logger;
import java.util;
public class ObjIntPair {

 final  T t;

 final  int i;

public ObjIntPair(T t, int i) {
    this.t = t;
    this.i = i;
}
public int hashCode(){
    return Util.hash(i, t);
}


public boolean equals(Object obj){
    return this == obj || obj instanceof ObjIntPair && this.i == ((ObjIntPair) obj).i && Util.equals(this.t, ((ObjIntPair) obj).t);
}


public String toString(){
    return "<" + t + ", " + i + ">";
}


}
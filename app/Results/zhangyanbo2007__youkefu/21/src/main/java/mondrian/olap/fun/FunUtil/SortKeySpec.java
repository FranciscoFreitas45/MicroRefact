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
public class SortKeySpec {

 private  Calc key;

 private  Flag direction;

SortKeySpec(Calc key, Flag dir) {
    this.key = key;
    this.direction = dir;
}
public Calc getKey(){
    return this.key;
}


public Flag getDirection(){
    return this.direction;
}


}
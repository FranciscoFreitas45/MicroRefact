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
public class HierarchizeComparator implements Comparator<Member>{

 private  boolean post;

HierarchizeComparator(boolean post) {
    this.post = post;
}
public int compare(Member m1,Member m2){
    return FunUtil.compareHierarchically(m1, m2, post);
}


}
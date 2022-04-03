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
public class HierarchizeTupleComparator extends TupleComparator{

 private  boolean post;

HierarchizeTupleComparator(int arity, boolean post) {
    super(arity);
    this.post = post;
}
public int compare(List<Member> a1,List<Member> a2){
    for (int i = 0; i < arity; i++) {
        Member m1 = a1.get(i), m2 = a2.get(i);
        int c = FunUtil.compareHierarchically(m1, m2, post);
        if (c != 0) {
            return c;
        }
    }
    return 0;
}


}
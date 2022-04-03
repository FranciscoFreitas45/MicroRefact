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
public class HierarchicalTupleKeyComparator extends TupleExpMemoComparator{

HierarchicalTupleKeyComparator(Evaluator e, Calc calc, int arity) {
    super(e, calc, arity);
}
public int compareMemberOrderKeysHierarchically(OrderKey k1,OrderKey k2){
    // null is less than anything else
    if (k1 == Util.nullValue) {
        return -1;
    }
    if (k2 == Util.nullValue) {
        return 1;
    }
    Member m1 = k1.member;
    Member m2 = k2.member;
    if (FunUtil.equals(m1, m2)) {
        return 0;
    }
    while (true) {
        int depth1 = m1.getDepth(), depth2 = m2.getDepth();
        if (depth1 < depth2) {
            m2 = m2.getParentMember();
            if (FunUtil.equals(m1, m2)) {
                return -1;
            }
        } else if (depth1 > depth2) {
            m1 = m1.getParentMember();
            if (FunUtil.equals(m1, m2)) {
                return 1;
            }
        } else {
            Member prev1 = m1, prev2 = m2;
            m1 = m1.getParentMember();
            m2 = m2.getParentMember();
            if (FunUtil.equals(m1, m2)) {
                OrderKey pk1 = new OrderKey(prev1);
                OrderKey pk2 = new OrderKey(prev2);
                return FunUtil.compareValues(pk1, pk2);
            }
        }
    }
}


public int compare(List<Member> a1,List<Member> a2){
    OrderKey k1 = (OrderKey) eval(a1);
    OrderKey k2 = (OrderKey) eval(a2);
    return compareMemberOrderKeysHierarchically(k1, k2);
}


}
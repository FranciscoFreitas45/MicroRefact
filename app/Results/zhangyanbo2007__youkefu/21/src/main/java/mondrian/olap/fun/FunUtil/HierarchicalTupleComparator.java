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
public class HierarchicalTupleComparator extends TupleExpComparator{

 private  boolean desc;

HierarchicalTupleComparator(Evaluator evaluator, Calc calc, int arity, boolean desc) {
    super(evaluator, calc, arity);
    this.desc = desc;
}
public int compare(List<Member> a1,List<Member> a2){
    int c = 0;
    final int savepoint = evaluator.savepoint();
    try {
        for (int i = 0; i < arity; i++) {
            Member m1 = a1.get(i), m2 = a2.get(i);
            c = compareHierarchicallyButSiblingsByValue(m1, m2);
            if (c != 0) {
                break;
            }
            // compareHierarchicallyButSiblingsByValue imposes a
            // total order
            assert m1.equals(m2);
            evaluator.setContext(m1);
        }
    } finally {
        evaluator.restore(savepoint);
    }
    return c;
}


public int compareHierarchicallyButSiblingsByValue(Member m1,Member m2){
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
                // including case where both parents are null
                int c = compareByValue(prev1, prev2);
                if (c == 0) {
                    c = FunUtil.compareSiblingMembers(prev1, prev2);
                }
                return desc ? -c : c;
            }
        }
    }
}


public int compareByValue(Member m1,Member m2){
    int c;
    final int savepoint = evaluator.savepoint();
    try {
        evaluator.setContext(m1);
        Object v1 = calc.evaluate(evaluator);
        evaluator.setContext(m2);
        Object v2 = calc.evaluate(evaluator);
        c = FunUtil.compareValues(v1, v2);
        return c;
    } finally {
        // important to restore the evaluator state
        evaluator.restore(savepoint);
    }
}


}
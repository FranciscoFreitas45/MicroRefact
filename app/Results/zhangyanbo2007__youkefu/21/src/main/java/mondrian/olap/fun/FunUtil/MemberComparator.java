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
public class MemberComparator implements Comparator<Member>{

 private  Logger LOGGER;

 final  Evaluator evaluator;

 final  Calc exp;

 private  int descMask;

 private  Map<Member,Object> valueMap;

MemberComparator(Evaluator evaluator, Calc exp, boolean desc) {
    this.evaluator = evaluator;
    this.exp = exp;
    this.descMask = desc ? -1 : 1;
    this.valueMap = new HashMap<Member, Object>();
}
public int compare(Member m1,Member m2){
    final int c = comparator.compare(m1, m2);
    // here guaranteed that eval(m) finds a memorized value
    LOGGER.debug("compare " + m1.getUniqueName() + "(" + eval(m1) + "), " + m2.getUniqueName() + "(" + eval(m2) + ")" + " yields " + c);
    return c;
}


public Object eval(Member m){
    Object val = valueMap.get(m);
    if (val == null) {
        evaluator.setContext(m);
        val = exp.evaluate(evaluator);
        if (val == null) {
            val = Util.nullValue;
        }
        valueMap.put(m, val);
    }
    return val;
}


public int maybeNegate(int c){
    return descMask * c;
}


public int compareHierarchicallyButSiblingsByValue(Member m1,Member m2){
    if (FunUtil.equals(m1, m2)) {
        return 0;
    }
    while (true) {
        int depth1 = m1.getDepth(), depth2 = m2.getDepth();
        if (depth1 < depth2) {
            m2 = m2.getParentMember();
            if (Util.equals(m1, m2)) {
                return -1;
            }
        } else if (depth1 > depth2) {
            m1 = m1.getParentMember();
            if (Util.equals(m1, m2)) {
                return 1;
            }
        } else {
            Member prev1 = m1, prev2 = m2;
            m1 = m1.getParentMember();
            m2 = m2.getParentMember();
            if (Util.equals(m1, m2)) {
                // including case where both parents are null
                int c = compareByValue(prev1, prev2);
                if (c != 0) {
                    return c;
                }
                // prev1 and prev2 are siblings.  Order according to
                // hierarchy, if the values do not differ.  Needed to
                // have a consistent sortMembers if members with equal
                // (null!)  values are compared.
                c = FunUtil.compareSiblingMembers(prev1, prev2);
                // Do not negate c, even if we are sorting descending.
                // This comparison is to achieve the 'natural order'.
                return c;
            }
        }
    }
}


public void preloadValues(Collection<Member> members){
    for (Member m : members) {
        eval(m);
    }
}


public int compareByValue(Member m1,Member m2){
    final int c = FunUtil.compareValues(eval(m1), eval(m2));
    return maybeNegate(c);
}


public Comparator<Member> wrap(){
    final MemberComparator comparator = this;
    if (LOGGER.isDebugEnabled()) {
        return new Comparator<Member>() {

            public int compare(Member m1, Member m2) {
                final int c = comparator.compare(m1, m2);
                // here guaranteed that eval(m) finds a memorized value
                LOGGER.debug("compare " + m1.getUniqueName() + "(" + eval(m1) + "), " + m2.getUniqueName() + "(" + eval(m2) + ")" + " yields " + c);
                return c;
            }
        };
    } else {
        return this;
    }
}


}
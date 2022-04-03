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
public class HierarchicalMemberComparator extends MemberComparator{

HierarchicalMemberComparator(Evaluator evaluator, Calc exp, boolean desc) {
    super(evaluator, exp, desc);
}
public int compare(Member m1,Member m2){
    return compareHierarchicallyButSiblingsByValue(m1, m2);
}


}
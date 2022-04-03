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
public class BreakTupleComparator extends TupleExpMemoComparator{

BreakTupleComparator(Evaluator e, Calc calc, int arity) {
    super(e, calc, arity);
}
public int compare(List<Member> a1,List<Member> a2){
    return FunUtil.compareValues(eval(a1), eval(a2));
}


}
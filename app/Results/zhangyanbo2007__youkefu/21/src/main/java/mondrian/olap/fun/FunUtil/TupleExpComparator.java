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
public class TupleExpComparator extends TupleComparator{

 private Evaluator evaluator;

 final  Calc calc;

TupleExpComparator(Evaluator evaluator, Calc calc, int arity) {
    super(arity);
    this.evaluator = evaluator;
    this.calc = calc;
}
}
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
public class TupleExpMemoComparator extends TupleExpComparator{

 private  Map<List<Member>,Object> valueMap;

TupleExpMemoComparator(Evaluator e, Calc calc, int arity) {
    super(e, calc, arity);
}
public Object compute(List<Member> key){
    evaluator.setContext(key);
    Object val = calc.evaluate(evaluator);
    if (val == null) {
        val = Util.nullValue;
    }
    valueMap.put(key, val);
    return val;
}


public Object eval(List<Member> t){
    Object val = valueMap.get(t);
    if (val != null) {
        return val;
    }
    return compute(t);
}


public void preloadValues(TupleList tuples){
    for (List<Member> t : tuples) {
        compute(t);
    }
}


}
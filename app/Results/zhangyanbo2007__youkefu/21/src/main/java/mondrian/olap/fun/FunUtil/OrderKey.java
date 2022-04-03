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
public class OrderKey implements Comparable{

 private  Member member;

public OrderKey(Member member) {
    super();
    this.member = member;
}
public int compareTo(Object o){
    assert o instanceof OrderKey;
    Member otherMember = ((OrderKey) o).member;
    final boolean thisCalculated = this.member.isCalculatedInQuery();
    final boolean otherCalculated = otherMember.isCalculatedInQuery();
    if (thisCalculated) {
        if (!otherCalculated) {
            return 1;
        }
    } else {
        if (otherCalculated) {
            return -1;
        }
    }
    final Comparable thisKey = this.member.getOrderKey();
    final Comparable otherKey = otherMember.getOrderKey();
    if ((thisKey != null) && (otherKey != null)) {
        return thisKey.compareTo(otherKey);
    } else {
        return this.member.compareTo(otherMember);
    }
}


}
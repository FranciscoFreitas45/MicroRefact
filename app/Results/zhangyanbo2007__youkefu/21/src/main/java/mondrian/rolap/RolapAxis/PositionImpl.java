package mondrian.rolap.RolapAxis;
 import mondrian.calc.TupleList;
import mondrian.olap;
import java.util.AbstractList;
import java.util.List;
public class PositionImpl extends AbstractList<Member>implements Position{

 private  TupleList tupleList;

 private  int offset;

PositionImpl(TupleList tupleList, int offset) {
    this.tupleList = tupleList;
    this.offset = offset;
}
public int size(){
    return tupleList.getArity();
}


public Member get(int index){
    return tupleList.get(index, offset);
}


}
package mondrian.rolap;
 import mondrian.calc.TupleList;
import mondrian.olap;
import java.util.AbstractList;
import java.util.List;
public class RolapAxis implements Axis{

 private  TupleList list;

 private  TupleList list;

 private  TupleList tupleList;

 private  int offset;

public RolapAxis(TupleList list) {
    this.list = list;
}
public TupleList getTupleList(){
    return list;
}


public int size(){
    return tupleList.getArity();
}


public Member get(int index){
    return tupleList.get(index, offset);
}


public boolean isEmpty(){
    // may be considerably cheaper than computing size
    return list.isEmpty();
}


public List<Position> getPositions(){
    return new PositionList(list);
}


public String toString(List<Position> pl){
    StringBuilder buf = new StringBuilder();
    for (Position p : pl) {
        buf.append('{');
        boolean firstTime = true;
        for (Member m : p) {
            if (!firstTime) {
                buf.append(", ");
            }
            buf.append(m.getUniqueName());
            firstTime = false;
        }
        buf.append('}');
        buf.append('\n');
    }
    return buf.toString();
}


@Override
public int getDataSize(){
    // TODO Auto-generated method stub
    return list.getCount();
}


}
package mondrian.rolap.RolapAxis;
 import mondrian.calc.TupleList;
import mondrian.olap;
import java.util.AbstractList;
import java.util.List;
public class PositionList extends AbstractList<Position>{

 private  TupleList list;

PositionList(TupleList list) {
    this.list = list;
}
public int size(){
    return list.size();
}


public Position get(int index){
    return new PositionImpl(list, index);
}


public boolean isEmpty(){
    // may be considerably cheaper than computing size
    return list.isEmpty();
}


}
package mondrian.calc;
 import mondrian.olap.Member;
import java.util.List;
public interface TupleList extends List<List<Member>>, TupleIterable{


public void addCurrent(TupleCursor tupleIter)
;

public TupleList subList(int fromIndex,int toIndex)
;

public TupleList fix()
;

public void onPosition(int index)
;

public List<Member> slice(int column)
;

public Member get(int slice,int index)
;

public TupleList withPositionCallback(PositionCallback positionCallback)
;

public TupleList cloneList(int capacity)
;

public TupleList project(int[] destIndices)
;

public void addTuple(Member members)
;

public int getCount()
;

public void setCount(int count)
;

}
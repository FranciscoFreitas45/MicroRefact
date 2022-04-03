package mondrian.calc.impl;
 import mondrian.calc.TupleIterator;
import mondrian.calc.TupleList;
import mondrian.olap.Member;
import mondrian.olap.Util;
import java.util;
public class ListTupleList extends AbstractEndToEndTupleList{

 private  List<Member> list;

 private  int count;

/**
 * Creates a ListTupleList.
 *
 * @param arity Arity
 * @param list Backing list
 */
public ListTupleList(int arity, List<Member> list) {
    super(arity);
    this.list = list;
}
public void add(int index,List<Member> element){
    assert mutable;
    list.addAll(index * arity, element);
}


@Override
public void clear(){
    assert mutable;
    list.clear();
}


public TupleIterator tupleIteratorInternal(){
    return new AbstractTupleListIterator();
}


public TupleList cloneList(int capacity){
    return new ListTupleList(arity, capacity < 0 ? new ArrayList<Member>(list) : new ArrayList<Member>(capacity * arity));
}


@Override
public List<Member> remove(int index){
    assert mutable;
    for (int i = 0, n = index * arity; i < arity; i++) {
        list.remove(n);
    }
    // breach of List contract
    return null;
}


@Override
public int size(){
    return ListTupleList.this.size();
}


public List<Member> slice(int column){
    if (column < 0 || column >= arity) {
        throw new IllegalArgumentException();
    }
    return new AbstractList<Member>() {

        @Override
        public Member get(int index) {
            return ListTupleList.this.get(column, index);
        }

        @Override
        public int size() {
            return ListTupleList.this.size();
        }
    };
}


@Override
public Member get(int index){
    return ListTupleList.this.get(column, index);
}


@Override
public void removeRange(int fromIndex,int toIndex){
    assert mutable;
    list.subList(fromIndex * arity, toIndex * arity).clear();
}


public void addTuple(Member members){
    assert mutable;
    list.addAll(Arrays.asList(members));
}


@Override
public int getCount(){
    // TODO Auto-generated method stub
    return count;
}


public List<Member> backingList(){
    return list;
}


@Override
public void setCount(int count){
    // TODO Auto-generated method stub
    this.count = count;
}


}
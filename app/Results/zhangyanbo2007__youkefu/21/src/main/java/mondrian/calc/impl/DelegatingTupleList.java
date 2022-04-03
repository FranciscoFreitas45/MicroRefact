package mondrian.calc.impl;
 import mondrian.calc.TupleIterator;
import mondrian.calc.TupleList;
import mondrian.olap.Member;
import mondrian.olap.Util;
import java.util;
public class DelegatingTupleList extends AbstractTupleList{

 private  List<List<Member>> list;

 private  int count;

/**
 * Creates a DelegatingTupleList.
 *
 * @param arity Arity
 * @param list Backing list
 */
public DelegatingTupleList(int arity, List<List<Member>> list) {
    super(arity);
    this.list = list;
    assert list.isEmpty() || (list.get(0) instanceof List && (list.get(0).isEmpty() || list.get(0).get(0) == null || list.get(0).get(0) instanceof Member)) : "sanity check failed: " + list;
}
@Override
public void add(int index,List<Member> element){
    positionCallback.onPosition(index);
    list.add(index, element);
}


@Override
public TupleList subList(int fromIndex,int toIndex){
    return new DelegatingTupleList(arity, list.subList(fromIndex, toIndex));
}


@Override
public List<Member> set(int index,List<Member> element){
    positionCallback.onPosition(index);
    return list.set(index, element);
}


public TupleList withPositionCallback(PositionCallback positionCallback){
    return new DelegatingTupleList(arity, new AbstractList<List<Member>>() {

        @Override
        public List<Member> get(int index) {
            positionCallback.onPosition(index);
            return list.get(index);
        }

        @Override
        public int size() {
            return list.size();
        }

        @Override
        public List<Member> set(int index, List<Member> element) {
            positionCallback.onPosition(index);
            return list.set(index, element);
        }

        @Override
        public void add(int index, List<Member> element) {
            positionCallback.onPosition(index);
            list.add(index, element);
        }

        @Override
        public List<Member> remove(int index) {
            positionCallback.onPosition(index);
            return list.remove(index);
        }
    });
}


@Override
public TupleIterator tupleIteratorInternal(){
    return new AbstractTupleListIterator();
}


public TupleList cloneList(int capacity){
    return new DelegatingTupleList(arity, capacity < 0 ? new ArrayList<List<Member>>(list) : new ArrayList<List<Member>>(capacity));
}


public TupleList project(int[] destIndices){
    return new DelegatingTupleList(destIndices.length, new AbstractList<List<Member>>() {

        public List<Member> get(final int index) {
            return new AbstractList<Member>() {

                public Member get(int column) {
                    return list.get(index).get(destIndices[column]);
                }

                public int size() {
                    return destIndices.length;
                }

                public Member set(int column, Member element) {
                    return list.get(index).set(index, element);
                }
            };
        }

        public List<Member> set(int index, List<Member> element) {
            return list.set(index, element);
        }

        public int size() {
            return list.size();
        }
    });
}


@Override
public List<Member> remove(int index){
    positionCallback.onPosition(index);
    return list.remove(index);
}


@Override
public int size(){
    return list.size();
}


public List<Member> slice(int column){
    return new AbstractList<Member>() {

        @Override
        public Member get(int index) {
            return list.get(index).get(column);
        }

        @Override
        public int size() {
            return list.size();
        }

        public Member set(int index, Member element) {
            List<Member> subList = list.get(index);
            if (subList.size() == 1) {
                // The sub list is probably a singleton list.
                // calling set() on it will fail. We have to
                // create a new singleton list.
                return list.set(index, Collections.singletonList(element)).get(0);
            }
            return subList.set(column, element);
        }
    };
}


@Override
public List<Member> get(int index){
    positionCallback.onPosition(index);
    return list.get(index);
}


public void addTuple(Member members){
    list.add(Util.flatList(members));
}


@Override
public int getCount(){
    // TODO Auto-generated method stub
    return count;
}


@Override
public void setCount(int count){
    // TODO Auto-generated method stub
    this.count = count;
}


}
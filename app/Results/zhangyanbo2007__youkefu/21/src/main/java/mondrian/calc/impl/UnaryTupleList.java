package mondrian.calc.impl;
 import mondrian.calc;
import mondrian.olap.Evaluator;
import mondrian.olap.Member;
import java.util;
public class UnaryTupleList extends AbstractList<List<Member>>implements TupleList{

 final  List<Member> list;

 private  int count;

 private int cursor;

 private int lastRet;

/**
 * Creates an empty UnaryTupleList.
 */
public UnaryTupleList() {
    this(new ArrayList<Member>());
}/**
 * Creates a UnaryTupleList with a given backing list.
 *
 * @param list Backing list
 */
public UnaryTupleList(List<Member> list) {
    this.list = list;
}
public List<Member> next(){
    try {
        List<Member> next = get(cursor);
        lastRet = cursor++;
        return next;
    } catch (IndexOutOfBoundsException e) {
        throw new NoSuchElementException();
    }
}


public TupleList withPositionCallback(PositionCallback positionCallback){
    return new UnaryTupleList(new AbstractList<Member>() {

        public Member get(int index) {
            positionCallback.onPosition(index);
            return list.get(index);
        }

        public int size() {
            return list.size();
        }

        public Member set(int index, Member element) {
            positionCallback.onPosition(index);
            return list.set(index, element);
        }

        public void add(int index, Member element) {
            positionCallback.onPosition(index);
            list.add(index, element);
        }

        public Member remove(int index) {
            positionCallback.onPosition(index);
            return list.remove(index);
        }
    });
}


public TupleList project(int[] destIndices){
    // REVIEW: Is 0-ary valid?
    assert destIndices.length == 1;
    assert destIndices[0] == 0;
    return this;
}


public void remove(){
    if (lastRet == -1) {
        throw new IllegalStateException();
    }
    try {
        UnaryTupleList.this.remove(lastRet);
        if (lastRet < cursor) {
            cursor--;
        }
        lastRet = -1;
    } catch (IndexOutOfBoundsException e) {
        throw new ConcurrentModificationException();
    }
}


public int getArity(){
    return 1;
}


public TupleCursor tupleCursor(){
    return tupleIterator();
}


public Iterator<List<Member>> iterator(){
    return tupleIterator();
}


public List<Member> current(){
    return get(lastRet);
}


public TupleList fix(){
    return this;
}


public List<Member> slice(int column){
    return list;
}


public Member get(int index){
    positionCallback.onPosition(index);
    return list.get(index);
}


public void setContext(Evaluator evaluator){
    evaluator.setContext(list.get(lastRet));
}


public Member member(int column){
    assert column == 0;
    return list.get(lastRet);
}


public void addTuple(Member members){
    assert members.length == 1;
    list.add(members[0]);
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


public void add(int index,Member element){
    positionCallback.onPosition(index);
    list.add(index, element);
}


@Override
public TupleList subList(int fromIndex,int toIndex){
    return new ListTupleList(1, list.subList(fromIndex, toIndex));
}


public Member set(int index,Member element){
    positionCallback.onPosition(index);
    return list.set(index, element);
}


public boolean forward(){
    if (cursor == size()) {
        return false;
    }
    lastRet = cursor++;
    return true;
}


public void currentToArray(Member[] members,int offset){
    members[offset] = list.get(lastRet);
}


@Override
public void clear(){
    list.clear();
}


public TupleList cloneList(int capacity){
    return new UnaryTupleList(capacity < 0 ? new ArrayList<Member>(list) : new ArrayList<Member>(capacity));
}


public boolean hasNext(){
    return cursor != size();
}


public void addCurrent(TupleCursor tupleIter){
    list.add(tupleIter.member(0));
}


public int size(){
    return list.size();
}


public TupleIterator tupleIterator(){
    return new UnaryIterator();
}


}
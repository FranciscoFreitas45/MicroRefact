package mondrian.calc.impl.UnaryTupleList;
 import mondrian.calc;
import mondrian.olap.Evaluator;
import mondrian.olap.Member;
import java.util;
public class UnaryIterator implements TupleIterator{

 private int cursor;

 private int lastRet;


public List<Member> next(){
    try {
        List<Member> next = get(cursor);
        lastRet = cursor++;
        return next;
    } catch (IndexOutOfBoundsException e) {
        throw new NoSuchElementException();
    }
}


public List<Member> current(){
    return get(lastRet);
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


public void setContext(Evaluator evaluator){
    evaluator.setContext(list.get(lastRet));
}


public Member member(int column){
    assert column == 0;
    return list.get(lastRet);
}


public boolean hasNext(){
    return cursor != size();
}


public int getArity(){
    return 1;
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


}
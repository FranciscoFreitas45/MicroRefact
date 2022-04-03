package switchtwentytwenty.project.domain.share.id;
 import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class IDList {

@Getter
 private  List<K> list;

// Constructor Methods
/**
 * Sole constructor
 */
protected IDList() {
    this.list = new ArrayList<>();
}
public void add(K id){
    if (id == null) {
        throw new NullPointerException("Argument points to nothing in memory, i.e., is null");
    }
    this.list.add(id);
}


public boolean contains(K id){
    for (K idInList : list) {
        if (idInList.equals(id)) {
            return true;
        }
    }
    return false;
}


public int size(){
    return this.list.size();
}


public void addAll(List<K> idList){
    if (idList == null) {
        throw new NullPointerException("List points to nothing in memory, i.e., null");
    }
    this.list.addAll(idList);
}


@Override
public int hashCode(){
    return Objects.hash(list);
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    IDList idList1 = (IDList) o;
    return Objects.equals(list, idList1.list);
}


public boolean isEmpty(){
    return this.list.isEmpty();
}


public K getID(int i){
    return this.list.get(i);
}


public List<String> idToString(){
    List<String> idStringList = new ArrayList<>();
    for (K idInList : list) {
        String id = idInList.toString();
        idStringList.add(id);
    }
    return idStringList;
}


}
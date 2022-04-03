package com.gbcom.common.im.ds;
 import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
public class IDArray implements IIDNameArray{

 private  long serialVersionUID;

 private  IIDNameStruct[][] table;

 private  int count;

 private  int threshold;

 private  float loadFactor;

 private int index;

 private int elementIndex;

/**
 * 构造函数
 */
public IDArray() {
    this(11, 0.75f);
}/**
 * 构造函数
 *
 * @param initialCapacity
 *            初始容量
 * @param factor
 *            装载因素
 */
public IDArray(int initialCapacity, float factor) {
    this.table = new IIDNameStruct[initialCapacity][];
    this.loadFactor = factor;
    threshold = (int) (initialCapacity * loadFactor);
}/**
 * 构造函数
 *
 * @param datas
 *            要装载的数据
 */
public IDArray(ArrayList<IIDNameStruct> datas) {
    loadFactor = 0.75f;
    count = datas.size();
    int initialCapacity = 11;
    while (count > (threshold = (int) (loadFactor * initialCapacity))) {
        initialCapacity = initialCapacity * 2 + 1;
    }
    this.table = new IIDNameStruct[initialCapacity][];
    Vector[] temp = new Vector[initialCapacity];
    for (int i = 0; i < count; i++) {
        IIDNameStruct e = datas.get(i);
        int index = (e.getID() & 0x7FFFFFFF) % initialCapacity;
        Vector<IIDNameStruct> v = temp[index];
        if (v == null) {
            v = new Vector();
            temp[index] = v;
        }
        v.add(e);
    }
    for (int i = 0; i < initialCapacity; i++) {
        table[i] = IDNameStructUtil.sort(temp[i]);
    }
}
public Object next(){
    return nextElement();
}


public void writeObject(ObjectOutputStream s){
    s.defaultWriteObject();
    s.writeInt(table.length);
    for (int i = 0; i < table.length; i++) {
        IIDNameStruct[] temp = table[i];
        s.writeInt(temp == null ? 0 : temp.length);
        if (temp != null) {
            for (int j = 0; j < temp.length; j++) {
                s.writeObject(temp[j]);
            }
        }
    }
}


public void rehash(){
    int oldCapacity = table.length;
    IIDNameStruct[][] oldMap = table;
    int newCapacity = oldCapacity * 2 + 1;
    IIDNameStruct[][] newMap = new IIDNameStruct[newCapacity][];
    Vector[] temp = new Vector[newCapacity];
    threshold = (int) (newCapacity * loadFactor);
    table = newMap;
    for (int i = oldCapacity; i-- > 0; ) {
        IIDNameStruct[] old = oldMap[i];
        if (old != null) {
            for (int j = old.length - 1; j > -1; j--) {
                IIDNameStruct e = old[j];
                int index = e.getID() % newCapacity;
                Vector<IIDNameStruct> v = temp[index];
                if (v == null) {
                    v = new Vector();
                    temp[index] = v;
                }
                v.add(e);
            }
        }
    }
    for (int i = 0; i < newCapacity; i++) {
        newMap[i] = IDNameStructUtil.sort(temp[i]);
    }
}


@Override
public IIDNameStruct findFirst(String name){
    for (int i = 0; i < table.length; i++) {
        IIDNameStruct[] e = table[i];
        if (e != null) {
            for (int j = 0; j < e.length; j++) {
                if (name.equals(e[j].getName())) {
                    return e[j];
                }
            }
        }
    }
    return null;
}


public boolean hasNext(){
    return hasMoreElements();
}


public void put(IIDNameStruct data){
    if (count >= threshold) {
        // Rehash the table if the threshold is exceeded
        rehash();
    }
    count++;
    int index = (data.getID() & 0x7FFFFFFF) % table.length;
    table[index] = IDNameStructUtil.add(table[index], data);
}


public void remove(){
    int tempIndex = index;
    int tempElement = elementIndex;
    BAD: {
        if (--tempElement >= 0) {
            break BAD;
        } else {
            while (tempIndex-- >= 0) {
                if (table[tempIndex] != null && table[tempIndex].length > 0) {
                    tempElement = table[tempIndex].length - 1;
                    break BAD;
                }
            }
        }
        return;
    }
    IIDNameStruct[] datas = table[tempIndex];
    IIDNameStruct[] newDatas = new IIDNameStruct[datas.length - 1];
    System.arraycopy(datas, 0, newDatas, 0, tempElement);
    System.arraycopy(datas, tempElement + 1, newDatas, tempElement, newDatas.length - tempElement);
    table[tempIndex] = newDatas;
    index = tempIndex;
    elementIndex = tempElement;
}


public void readObject(ObjectInputStream s){
    s.defaultReadObject();
    int length = s.readInt();
    table = new IIDNameStruct[length][];
    for (int i = 0; i < table.length; i++) {
        length = s.readInt();
        if (length != 0) {
            IIDNameStruct[] temp = new IIDNameStruct[length];
            for (int j = 0; j < temp.length; j++) {
                temp[j] = (IIDNameStruct) s.readObject();
            }
            table[i] = temp;
        }
    }
}


@Override
public Iterator iterator(){
    return new Enumerator();
}


@Override
public int size(){
    return count;
}


public boolean hasMoreElements(){
    if (index >= table.length) {
        return false;
    }
    if (table[index].length > elementIndex) {
        return true;
    }
    elementIndex = 0;
    for (index++; index < table.length; index++) {
        if (table[index] != null && table[index].length > 0) {
            return true;
        }
    }
    return false;
}


public IIDNameStruct[] get(){
    IIDNameStruct[] result = new IIDNameStruct[count];
    int index = 0;
    for (int i = 0; i < table.length; i++) {
        IIDNameStruct[] entry = table[i];
        if (entry != null) {
            for (int j = entry.length - 1; j > -1; j--) {
                result[index++] = entry[j];
            }
        }
    }
    return result;
}


public Object nextElement(){
    if (hasMoreElements()) {
        return table[index][elementIndex++];
    }
    return null;
}


}
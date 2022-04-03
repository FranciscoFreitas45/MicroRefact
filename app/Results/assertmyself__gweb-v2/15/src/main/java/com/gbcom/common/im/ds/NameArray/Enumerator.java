package com.gbcom.common.im.ds.NameArray;
 import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
public class Enumerator implements Iterator{

 private int index;

 private int elementIndex;

/**
 * 构造函数
 */
Enumerator() {
    for (index = 0; index < table.length; index++) {
        if (table[index] != null && table[index].length > 0) {
            break;
        }
    }
}
public Object next(){
    return nextElement();
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


public boolean hasNext(){
    return hasMoreElements();
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


public Object nextElement(){
    if (hasMoreElements()) {
        return table[index][elementIndex++];
    }
    return null;
}


}
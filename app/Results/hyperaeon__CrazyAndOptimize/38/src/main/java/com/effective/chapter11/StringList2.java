package com.effective.chapter11;
 import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
public class StringList2 implements Serializable{

 private  int size;

 private  Entry head;

 private String data;

 private Entry next;

 private Entry previous;


public void add(String s){
}


public void writeObject(ObjectOutputStream s){
    s.defaultWriteObject();
    s.writeInt(size);
    for (Entry e = head; e != null; e = e.next) {
        s.writeObject(e.data);
    }
}


public void readObject(ObjectInputStream s){
    s.defaultReadObject();
    int numElements = s.readInt();
    for (int i = 0; i < numElements; i++) {
        add((String) s.readObject());
    }
}


}
package com.effective.chapter11;
 import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
public class Foo extends AbstractFoo{

 private  long serialVersionUID;

public Foo(int x, int y) {
    super(x, y);
}
public void writeObject(ObjectOutputStream oos){
    oos.defaultWriteObject();
    oos.writeInt(getX());
    oos.writeInt(getY());
}


public void readObject(ObjectInputStream ois){
    ois.defaultReadObject();
    int x = ois.readInt();
    int y = ois.readInt();
    initialize(x, y);
}


}
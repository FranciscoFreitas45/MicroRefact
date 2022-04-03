package com.crazy.chapter15.duplicate;
 import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
public class Person extends Humanimplements Serializable{

 private  long serialVersionUID;

 private  String name;

 private  int age;

public Person(String name, int age) {
    System.out.println("有参数的构造器");
    this.name = name;
    this.age = age;
}
public void setName(String name){
    this.name = name;
}


public int getAge(){
    return age;
}


public String getName(){
    return name;
}


public void writeObject(ObjectOutputStream out){
    out.writeObject(new StringBuffer(name).reverse());
    out.writeInt(age);
}


public Object writeReplace(){
    ArrayList<Object> list = new ArrayList<Object>();
    list.add(name);
    list.add(age);
    return list;
}


public void setAge(int age){
    this.age = age;
}


public void readObject(ObjectInputStream in){
    this.name = ((StringBuffer) in.readObject()).reverse().toString();
    this.age = in.readInt();
}


}
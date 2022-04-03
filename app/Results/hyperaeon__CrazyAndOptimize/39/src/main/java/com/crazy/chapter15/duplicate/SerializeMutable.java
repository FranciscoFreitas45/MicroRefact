package com.crazy.chapter15.duplicate;
 import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
public class SerializeMutable {


public void main(String[] args){
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Constants.basicPath + "mutable.txt"));
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constants.basicPath + "mutable.txt"))) {
        Person p = new Person("sunwukong", 500);
        oos.writeObject(p);
        p.setName("zhubajie");
        oos.writeObject(p);
        Person p1 = (Person) ois.readObject();
        Person p2 = (Person) ois.readObject();
        System.out.println("p1 and p2 same object? " + (p1 == p2));
        System.out.println("p2.getName(): " + p2.getName());
    } catch (Exception e) {
        e.printStackTrace();
    }
}


}
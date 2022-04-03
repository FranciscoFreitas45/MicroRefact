package com.crazy.chapter15.triple;
 import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
public class SerializableMutable {


public void main(String[] args){
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Constants.currentPath + "mutable.txt"));
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constants.currentPath + "mutable.txt"))) {
        Person per = new Person("wukong", 500);
        oos.writeObject(per);
        per.setName("pig");
        oos.writeObject(per);
        Person wukong = (Person) ois.readObject();
        Person pig = (Person) ois.readObject();
        System.out.println(wukong == pig);
        System.out.println(pig.getName());
    } catch (Exception e) {
        e.printStackTrace();
    }
}


}
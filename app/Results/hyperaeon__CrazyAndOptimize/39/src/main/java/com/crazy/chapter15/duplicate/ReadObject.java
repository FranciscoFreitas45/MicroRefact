package com.crazy.chapter15.duplicate;
 import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
public class ReadObject {


public void main(String[] args){
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constants.basicPath + "object.txt"))) {
        Person p = (Person) ois.readObject();
        System.out.println(p.getName() + " " + p.getAge() + " " + p.getArm());
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
}


}
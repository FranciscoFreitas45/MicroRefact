package com.crazy.chapter15.duplicate;
 import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
public class WriteObject {


public void main(String[] args){
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Constants.basicPath + "object.txt"))) {
        Person p = new Person("liyong", 29);
        p.setArm(3);
        oos.writeObject(p);
    } catch (IOException e) {
        e.printStackTrace();
    }
}


}
package com.crazy.chapter15.triple;
 import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
public class WriteObject {


public void main(String[] args){
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"))) {
        Person person = new Person("孫悟空", 500);
        oos.writeObject(person);
    } catch (IOException e) {
        e.printStackTrace();
    }
}


}
package com.crazy.chapter15.triple;
 import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
public class ReadObject {


public void main(String[] args){
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"))) {
        Person person = (Person) ois.readObject();
        System.out.print(person.getName() + " - " + person.getAge());
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
}


}
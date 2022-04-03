package com.crazy.chapter15.triple;
 import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
public class WriterTeacher {


public void main(String[] args){
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("teacher.txt"))) {
        Person wukong = new Person("wukong", 500);
        Teacher puti = new Teacher("puti", wukong);
        Teacher tangsen = new Teacher("tangsen", wukong);
        oos.writeObject(wukong);
        oos.writeObject(puti);
        oos.writeObject(tangsen);
        oos.writeObject(puti);
    } catch (Exception e) {
        e.printStackTrace();
    }
}


}
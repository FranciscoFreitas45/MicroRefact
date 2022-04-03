package com.crazy.chapter15.duplicate;
 import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
public class WriteTeacher {


public void main(String[] args){
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Constants.basicPath + "writeTeacher.txt"))) {
        Person sunwukong = new Person("sunwukong", 600);
        Teacher puti = new Teacher("puti", sunwukong);
        Teacher tang = new Teacher("tang", sunwukong);
        oos.writeObject(puti);
        oos.writeObject(tang);
        oos.writeObject(sunwukong);
        oos.writeObject(tang);
    } catch (IOException e) {
        e.printStackTrace();
    }
}


}
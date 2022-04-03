package com.crazy.chapter15.triple;
 import java.io.FileInputStream;
import java.io.ObjectInputStream;
public class ReadTeacher {


public void main(String[] args){
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("teacher.txt"))) {
        Person wukong = (Person) ois.readObject();
        Teacher puti = (Teacher) ois.readObject();
        Teacher tangsen = (Teacher) ois.readObject();
        Teacher puti2 = (Teacher) ois.readObject();
        System.out.println("puti refer same wukong? " + (puti.getStudent() == wukong));
        System.out.println("tangsen refer same wukong? " + (tangsen.getStudent() == wukong));
        System.out.println("puti == puti2 " + (puti == puti2));
    } catch (Exception e) {
        e.printStackTrace();
    }
}


}
package com.crazy.chapter15.duplicate;
 import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
public class ReadTeacher {


public void main(String[] args){
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constants.basicPath + "writeTeacher.txt"))) {
        Teacher t1 = (Teacher) ois.readObject();
        Teacher t2 = (Teacher) ois.readObject();
        Person p = (Person) ois.readObject();
        Teacher t3 = (Teacher) ois.readObject();
        System.out.println("t1的student引用和p是否相同：" + (t1.getStudent() == p));
        System.out.println("t2的student引用和p是否相同：" + (t2.getStudent() == p));
        System.out.println("t2和t3是否同一对象：" + (t2 == t3));
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


}
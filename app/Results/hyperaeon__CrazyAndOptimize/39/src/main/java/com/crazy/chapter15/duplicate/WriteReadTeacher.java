package com.crazy.chapter15.duplicate;
 import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
public class WriteReadTeacher {


public void main(String[] args){
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Constants.basicPath + "teacher.txt"));
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Constants.basicPath + "teacher.txt"))) {
        Person student = new Person("liyong", 29);
        Teacher teacher = new Teacher("yongli", student);
        oos.writeObject(teacher);
        Teacher t = (Teacher) ois.readObject();
        System.out.println(t.getName() + " " + t.getStudent().getName() + " " + t.getStudent().getAge());
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
}


}
package com.crazy.chapter15.duplicate;
 import java.io.Serializable;
public class Teacher implements Serializable{

 private  long serialVersionUID;

 private  String name;

 private  Person student;

public Teacher(String name, Person student) {
    this.name = name;
    this.student = student;
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setStudent(Person student){
    this.student = student;
}


public Person getStudent(){
    return student;
}


}
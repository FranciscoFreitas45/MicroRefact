package com.crazy.chapter15.triple;
 import java.io.Serializable;
public class Person implements Serializable{

 private  long serialVersionUID;

 private  String name;

 private  int age;

public Person(String name, int age) {
    System.out.println("有参构造函数");
    this.name = name;
    this.age = age;
}
public void setName(String name){
    this.name = name;
}


public int getAge(){
    return age;
}


public String getName(){
    return name;
}


public void setAge(int age){
    this.age = age;
}


}
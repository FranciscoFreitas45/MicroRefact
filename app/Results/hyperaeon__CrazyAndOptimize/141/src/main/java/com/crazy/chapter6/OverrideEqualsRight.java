package com.crazy.chapter6;
 public class OverrideEqualsRight {


public void main(String[] args){
    Person person1 = new Person("������", "123456");
    Person person2 = new Person("������", "123456");
    Person person3 = new Person("����", "654321");
    System.out.println(person1.equals(person2));
    System.out.println(person1.equals(person3));
    System.out.println(person2.equals(person3));
}


}
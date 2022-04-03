package com.crazy.chapter16;
 public class OverrideEqualsError {


public void main(String[] args){
    Person p = new Person();
    Dog d = new Dog();
    System.out.println(p.equals(d));
    System.out.println(d.equals(p));
}


}
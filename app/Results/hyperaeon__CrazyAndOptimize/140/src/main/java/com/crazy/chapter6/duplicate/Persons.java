package com.crazy.chapter6.duplicate;
 public class Persons {

 private  Name name;

public Persons(Name name) {
    // this.name = name;
    this.name = new Name(name.getFirstName(), name.getLastName());
}
public Name getName(){
    // return name;
    return new Name(name.getFirstName(), name.getLastName());
}


public void main(String[] args){
    Name n = new Name("���", "��");
    Persons p = new Persons(n);
    System.out.println(p.getName().getFirstName());
    n.setFirstName("�˽�");
    System.out.println(p.getName().getFirstName());
}


}
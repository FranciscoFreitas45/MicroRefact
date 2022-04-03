package com.crazy.chapter7;
 public class User implements Cloneable{

 private int age;

 private Address address;

public User(int age) {
    address = new Address("�й�����");
}
public User clone(){
    User user = (User) super.clone();
    user.address = address.clone();
    return user;
}


}
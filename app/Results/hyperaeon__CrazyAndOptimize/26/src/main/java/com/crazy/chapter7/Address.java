package com.crazy.chapter7;
 public class Address implements Cloneable{

 private String detail;

public Address(String detail) {
    this.detail = detail;
}
public Address clone(){
    return (Address) super.clone();
}


}
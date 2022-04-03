package com.designpattern.visitor;
 public class MySubject implements Subject{


@Override
public String getSubject(){
    return "love";
}


@Override
public void accepte(Visitor visitor){
    visitor.visitor(this);
}


}
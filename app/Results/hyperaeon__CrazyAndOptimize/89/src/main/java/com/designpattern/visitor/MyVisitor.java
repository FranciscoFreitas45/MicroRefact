package com.designpattern.visitor;
 public class MyVisitor implements Visitor{


@Override
public void visitor(Subject subject){
    System.out.println("visit the subject: " + subject.getSubject());
}


}
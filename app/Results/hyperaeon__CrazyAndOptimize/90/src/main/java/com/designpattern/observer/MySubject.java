package com.designpattern.observer;
 public class MySubject extends AbstractSubject{


@Override
public void operation(){
    System.out.println("Update self");
    notifyObsevers();
}


}
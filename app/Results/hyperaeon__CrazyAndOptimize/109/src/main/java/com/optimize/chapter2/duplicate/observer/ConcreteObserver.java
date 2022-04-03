package com.optimize.chapter2.duplicate.observer;
 public class ConcreteObserver implements IObserver{


@Override
public void update(Event evt){
    System.out.println("observer recieves information");
}


}
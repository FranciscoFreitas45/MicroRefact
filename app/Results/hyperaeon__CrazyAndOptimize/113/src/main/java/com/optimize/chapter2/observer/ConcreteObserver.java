package com.optimize.chapter2.observer;
 import java.awt.Event;
public class ConcreteObserver implements IObserver{


@Override
public void update(Event event){
    System.out.println("observer receives information");
}


}
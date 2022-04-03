package com.optimize.chapter2.observer;
 import java.awt.Event;
import java.util.Vector;
public class ConcreteSubject implements ISubject{

 private Vector<IObserver> observers;


@Override
public void inform(){
    Event event = new Event(new Object(), 2, new Object());
    for (IObserver ob : observers) {
        ob.update(event);
    }
}


@Override
public void detach(IObserver observer){
    observers.removeElement(observer);
}


@Override
public void attach(IObserver observer){
    if (!observers.contains(observer)) {
        observers.addElement(observer);
    }
}


}
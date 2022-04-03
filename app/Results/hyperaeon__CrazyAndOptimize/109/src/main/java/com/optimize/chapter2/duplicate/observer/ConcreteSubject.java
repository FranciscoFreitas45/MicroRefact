package com.optimize.chapter2.duplicate.observer;
 import java.util.Vector;
public class ConcreteSubject implements ISubject{

 private Vector<IObserver> observers;


@Override
public void inform(){
    Event evt = new Event();
    for (IObserver ob : observers) {
        ob.update(evt);
    }
}


@Override
public void dettach(IObserver observer){
    observers.removeElement(observer);
}


@Override
public void attach(IObserver observer){
    observers.addElement(observer);
}


}
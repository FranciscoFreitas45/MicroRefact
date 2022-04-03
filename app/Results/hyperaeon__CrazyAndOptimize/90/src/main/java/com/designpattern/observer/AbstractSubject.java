package com.designpattern.observer;
 import java.util.Enumeration;
import java.util.Vector;
public class AbstractSubject implements Subject{

 private  Vector<Observer> vector;


public void add(Observer observer){
    vector.add(observer);
}


public void notifyObsevers(){
    Enumeration<Observer> enums = vector.elements();
    while (enums.hasMoreElements()) {
        enums.nextElement().update();
    }
}


public void del(Observer obsever){
    vector.remove(obsever);
}


}
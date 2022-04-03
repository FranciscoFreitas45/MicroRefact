package com.optimize.chapter2.observer;
 public interface ISubject {


public void inform()
;

public void detach(IObserver observer)
;

public void attach(IObserver observer)
;

}
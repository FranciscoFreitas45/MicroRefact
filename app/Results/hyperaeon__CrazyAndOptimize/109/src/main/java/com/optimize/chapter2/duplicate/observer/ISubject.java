package com.optimize.chapter2.duplicate.observer;
 public interface ISubject {


public void inform()
;

public void dettach(IObserver observer)
;

public void attach(IObserver observer)
;

}
package com.designpattern.mediator.duplicate;
 public class User {

 private  Mediator mediator;

public User(Mediator mediator) {
    this.mediator = mediator;
}
public Mediator getMediator(){
    return mediator;
}


public void work()


}
package com.designpattern.mediator.duplicate;
 public class User1 extends User{

public User1(Mediator mediator) {
    super(mediator);
}
@Override
public void work(){
    System.out.println("User1 work!");
}


}
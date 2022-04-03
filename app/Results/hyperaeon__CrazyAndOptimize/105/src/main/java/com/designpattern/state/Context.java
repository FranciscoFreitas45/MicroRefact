package com.designpattern.state;
 public class Context {

 private  State state;

public Context(State state) {
    this.state = state;
}
public State getState(){
    return state;
}


public void method(){
    if (state.getValue().equals("state1")) {
        state.method1();
    } else if (state.getValue().equals("state2")) {
        state.method2();
    }
}


public void setState(State state){
    this.state = state;
}


}
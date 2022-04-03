package com.designpattern.state.duplicate;
 public class Context {

 private  State state;

public Context(State state) {
    this.state = state;
}
public State getState(){
    return state;
}


public void method(){
    if (state.getValue().equals("method1")) {
        state.method1();
    } else {
        state.method2();
    }
}


public void setState(State state){
    this.state = state;
}


}
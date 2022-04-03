package com.effective.chapter11;
 import java.util.concurrent.atomic.AtomicReference;
public class AbstractFoo {

 private  int x;

 private  AtomicReference<State> init;

public AbstractFoo(int x, int y) {
    initialize(x, y);
}protected AbstractFoo() {
}
public int getX(){
    checkInit();
    return x;
}


public int getY(){
    checkInit();
    return y;
}


public void checkInit(){
    if (init.get() != State.INITIALIZED) {
        throw new IllegalStateException("Uninitialized");
    }
}


public void initialize(int x,int y){
    if (!init.compareAndSet(State.NEW, State.INITIALIZING)) {
        throw new IllegalStateException("Already initialized");
    }
    this.x = x;
    this.y = y;
    init.set(State.INITIALIZED);
}


}
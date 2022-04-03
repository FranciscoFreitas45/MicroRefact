package com.designpattern.bridge.duplicate;
 public class Bridge {

 private  Sourceable source;


public void setSource(Sourceable source){
    this.source = source;
}


public void method(){
    source.method();
}


public Sourceable getSource(){
    return source;
}


}
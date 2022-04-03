package com.designpattern.memento.duplicate;
 public class Original {

 private  String value;

public Original(String value) {
    this.value = value;
}
public Memento createMemeto(){
    return new Memento(value);
}


public void restoreValue(Memento memento){
    this.value = memento.getValue();
}


public String getValue(){
    return value;
}


public void setValue(String value){
    this.value = value;
}


}
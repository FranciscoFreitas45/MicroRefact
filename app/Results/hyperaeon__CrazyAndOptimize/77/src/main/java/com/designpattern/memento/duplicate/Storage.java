package com.designpattern.memento.duplicate;
 public class Storage {

 private  Memento memento;

public Storage(Memento memento) {
    this.memento = memento;
}
public void setMemento(Memento memento){
    this.memento = memento;
}


public Memento getMemento(){
    return memento;
}


}
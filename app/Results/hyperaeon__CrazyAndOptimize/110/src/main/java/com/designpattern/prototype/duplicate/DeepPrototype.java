package com.designpattern.prototype.duplicate;
 import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
public class DeepPrototype implements Serializable,Cloneable{

 private  long serialVersionUID;

 private  String string;

 private  SerializableObject obj;


public void setString(String string){
    this.string = string;
}


public void setObj(SerializableObject obj){
    this.obj = obj;
}


public Object deepClone(){
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(bos);
    oos.writeObject(this);
    ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
    ObjectInputStream ois = new ObjectInputStream(bis);
    return ois.readObject();
}


public SerializableObject getObj(){
    return obj;
}


public String getString(){
    return string;
}


}
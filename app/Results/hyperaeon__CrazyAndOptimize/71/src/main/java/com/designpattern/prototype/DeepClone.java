package com.designpattern.prototype;
 import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
public class DeepClone implements Serializable,Cloneable{

 private  long serialVersionUID;

 private  String deepName;

 private  PlainObject obj;

public DeepClone(String deepName, PlainObject po) {
    this.deepName = deepName;
    this.obj = po;
}
public String getDeepName(){
    return deepName;
}


public void setObj(PlainObject obj){
    this.obj = obj;
}


public Object clone(){
    DeepClone obj = (DeepClone) super.clone();
    PlainObject po = (PlainObject) obj.obj.clone();
    obj.setObj(po);
    return obj;
}


public Object deepClone(){
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(bos);
    oos.writeObject(this);
    ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
    ObjectInputStream ois = new ObjectInputStream(bis);
    return ois.readObject();
}


public PlainObject getObj(){
    return obj;
}


public void setDeepName(String deepName){
    this.deepName = deepName;
}


}
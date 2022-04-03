package org.russianpost.operationhistory.data;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Rtm02Parameter", propOrder = { "id", "name" })
public class Rtm02Parameter {

@XmlElement(name = "Id")
 protected  int id;

@XmlElement(name = "Name")
 protected  String name;


public void setName(String value){
    this.name = value;
}


public String getName(){
    return name;
}


public void setId(int value){
    this.id = value;
}


public int getId(){
    return id;
}


}
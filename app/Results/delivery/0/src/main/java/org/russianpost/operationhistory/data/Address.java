package org.russianpost.operationhistory.data;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Address", propOrder = { "index", "description" })
public class Address {

@XmlElement(name = "Index", required = true)
 protected  String index;

@XmlElement(name = "Description", required = true)
 protected  String description;


public String getIndex(){
    return index;
}


public void setIndex(String value){
    this.index = value;
}


public void setDescription(String value){
    this.description = value;
}


public String getDescription(){
    return description;
}


}
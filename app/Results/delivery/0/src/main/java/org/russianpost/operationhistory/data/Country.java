package org.russianpost.operationhistory.data;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Country", propOrder = { "id", "code2A", "code3A", "name", "nameRU", "nameEN" })
public class Country {

@XmlElement(name = "Id")
 protected  int id;

@XmlElement(name = "Code2A")
 protected  String code2A;

@XmlElement(name = "Code3A")
 protected  String code3A;

@XmlElement(name = "Name")
 protected  String name;

@XmlElement(name = "NameRU")
 protected  String nameRU;

@XmlElement(name = "NameEN")
 protected  String nameEN;


public String getCode2A(){
    return code2A;
}


public void setName(String value){
    this.name = value;
}


public String getNameRU(){
    return nameRU;
}


public String getCode3A(){
    return code3A;
}


public String getName(){
    return name;
}


public void setId(int value){
    this.id = value;
}


public void setCode2A(String value){
    this.code2A = value;
}


public int getId(){
    return id;
}


public void setCode3A(String value){
    this.code3A = value;
}


public void setNameRU(String value){
    this.nameRU = value;
}


public String getNameEN(){
    return nameEN;
}


public void setNameEN(String value){
    this.nameEN = value;
}


}
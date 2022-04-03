package org.russianpost.operationhistory.data.LanguageData;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class Language {

@XmlAttribute(name = "name")
 protected  String name;

@XmlAttribute(name = "code")
 protected  String code;


public void setName(String value){
    this.name = value;
}


public String getName(){
    return name;
}


public void setCode(String value){
    this.code = value;
}


public String getCode(){
    return code;
}


}
package org.russianpost.operationhistory.data;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "language" })
@XmlRootElement(name = "LanguageData")
public class LanguageData {

@XmlElement(name = "Language", required = true)
 protected  List<LanguageData.Language> language;

@XmlAttribute(name = "name")
 protected  String name;

@XmlAttribute(name = "code")
 protected  String code;


public void setName(String value){
    this.name = value;
}


public List<LanguageData.Language> getLanguage(){
    if (language == null) {
        language = new ArrayList<LanguageData.Language>();
    }
    return this.language;
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
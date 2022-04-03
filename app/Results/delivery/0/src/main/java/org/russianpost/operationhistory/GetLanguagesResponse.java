package org.russianpost.operationhistory;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.russianpost.operationhistory.data.LanguageData;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getLanguagesResponse", propOrder = { "languageData" })
public class GetLanguagesResponse {

@XmlElement(name = "LanguageData", namespace = "http://russianpost.org/operationhistory/data")
 protected  LanguageData languageData;


public void setLanguageData(LanguageData value){
    this.languageData = value;
}


public LanguageData getLanguageData(){
    return languageData;
}


}
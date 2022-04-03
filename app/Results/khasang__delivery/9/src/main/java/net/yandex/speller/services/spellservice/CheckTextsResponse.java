package net.yandex.speller.services.spellservice;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "arrayOfSpellResult" })
@XmlRootElement(name = "CheckTextsResponse")
public class CheckTextsResponse {

@XmlElement(name = "ArrayOfSpellResult", required = true)
 protected  ArrayOfSpellResult arrayOfSpellResult;


public void setArrayOfSpellResult(ArrayOfSpellResult value){
    this.arrayOfSpellResult = value;
}


public ArrayOfSpellResult getArrayOfSpellResult(){
    return arrayOfSpellResult;
}


}
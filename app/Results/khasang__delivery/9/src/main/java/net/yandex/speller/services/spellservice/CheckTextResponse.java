package net.yandex.speller.services.spellservice;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "spellResult" })
@XmlRootElement(name = "CheckTextResponse")
public class CheckTextResponse {

@XmlElement(name = "SpellResult", required = true)
 protected  SpellResult spellResult;


public void setSpellResult(SpellResult value){
    this.spellResult = value;
}


public SpellResult getSpellResult(){
    return spellResult;
}


}
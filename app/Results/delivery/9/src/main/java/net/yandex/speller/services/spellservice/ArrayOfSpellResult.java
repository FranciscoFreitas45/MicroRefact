package net.yandex.speller.services.spellservice;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSpellResult", propOrder = { "spellResult" })
public class ArrayOfSpellResult {

@XmlElement(name = "SpellResult")
 protected  List<SpellResult> spellResult;


public List<SpellResult> getSpellResult(){
    if (spellResult == null) {
        spellResult = new ArrayList<SpellResult>();
    }
    return this.spellResult;
}


}
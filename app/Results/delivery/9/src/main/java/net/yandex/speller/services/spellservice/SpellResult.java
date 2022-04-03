package net.yandex.speller.services.spellservice;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpellResult", propOrder = { "error" })
public class SpellResult {

 protected  List<SpellError> error;


public List<SpellError> getError(){
    if (error == null) {
        error = new ArrayList<SpellError>();
    }
    return this.error;
}


}
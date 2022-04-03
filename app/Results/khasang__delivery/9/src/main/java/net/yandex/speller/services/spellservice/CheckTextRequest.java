package net.yandex.speller.services.spellservice;
 import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "text" })
@XmlRootElement(name = "CheckTextRequest")
public class CheckTextRequest {

@XmlElement(required = true)
 protected  String text;

@XmlAttribute(name = "lang")
 protected  String lang;

@XmlAttribute(name = "options")
 protected  Integer options;

@XmlAttribute(name = "format")
 protected  String format;


public String getLang(){
    return lang;
}


public void setFormat(String value){
    this.format = value;
}


public String getText(){
    return text;
}


public void setOptions(Integer value){
    this.options = value;
}


public String getFormat(){
    if (format == null) {
        return "";
    } else {
        return format;
    }
}


public int getOptions(){
    if (options == null) {
        return 0;
    } else {
        return options;
    }
}


public void setLang(String value){
    this.lang = value;
}


public void setText(String value){
    this.text = value;
}


}
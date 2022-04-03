package net.yandex.speller.services.spellservice;
 import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpellError", propOrder = { "word", "s" })
public class SpellError {

@XmlElement(required = true)
 protected  String word;

 protected  List<String> s;

@XmlAttribute(name = "code", required = true)
 protected  int code;

@XmlAttribute(name = "pos", required = true)
 protected  int pos;

@XmlAttribute(name = "row", required = true)
 protected  int row;

@XmlAttribute(name = "col", required = true)
 protected  int col;

@XmlAttribute(name = "len", required = true)
 protected  int len;


public int getCol(){
    return col;
}


public String getWord(){
    return word;
}


public void setCode(int value){
    this.code = value;
}


public int getRow(){
    return row;
}


public List<String> getS(){
    if (s == null) {
        s = new ArrayList<String>();
    }
    return this.s;
}


public void setPos(int value){
    this.pos = value;
}


public void setLen(int value){
    this.len = value;
}


public int getPos(){
    return pos;
}


public int getLen(){
    return len;
}


public void setRow(int value){
    this.row = value;
}


public void setCol(int value){
    this.col = value;
}


public void setWord(String value){
    this.word = value;
}


public int getCode(){
    return code;
}


}
package com.gbcom.common.template.xml.am;
 import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
public class AlarmReasonBean implements Serializable{

 private  long serialVersionUID;

 private  int code;

 private  String info;


@XmlElement(name = "info")
public String getInfo(){
    return info;
}


public void setCode(int code){
    this.code = code;
}


public void setInfo(String info){
    this.info = info;
}


@XmlElement(name = "code")
public int getCode(){
    return code;
}


}
package com.gbcom.common.template.xml.oem;
 import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "model")
public class VendorModel {

 private  String sysModel;

 private  String venModel;


@XmlAttribute
public String getVenModel(){
    return venModel;
}


@XmlAttribute
public String getSysModel(){
    return sysModel;
}


public void setSysModel(String sysModel){
    this.sysModel = sysModel;
}


public void setVenModel(String venModel){
    this.venModel = venModel;
}


}
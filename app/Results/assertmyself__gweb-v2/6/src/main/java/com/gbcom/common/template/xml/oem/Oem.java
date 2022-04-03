package com.gbcom.common.template.xml.oem;
 import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "oem")
public class Oem implements Serializable{

 private  long serialVersionUID;

 private  String name;

 private  Vendor vendor;

 private  String pline;

 private  String locale;


public void setName(String name){
    this.name = name;
}


public void setVendor(Vendor vendor){
    this.vendor = vendor;
}


@XmlElement(name = "name")
public String getName(){
    return name;
}


public String getPline(){
    return pline;
}


public void setPline(String pline){
    this.pline = pline;
}


public Vendor getVendor(){
    return vendor;
}


@XmlElement(name = "locale")
public String getLocale(){
    return locale;
}


public void setLocale(String locale){
    this.locale = locale;
}


}
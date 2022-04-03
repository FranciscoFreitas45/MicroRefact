package com.gbcom.common.template.xml.oem;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "vendor")
public class Vendor implements Serializable{

 private  long serialVersionUID;

 private  String name;

 private  String alias;

 private  String vendorCode;

 private  String version;

 private  String product;

 private  String imgPath;

 private  boolean lit;

 private  List<VendorModel> models;

 private  List<String> services;


public void setName(String name){
    this.name = name;
}


public String getVersion(){
    return version;
}


public void setProduct(String product){
    this.product = product;
}


@XmlElementWrapper(name = "models")
@XmlElement(name = "model")
public List<VendorModel> getModels(){
    return models;
}


public String getProduct(){
    return product;
}


public String getName(){
    return name;
}


public void setVersion(String version){
    this.version = version;
}


public void setServices(List<String> services){
    this.services = services;
}


public void setVendorCode(String vendorCode){
    this.vendorCode = vendorCode;
}


public String getVendorCode(){
    return vendorCode;
}


public boolean isLit(){
    return lit;
}


public void setLit(boolean lit){
    this.lit = lit;
}


public void setImgPath(String imgPath){
    this.imgPath = imgPath;
}


public void setAlias(String alias){
    this.alias = alias;
}


@XmlElementWrapper(name = "services")
@XmlElement(name = "service")
public List<String> getServices(){
    return services;
}


public void setModels(List<VendorModel> models){
    this.models = models;
}


public String getAlias(){
    return alias;
}


public String getImgPath(){
    return imgPath;
}


}
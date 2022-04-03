package com.gbcom.common.template.xml.northful;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "north")
public class NorthConfig implements Serializable{

 private  long serialVersionUID;

 private  String name;


public void setName(String name){
    this.name = name;
}


@XmlElement(name = "name")
public String getName(){
    return name;
}


public List<String> getList(){
    List<String> list = new ArrayList<String>();
    String[] names = name.split(",");
    for (int i = 0; i < names.length; i++) {
        list.add(names[i]);
    }
    return list;
}


}
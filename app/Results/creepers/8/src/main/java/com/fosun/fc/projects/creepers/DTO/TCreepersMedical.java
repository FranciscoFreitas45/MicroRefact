package com.fosun.fc.projects.creepers.DTO;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
public class TCreepersMedical implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String content;

 private  String key;

 private  String type;

public TCreepersMedical() {
}
public String getKey(){
    return this.key;
}


public String getType(){
    return this.type;
}


public String getContent(){
    return this.content;
}


public long getId(){
    return this.id;
}


public void setKey(String key){
    this.key = key;
}


}
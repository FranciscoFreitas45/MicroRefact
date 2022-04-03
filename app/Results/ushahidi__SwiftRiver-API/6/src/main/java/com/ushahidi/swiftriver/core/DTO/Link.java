package com.ushahidi.swiftriver.core.DTO;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
public class Link implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String hash;

 private  String url;

public Link() {
}
public String getUrl(){
    return url;
}


public String getHash(){
    return hash;
}


public long getId(){
    return id;
}


}
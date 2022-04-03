package com.ushahidi.swiftriver.core.DTO;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
public class Tag implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String hash;

 private  String tag;

 private  String tagCanonical;

 private  String type;

public Tag() {
}
public String getHash(){
    return hash;
}


public String getType(){
    return type;
}


public String getTagCanonical(){
    return tagCanonical;
}


public long getId(){
    return id;
}


public String getTag(){
    return tag;
}


}
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


@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + ((hash == null) ? 0 : hash.hashCode());
    return result;
}


public String getTagCanonical(){
    return tagCanonical;
}


@Override
public boolean equals(Object obj){
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    Tag other = (Tag) obj;
    if (hash == null) {
        if (other.hash != null)
            return false;
    } else if (!hash.equals(other.hash))
        return false;
    return true;
}


public void setId(long id){
    this.id = id;
}


public void setHash(String hash){
    this.hash = hash;
}


public void setTag(String tag){
    this.tag = tag;
}


public long getId(){
    return id;
}


public String getTag(){
    return tag;
}


public void setTagCanonical(String tagCanonical){
    this.tagCanonical = tagCanonical;
}


public void setType(String type){
    this.type = type;
}


}
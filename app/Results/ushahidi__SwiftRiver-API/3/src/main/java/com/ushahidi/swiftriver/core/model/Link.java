package com.ushahidi.swiftriver.core.model;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
@Entity
@Table(name = "links")
public class Link implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.TABLE, generator = "Seq")
@TableGenerator(name = "Seq", table = "seq", pkColumnName = "name", valueColumnName = "id", pkColumnValue = "links")
 private  long id;

@Column(name = "hash", nullable = false)
 private  String hash;

@Column(name = "url", nullable = false)
 private  String url;

public Link() {
}
public String getUrl(){
    return url;
}


public String getHash(){
    return hash;
}


@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + ((hash == null) ? 0 : hash.hashCode());
    return result;
}


@Override
public boolean equals(Object obj){
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    Link other = (Link) obj;
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


public long getId(){
    return id;
}


public void setHash(String hash){
    this.hash = hash;
}


public void setUrl(String url){
    this.url = url;
}


}
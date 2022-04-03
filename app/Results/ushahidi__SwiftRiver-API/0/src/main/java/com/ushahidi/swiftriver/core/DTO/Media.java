package com.ushahidi.swiftriver.core.DTO;
 import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
public class Media {

 private  long id;

 private  String hash;

 private  String url;

 private  String type;

 private  List<MediaThumbnail> thumbnails;

public Media() {
}
public String getUrl(){
    return url;
}


public List<MediaThumbnail> getThumbnails(){
    return thumbnails;
}


public String getHash(){
    return hash;
}


public String getType(){
    return type;
}


@Override
public int hashCode(){
    return new HashCodeBuilder(17, 31).append(url).toHashCode();
}


@Override
public boolean equals(Object obj){
    if (obj == null)
        return false;
    if (obj == this)
        return true;
    if (obj.getClass() != getClass())
        return false;
    Media other = (Media) obj;
    return new EqualsBuilder().append(url, other.url).isEquals();
}


public void setId(long id){
    this.id = id;
}


public void setThumbnails(List<MediaThumbnail> thumbnails){
    this.thumbnails = thumbnails;
}


public void setHash(String hash){
    this.hash = hash;
}


public long getId(){
    return id;
}


public void setType(String type){
    this.type = type;
}


public void setUrl(String url){
    this.url = url;
}


}
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


public long getId(){
    return id;
}


}
package com.tech.models.entities;
 import com.tech.configurations.tools.NameCoder;
import com.tech.models.entities.embeddedIds.ImageComposite;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Entity
@IdClass(ImageComposite.class)
@NamedQueries({ @NamedQuery(name = "ImagesMod.findByUserid", query = "SELECT p FROM ImagesMod p WHERE p.userid = ?1"), @NamedQuery(name = "ImagesMod.findByHashtag", query = "SELECT p FROM ImagesMod p WHERE p.hashtag = ?1") // @NamedQuery(name = "ImagesMod.findByTimeStamp",query = "SELECT p FROM ImagesMod p WHERE p.tmstamp = ?1")
// @NamedQuery(name = "ImagesMod.findByLocation",query = "SELECT p FROM ImagesMod p WHERE p.location = ?1")
})
@Table(name = "images")
public class ImagesMod implements Serializable{

@Id
@NotNull
@Min(1)
 private  Long userid;

@Id
@NotNull
 private  Date tmstamp;

@Column(name = "images")
@NotNull
 private  String images;

@Column(name = "hashtag")
@NotNull
 private  long hashtag;

public ImagesMod() {
}public ImagesMod(Long userid) {
    this.userid = userid;
    this.tmstamp = new Date();
    try {
        this.hashtag = NameCoder.nameConverter(userid, tmstamp.hashCode());
        this.images = NameCoder.pathConverter(this.hashtag);
    } catch (NumberFormatException ex) {
        this.hashtag = tmstamp.hashCode();
        this.images = NameCoder.invalidPathConvertrer(this.hashtag);
    }
}
public Date getTimestamp(){
    return tmstamp;
}


public long getHashtag(){
    return hashtag;
}


public String getImagePath(){
    return images;
}


public void setImagePath(String data){
    this.images = data;
}


public long getUserID(){
    return userid;
}


}
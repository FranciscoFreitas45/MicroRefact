package net.shangtech.weixin.property.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import net.shangtech.ssh.core.base.IBaseEntity;
@Entity
@Table(name = "panorama")
public class Panorama extends IBaseEntity{

 private  long serialVersionUID;

@Column(name = "image_front")
 private  String imageFront;

@Column(name = "image_back")
 private  String imageBack;

@Column(name = "image_left")
 private  String imageLeft;

@Column(name = "image_right")
 private  String imageRight;

@Column(name = "image_top")
 private  String imageTop;

@Column(name = "image_bottom")
 private  String imageBottom;

@Column(name = "title")
 private  String title;

@Column(name = "music")
 private  String music;

@Column(name = "page_url")
 private  String pageUrl;


public String getImageBack(){
    return imageBack;
}


public void setImageBack(String imageBack){
    this.imageBack = imageBack;
}


public void setMusic(String music){
    this.music = music;
}


public String getImageFront(){
    return imageFront;
}


public void setImageLeft(String imageLeft){
    this.imageLeft = imageLeft;
}


public String getImageBottom(){
    return imageBottom;
}


public void setPageUrl(String pageUrl){
    this.pageUrl = pageUrl;
}


public void setImageTop(String imageTop){
    this.imageTop = imageTop;
}


public void setTitle(String title){
    this.title = title;
}


public void setImageRight(String imageRight){
    this.imageRight = imageRight;
}


public String getImageTop(){
    return imageTop;
}


public String getPageUrl(){
    return pageUrl;
}


public String getImageRight(){
    return imageRight;
}


public String getTitle(){
    return title;
}


public void setImageFront(String imageFront){
    this.imageFront = imageFront;
}


public void setImageBottom(String imageBottom){
    this.imageBottom = imageBottom;
}


public String getMusic(){
    return music;
}


public String getImageLeft(){
    return imageLeft;
}


}
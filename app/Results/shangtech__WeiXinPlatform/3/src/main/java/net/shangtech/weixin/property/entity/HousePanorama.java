package net.shangtech.weixin.property.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import net.shangtech.ssh.core.base.IBaseEntity;
@Entity
@Table(name = "house_panorama")
public class HousePanorama extends IBaseEntity{

 private  long serialVersionUID;

@Column(name = "house_id")
 private  Integer houseId;

@Column(name = "image_name")
 private  String imageName;

@Column(name = "sort")
 private  Integer sort;

@Column(name = "panorama_id")
 private  Integer panoramaId;

@Transient
 private  Panorama panorama;


public Integer getHouseId(){
    return houseId;
}


public void setSort(Integer sort){
    this.sort = sort;
}


public Integer getSort(){
    return sort;
}


public Integer getPanoramaId(){
    return panoramaId;
}


public String getImageName(){
    return imageName;
}


public void setImageName(String imageName){
    this.imageName = imageName;
}


public void setPanoramaId(Integer panoramaId){
    this.panoramaId = panoramaId;
}


public Panorama getPanorama(){
    return panorama;
}


public void setHouseId(Integer houseId){
    this.houseId = houseId;
}


public void setPanorama(Panorama panorama){
    this.panorama = panorama;
}


}
package net.shangtech.weixin.property.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import net.shangtech.ssh.core.base.IBaseEntity;
@Entity
@Table(name = "project_image")
public class ProjectImage extends IBaseEntity{

 private  long serialVersionUID;

@Column(name = "project_id")
 private  Integer projectId;

@Column(name = "image")
 private  String image;


public Integer getProjectId(){
    return projectId;
}


public void setProjectId(Integer projectId){
    this.projectId = projectId;
}


public String getImage(){
    return image;
}


public void setImage(String image){
    this.image = image;
}


}
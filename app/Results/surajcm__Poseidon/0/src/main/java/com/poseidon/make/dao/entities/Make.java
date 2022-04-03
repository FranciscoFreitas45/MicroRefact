package com.poseidon.make.dao.entities;
 import com.poseidon.init.entity.CommonEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "make")
public class Make extends CommonEntity{

@Column(name = "makeName")
 private  String makeName;

@Column(name = "description")
 private  String description;

@OneToMany(mappedBy = "make")
 private  List<Model> models;


public void setMakeName(String makeName){
    this.makeName = makeName;
}


public List<Model> getModels(){
    return models;
}


public void setDescription(String description){
    this.description = description;
}


public void setModels(List<Model> models){
    this.models = models;
}


public String getDescription(){
    return description;
}


public String getMakeName(){
    return makeName;
}


}
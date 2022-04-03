package com.poseidon.make.dao.entities;
 import com.poseidon.init.entity.CommonEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "model")
public class Model extends CommonEntity{

@Column(name = "modelId")
 private  Long modelId;

@Column(name = "modelName")
 private  String modelName;

@Column(name = "makeId")
 private  Long makeId;

@ManyToOne
@JoinColumn(name = "makeId", referencedColumnName = "id", insertable = false, updatable = false)
 private  Make make;


public Long getModelId(){
    return modelId;
}


public void setModelId(Long modelId){
    this.modelId = modelId;
}


public void setModelName(String modelName){
    this.modelName = modelName;
}


public Make getMake(){
    return make;
}


public String getModelName(){
    return modelName;
}


public void setMake(Make make){
    this.make = make;
}


public void setMakeId(Long makeId){
    this.makeId = makeId;
}


public Long getMakeId(){
    return makeId;
}


}
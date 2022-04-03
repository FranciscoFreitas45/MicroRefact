package com.gp.cricket.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
@Entity
@Table(name = "baller_type")
public class BallerType {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "baller_type_id")
 private  Integer ballerTypeId;

@NotBlank
 private  String type;

public BallerType() {
}public BallerType(Integer ballerTypeId, @NotBlank String type) {
    super();
    this.ballerTypeId = ballerTypeId;
    this.type = type;
}
public Integer getBallerTypeId(){
    return ballerTypeId;
}


public void setBallerTypeId(Integer ballerTypeId){
    this.ballerTypeId = ballerTypeId;
}


public String getType(){
    return type;
}


@Override
public String toString(){
    return "BallerType [ballerTypeId=" + ballerTypeId + ", type=" + type + "]";
}


public void setType(String type){
    this.type = type;
}


}
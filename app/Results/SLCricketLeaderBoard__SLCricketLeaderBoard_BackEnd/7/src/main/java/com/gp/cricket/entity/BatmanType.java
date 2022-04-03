package com.gp.cricket.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
@Entity
@Table(name = "batman_type")
public class BatmanType {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "batman_type_id")
 private  Integer batmanTypeId;

@NotBlank
 private  String type;

public BatmanType() {
}public BatmanType(Integer batmanTypeId, @NotBlank String type) {
    super();
    this.batmanTypeId = batmanTypeId;
    this.type = type;
}
public String getType(){
    return type;
}


public Integer getBatmanTypeId(){
    return batmanTypeId;
}


@Override
public String toString(){
    return "BatmanType [batmanTypeId=" + batmanTypeId + ", type=" + type + "]";
}


public void setType(String type){
    this.type = type;
}


public void setBatmanTypeId(Integer batmanTypeId){
    this.batmanTypeId = batmanTypeId;
}


}
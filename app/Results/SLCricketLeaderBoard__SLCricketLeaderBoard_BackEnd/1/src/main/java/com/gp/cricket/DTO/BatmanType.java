package com.gp.cricket.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
public class BatmanType {

 private  Integer batmanTypeId;

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


}
package com.gp.cricket.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
public class BallerType {

 private  Integer ballerTypeId;

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


public String getType(){
    return type;
}


}
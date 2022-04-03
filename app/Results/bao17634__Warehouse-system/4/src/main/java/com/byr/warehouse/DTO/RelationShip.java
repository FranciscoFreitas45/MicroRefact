package com.byr.warehouse.DTO;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
public class RelationShip {

 private  int id;

 private  String supplyCode;

 private  String supplyName;

 private  String demandCode;

 private  String demandName;

 private  String groupMark;

 private  String relationId;


public String getDemandName(){
    return demandName;
}


public String getSupplyCode(){
    return supplyCode;
}


public int getId(){
    return id;
}


public String getSupplyName(){
    return supplyName;
}


public String getDemandCode(){
    return demandCode;
}


public String getGroupMark(){
    return groupMark;
}


public String getRelationId(){
    return relationId;
}


public void setId(int id){
    this.id = id;
}


public void setRelationId(String relationId){
    this.relationId = relationId;
}


public void setDemandCode(String demandCode){
    this.demandCode = demandCode;
}


}
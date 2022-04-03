package com.byr.warehouse.pojo;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class RelationShip {

@Id
// 设置主键并且设置主键为自增
@GeneratedValue(strategy = GenerationType.AUTO)
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


public void setSupplyCode(String supplyCode){
    this.supplyCode = supplyCode;
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


public void setGroupMark(String groupMark){
    this.groupMark = groupMark;
}


public String getRelationId(){
    return relationId;
}


public void setDemandName(String demandName){
    this.demandName = demandName;
}


public void setId(int id){
    this.id = id;
}


@Override
public String toString(){
    return "RelationShip{" + "id=" + id + ", supplyCode='" + supplyCode + '\'' + ", supplyName='" + supplyName + '\'' + ", demandCode='" + demandCode + '\'' + ", demandName='" + demandName + '\'' + ", groupMark='" + groupMark + '\'' + ", relationId='" + relationId + '\'' + '}';
}


public void setRelationId(String relationId){
    this.relationId = relationId;
}


public void setSupplyName(String supplyName){
    this.supplyName = supplyName;
}


public void setDemandCode(String demandCode){
    this.demandCode = demandCode;
}


}
package com.empl.mgr.model;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "te_village")
public class TeVillage {

 private  long serialVersionUID;

 private  long villageId;

 private  String villageName;

 private  String villageCode;

 private  long townshipId;

// Constructors
/**
 * default constructor
 */
public TeVillage() {
}/**
 * full constructor
 */
public TeVillage(long villageId, String villageName, String villageCode, long townshipId) {
    this.villageId = villageId;
    this.villageName = villageName;
    this.villageCode = villageCode;
    this.townshipId = townshipId;
}
@Column(name = "villageName", nullable = false)
public String getVillageName(){
    return this.villageName;
}


public void setVillageName(String villageName){
    this.villageName = villageName;
}


public void setTownshipId(long townshipId){
    this.townshipId = townshipId;
}


public void setVillageCode(String villageCode){
    this.villageCode = villageCode;
}


@Id
@Column(name = "villageId", unique = true, nullable = false)
public long getVillageId(){
    return this.villageId;
}


@Override
public String toString(){
    return "TeVillage [villageId:" + villageId + ", villageName:" + villageName + ", villageCode:" + villageCode + ", townshipId:" + townshipId + "]";
}


@Column(name = "townshipId", nullable = false)
public long getTownshipId(){
    return this.townshipId;
}


public void setVillageId(long villageId){
    this.villageId = villageId;
}


@Column(name = "villageCode", nullable = false, length = 3)
public String getVillageCode(){
    return this.villageCode;
}


}
package com.empl.mgr.model;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "te_township")
public class TeTownship {

 private  long serialVersionUID;

 private  long townshipId;

 private  String townshipName;

 private  String townshipCode;

 private  long countyId;

// Constructors
/**
 * default constructor
 */
public TeTownship() {
}/**
 * full constructor
 */
public TeTownship(long townshipId, String townshipName, String townshipCode, long countyId) {
    this.townshipId = townshipId;
    this.townshipName = townshipName;
    this.townshipCode = townshipCode;
    this.countyId = countyId;
}
public void setTownshipName(String townshipName){
    this.townshipName = townshipName;
}


public void setCountyId(long countyId){
    this.countyId = countyId;
}


public void setTownshipId(long townshipId){
    this.townshipId = townshipId;
}


@Column(name = "countyId", nullable = false)
public long getCountyId(){
    return this.countyId;
}


public void setTownshipCode(String townshipCode){
    this.townshipCode = townshipCode;
}


@Override
public String toString(){
    return "TeTownship [townshipId:" + townshipId + ", townshipName:" + townshipName + ", townshipCode:" + townshipCode + ", countyId:" + countyId + "]";
}


@Id
@Column(name = "townshipId", unique = true, nullable = false)
public long getTownshipId(){
    return this.townshipId;
}


@Column(name = "townshipCode", nullable = false, length = 3)
public String getTownshipCode(){
    return this.townshipCode;
}


@Column(name = "townshipName", nullable = false)
public String getTownshipName(){
    return this.townshipName;
}


}
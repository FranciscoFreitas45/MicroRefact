package com.empl.mgr.model;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "te_county")
public class TeCounty {

 private  long serialVersionUID;

 private  long countyId;

 private  String countyName;

 private  String countyCode;

 private  long cityId;

// Constructors
/**
 * default constructor
 */
public TeCounty() {
}/**
 * full constructor
 */
public TeCounty(long countyId, String countyName, String countyCode, long cityId) {
    this.countyId = countyId;
    this.countyName = countyName;
    this.countyCode = countyCode;
    this.cityId = cityId;
}
public void setCountyId(long countyId){
    this.countyId = countyId;
}


@Column(name = "countyName", nullable = false)
public String getCountyName(){
    return this.countyName;
}


public void setCityId(long cityId){
    this.cityId = cityId;
}


public void setCountyCode(String countyCode){
    this.countyCode = countyCode;
}


@Id
@Column(name = "countyId", unique = true, nullable = false)
public long getCountyId(){
    return this.countyId;
}


public void setCountyName(String countyName){
    this.countyName = countyName;
}


@Column(name = "countyCode", nullable = false, length = 3)
public String getCountyCode(){
    return this.countyCode;
}


@Column(name = "cityId", nullable = false)
public long getCityId(){
    return this.cityId;
}


@Override
public String toString(){
    return "TeCounty [countyId:" + countyId + ", countyName:" + countyName + ", countyCode:" + countyCode + ", cityId:" + cityId + "]";
}


}
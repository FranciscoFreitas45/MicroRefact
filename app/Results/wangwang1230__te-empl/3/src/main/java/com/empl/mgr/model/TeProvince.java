package com.empl.mgr.model;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "te_province")
public class TeProvince {

 private  long serialVersionUID;

 private  long provinceId;

 private  String provinceName;

 private  String provinceCode;

 private  String language;

// Constructors
/**
 * default constructor
 */
public TeProvince() {
}/**
 * minimal constructor
 */
public TeProvince(String provinceName, String provinceCode) {
    this.provinceName = provinceName;
    this.provinceCode = provinceCode;
}/**
 * full constructor
 */
public TeProvince(String provinceName, String provinceCode, String language) {
    this.provinceName = provinceName;
    this.provinceCode = provinceCode;
    this.language = language;
}
@Column(name = "language", length = 12)
public String getLanguage(){
    return this.language;
}


@Column(name = "provinceName", nullable = false)
public String getProvinceName(){
    return this.provinceName;
}


public void setProvinceId(long provinceId){
    this.provinceId = provinceId;
}


public void setProvinceName(String provinceName){
    this.provinceName = provinceName;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "provinceId", unique = true, nullable = false)
public long getProvinceId(){
    return this.provinceId;
}


public void setProvinceCode(String provinceCode){
    this.provinceCode = provinceCode;
}


@Override
public String toString(){
    return "TeProvince [provinceId:" + provinceId + ", provinceName:" + provinceName + ", provinceCode:" + provinceCode + ", language:" + language + "]";
}


@Column(name = "provinceCode", nullable = false, length = 3)
public String getProvinceCode(){
    return this.provinceCode;
}


public void setLanguage(String language){
    this.language = language;
}


}
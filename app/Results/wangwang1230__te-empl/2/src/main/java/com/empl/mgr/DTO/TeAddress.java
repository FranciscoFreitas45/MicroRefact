package com.empl.mgr.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
public class TeAddress {

 private  long serialVersionUID;

 private  long adsId;

 private  Date timestamp;

 private  Integer adsType;

 private  long adsProvince;

 private  long adsCity;

 private  long adsCounty;

 private  long adsTownship;

 private  long adsVillage;

 private  String adsDetailed;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

// Constructors
/**
 * default constructor
 */
public TeAddress() {
}/**
 * full constructor
 */
public TeAddress(Integer adsType, long adsProvince, long adsCity, long adsCounty, long adsTownship, long adsVillage, String adsDetailed) {
    this.adsType = adsType;
    this.adsProvince = adsProvince;
    this.adsCity = adsCity;
    this.adsCounty = adsCounty;
    this.adsTownship = adsTownship;
    this.adsVillage = adsVillage;
    this.adsDetailed = adsDetailed;
}
@Column(name = "adsProvince")
public long getAdsProvince(){
    return this.adsProvince;
}


@Column(name = "adsCounty")
public long getAdsCounty(){
    return this.adsCounty;
}


@Column(name = "adsDetailed", length = 512)
public String getAdsDetailed(){
    return this.adsDetailed;
}


@Column(name = "adsCity")
public long getAdsCity(){
    return this.adsCity;
}


@Column(name = "adsVillage")
public long getAdsVillage(){
    return this.adsVillage;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "adsId", unique = true, nullable = false)
public long getAdsId(){
    return this.adsId;
}


@Version
@Column(name = "timestamp", nullable = false, length = 19)
public Date getTimestamp(){
    return this.timestamp;
}


@Column(name = "adsType")
public Integer getAdsType(){
    return this.adsType;
}


@Column(name = "adsTownship")
public long getAdsTownship(){
    return this.adsTownship;
}


public void setAdsType(Integer adsType){
    this.adsType = adsType;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAdsType"))

.queryParam("adsType",adsType)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAdsProvince(long adsProvince){
    this.adsProvince = adsProvince;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAdsProvince"))

.queryParam("adsProvince",adsProvince)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAdsCity(long adsCity){
    this.adsCity = adsCity;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAdsCity"))

.queryParam("adsCity",adsCity)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAdsCounty(long adsCounty){
    this.adsCounty = adsCounty;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAdsCounty"))

.queryParam("adsCounty",adsCounty)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAdsTownship(long adsTownship){
    this.adsTownship = adsTownship;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAdsTownship"))

.queryParam("adsTownship",adsTownship)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAdsVillage(long adsVillage){
    this.adsVillage = adsVillage;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAdsVillage"))

.queryParam("adsVillage",adsVillage)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAdsDetailed(String adsDetailed){
    this.adsDetailed = adsDetailed;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setAdsDetailed"))

.queryParam("adsDetailed",adsDetailed)
;
restTemplate.put(builder.toUriString(),null);
}


}
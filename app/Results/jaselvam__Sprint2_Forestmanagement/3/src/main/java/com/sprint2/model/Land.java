package com.sprint2.model;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
// defining class name as Table name
@Table(name = "Forest_Land")
public class Land {

@Id
@GeneratedValue
 private  Integer landId;

 private  String surveyNumber;

 private  String landOwner;

 private  String landArea;

public Land() {
}public Land(Integer landId, String surveyNumber, String landOwner, String landArea) {
    super();
    this.landId = landId;
    this.surveyNumber = surveyNumber;
    this.landOwner = landOwner;
    this.landArea = landArea;
}public Land(String surveyNumber, String landOwner, String landArea) {
    super();
    this.surveyNumber = surveyNumber;
    this.landOwner = landOwner;
    this.landArea = landArea;
}
public Integer getLandId(){
    return landId;
}


public void setSurveyNumber(String surveyNumber){
    this.surveyNumber = surveyNumber;
}


public String getSurveyNumber(){
    return surveyNumber;
}


public String getLandOwner(){
    return landOwner;
}


public void setLandOwner(String landOwner){
    this.landOwner = landOwner;
}


public String getLandArea(){
    return landArea;
}


public void setLandArea(String landArea){
    this.landArea = landArea;
}


public void setLandId(Integer landId){
    this.landId = landId;
}


@Override
public String toString(){
    return "Land [landId=" + landId + ", surveyNumber=" + surveyNumber + ", landOwner=" + landOwner + ", landArea=" + landArea + "]";
}


}
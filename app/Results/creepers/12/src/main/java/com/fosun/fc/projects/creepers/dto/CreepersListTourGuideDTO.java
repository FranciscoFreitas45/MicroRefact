package com.fosun.fc.projects.creepers.dto;
 public class CreepersListTourGuideDTO extends CreepersBaseDTO{

 private  long serialVersionUID;

 private  String guideNo;

 private  String cardNo;

 private  String certNo;


public String getCertNo(){
    return certNo;
}


public String getCardNo(){
    return cardNo;
}


public void setGuideNo(String guideNo){
    this.guideNo = guideNo;
}


public void setCertNo(String certNo){
    this.certNo = certNo;
}


public String getGuideNo(){
    return guideNo;
}


public void setCardNo(String cardNo){
    this.cardNo = cardNo;
}


}
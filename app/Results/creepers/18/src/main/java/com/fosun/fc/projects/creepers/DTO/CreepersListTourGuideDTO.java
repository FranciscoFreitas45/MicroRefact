package com.fosun.fc.projects.creepers.DTO;
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


public String getGuideNo(){
    return guideNo;
}


}
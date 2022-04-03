package com.fosun.fc.projects.creepers.DTO;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
public class TCreepersListTourGuide implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String cardNo;

 private  String certNo;

 private  String guideNo;

public TCreepersListTourGuide() {
}
public String getCertNo(){
    return this.certNo;
}


public String getCardNo(){
    return this.cardNo;
}


public void setCertNo(String certNo){
    this.certNo = certNo;
}


public long getId(){
    return this.id;
}


public String getGuideNo(){
    return this.guideNo;
}


}
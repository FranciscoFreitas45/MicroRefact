package com.fosun.fc.projects.creepers.entity;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "T_CREEPERS_LIST_TOUR_GUIDE")
@NamedQuery(name = "TCreepersListTourGuide.findAll", query = "SELECT t FROM TCreepersListTourGuide t")
public class TCreepersListTourGuide implements Serializable{

 private  long serialVersionUID;

@Id
@SequenceGenerator(name = "T_CREEPERS_LIST_TOUR_GUIDE_ID_GENERATOR", sequenceName = "SEQ_CREEPERS_LIST_TOUR_GUIDE")
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_CREEPERS_LIST_TOUR_GUIDE_ID_GENERATOR")
 private  long id;

@Column(name = "CARD_NO")
 private  String cardNo;

@Column(name = "CERT_NO")
 private  String certNo;

@Column(name = "GUIDE_NO")
 private  String guideNo;

public TCreepersListTourGuide() {
}
public String getCertNo(){
    return this.certNo;
}


public String getCardNo(){
    return this.cardNo;
}


public void setGuideNo(String guideNo){
    this.guideNo = guideNo;
}


public void setCertNo(String certNo){
    this.certNo = certNo;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return this.id;
}


public void setCardNo(String cardNo){
    this.cardNo = cardNo;
}


public String getGuideNo(){
    return this.guideNo;
}


}
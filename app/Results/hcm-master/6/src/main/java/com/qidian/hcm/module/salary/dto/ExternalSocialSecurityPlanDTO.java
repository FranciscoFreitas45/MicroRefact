package com.qidian.hcm.module.salary.dto;
 import com.qidian.hcm.module.salary.enums.PointRule;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class ExternalSocialSecurityPlanDTO implements Serializable{

 private  long serialVersionUID;

 public  Integer limitPoint;

 public  Date effectDate;

 public  Integer pointScale;

 public  String pointRule;

 public  Double pensionPersonalRatio;

 public  Double pensionEmployerRatio;

 public  Double historyPersonalRatio;

 public  Double historyEmployerRatio;

 public  Double outworkPersonalRatio;

 public  Double outworkEmployerRatio;

 public  Double injuryPersonalRatio;

 public  Double injuryEmployerRatio;

 public  Double birthPersonalRatio;

 public  Double birthEmployerRatio;


}
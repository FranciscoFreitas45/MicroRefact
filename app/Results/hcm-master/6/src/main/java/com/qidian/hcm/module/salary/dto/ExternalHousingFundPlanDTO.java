package com.qidian.hcm.module.salary.dto;
 import com.qidian.hcm.module.salary.enums.PointRule;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
public class ExternalHousingFundPlanDTO implements Serializable{

 private  long serialVersionUID;

 public  Integer limitPoint;

 public  Date effectDate;

 public  Integer pointScale;

 public  String pointRule;

 public  Double fundEmployerRatio;

 public  Double fundPersonalRatio;

 public  Double fundAddingEmployerRatio;

 public  Double fundAddingPersonalRatio;


}
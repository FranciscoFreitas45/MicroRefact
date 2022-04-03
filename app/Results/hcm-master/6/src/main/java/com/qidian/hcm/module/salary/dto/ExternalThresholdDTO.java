package com.qidian.hcm.module.salary.dto;
 import com.qidian.hcm.module.salary.enums.ThresholdType;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
@Getter
@Setter
public class ExternalThresholdDTO implements Serializable{

 private  long serialVersionUID;

 public  Integer point;

 public  String type;


}
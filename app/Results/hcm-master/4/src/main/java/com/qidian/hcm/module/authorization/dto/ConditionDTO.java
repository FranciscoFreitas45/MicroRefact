package com.qidian.hcm.module.authorization.dto;
 import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
@Getter
@Setter
public class ConditionDTO implements Serializable{

 private  String field;

 private  String op;

 private  String value;

 private  String extraValue;


}
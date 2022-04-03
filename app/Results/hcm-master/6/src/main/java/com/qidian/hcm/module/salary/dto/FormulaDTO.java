package com.qidian.hcm.module.salary.dto;
 import com.qidian.hcm.module.salary.enums.PointRule;
import com.qidian.hcm.module.salary.enums.SalaryType;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;
@Getter
@Setter
public class FormulaDTO implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  String code;

 private  SalaryType type;

 private  Integer pointScale;

 private  PointRule pointRule;

 private  String formula;

 private  List<String> children;

 private  List<String> parents;


}
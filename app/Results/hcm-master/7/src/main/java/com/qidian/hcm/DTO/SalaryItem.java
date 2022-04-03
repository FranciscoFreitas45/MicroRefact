package com.qidian.hcm.DTO;
 import com.qidian.hcm.module.salary.enums.PointRule;
import com.qidian.hcm.module.salary.enums.SalaryType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
public class SalaryItem implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  String name;

 private  String code;

 private  SalaryType type;

 private  Integer pointScale;

 private  PointRule pointRule;

 private  String formula;

 private  Boolean display;

 private  Boolean deletable;

 private  Boolean editable;

 private  Boolean inList;

 private  Boolean inOption;

 private  Boolean inflow;


}
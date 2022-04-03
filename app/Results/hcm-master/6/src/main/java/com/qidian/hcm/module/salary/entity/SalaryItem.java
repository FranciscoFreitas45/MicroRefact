package com.qidian.hcm.module.salary.entity;
 import com.qidian.hcm.module.salary.enums.PointRule;
import com.qidian.hcm.module.salary.enums.SalaryType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
@Entity
@Table(name = "salary_item")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class SalaryItem implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "name")
 private  String name;

@Column(name = "code")
 private  String code;

@Column(name = "type")
@Enumerated(EnumType.STRING)
 private  SalaryType type;

@Range(min = 0, max = 4)
@Column(name = "point_scale")
 private  Integer pointScale;

@Column(name = "point_rule")
@Enumerated(EnumType.STRING)
 private  PointRule pointRule;

@Column(name = "formula")
 private  String formula;

@Column(name = "display")
 private  Boolean display;

@Column(name = "deletable")
 private  Boolean deletable;

@Column(name = "editable")
 private  Boolean editable;

@Column(name = "in_list")
 private  Boolean inList;

@Column(name = "in_option")
 private  Boolean inOption;

@Column(name = "inflow")
 private  Boolean inflow;


}
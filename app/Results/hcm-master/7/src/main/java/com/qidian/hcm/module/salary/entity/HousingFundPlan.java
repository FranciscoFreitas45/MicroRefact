package com.qidian.hcm.module.salary.entity;
 import com.qidian.hcm.module.salary.enums.PointRule;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "housing_fund_plan")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class HousingFundPlan implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "name")
 private  String name;

@Column(name = "limit_point")
 private  Integer limitPoint;

@Column(name = "effect_date")
 private  Date effectDate;

@Column(name = "point_scale")
 private  Integer pointScale;

@Column(name = "point_rule")
@Enumerated(EnumType.STRING)
 private  PointRule pointRule;

@Column(name = "limit_up")
 private  Long limitUp;

@Column(name = "limit_down")
 private  Long limitDown;

@Column(name = "fund_employer_ratio")
 private  Double fundEmployerRatio;

@Column(name = "fund_personal_ratio")
 private  Double fundPersonalRatio;

@Column(name = "fund_adding_employer_ratio")
 private  Double fundAddingEmployerRatio;

@Column(name = "fund_adding_personal_ratio")
 private  Double fundAddingPersonalRatio;


}
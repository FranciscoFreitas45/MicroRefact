package com.qidian.hcm.module.salary.entity;
 import com.qidian.hcm.module.salary.enums.PointRule;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "social_security_plan")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class SocialSecurityPlan implements Serializable{

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

@Column(name = "pension_personal_ratio")
 private  Double pensionPersonalRatio;

@Column(name = "pension_employer_ratio")
 private  Double pensionEmployerRatio;

@Column(name = "history_personal_ratio")
 private  Double historyPersonalRatio;

@Column(name = "history_employer_ratio")
 private  Double historyEmployerRatio;

@Column(name = "outwork_personal_ratio")
 private  Double outworkPersonalRatio;

@Column(name = "outwork_employer_ratio")
 private  Double outworkEmployerRatio;

@Column(name = "injury_personal_ratio")
 private  Double injuryPersonalRatio;

@Column(name = "injury_employer_ratio")
 private  Double injuryEmployerRatio;

@Column(name = "birth_personal_ratio")
 private  Double birthPersonalRatio;

@Column(name = "birth_employer_ratio")
 private  Double birthEmployerRatio;


}
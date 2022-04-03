package com.qidian.hcm.module.employee.entity;
 import com.alibaba.fastjson.JSONObject;
import com.qidian.hcm.module.employee.enums.Degree;
import com.qidian.hcm.module.employee.enums.Education;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "employee_education")
@Getter
@Setter
@ApiModel(value = "employee_education")
@EntityListeners(AuditingEntityListener.class)
public class EmployeeEducation implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "employee_id")
 private  Long employeeId;

@Column(name = "start_time")
 private  Date startTime;

@Column(name = "end_time")
 private  Date endTime;

@Column(name = "school")
 private  String school;

@Column(name = "education")
@Enumerated(value = EnumType.STRING)
 private  Education education;

@Column(name = "fulltime")
 private  Boolean fullTime;

@Column(name = "highest")
 private  Boolean highest;

@Column(name = "degree")
@Enumerated(value = EnumType.STRING)
 private  Degree degree;

@Column(name = "customized_field")
 private  String customizedField;

@Transient
 private  JSONObject customizedFieldJson;


}
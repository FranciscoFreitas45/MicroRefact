package com.qidian.hcm.module.employee.entity;
 import com.alibaba.fastjson.JSONObject;
import com.qidian.hcm.module.employee.enums.ContractPeriod;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "employee_contract")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class EmployeeContract implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "employee_id")
 private  Long employeeId;

@Column(name = "sign_unit")
@ApiModelProperty(value = "签约单位", name = "sign_unit", required = true)
 private  String signUnit;

@Column(name = "start_date")
 private  Date startDate;

@Column(name = "end_date")
 private  Date endDate;

@Column(name = "period")
@Enumerated(EnumType.STRING)
 private  ContractPeriod period;

@Column(name = "probation_end_date")
 private  Date probationEndDate;

@Column(name = "customized_field")
 private  String customizedField;

@Transient
 private  JSONObject customizedFieldJson;


}
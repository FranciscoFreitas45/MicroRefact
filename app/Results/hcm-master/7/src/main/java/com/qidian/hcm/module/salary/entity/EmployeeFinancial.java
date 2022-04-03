package com.qidian.hcm.module.salary.entity;
 import com.qidian.hcm.module.employee.entity.Employee;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import java.io.Serializable;
import java.util.Date;
import com.qidian.hcm.Request.EmployeeRequest;
import com.qidian.hcm.Request.Impl.EmployeeRequestImpl;
import com.qidian.hcm.DTO.Employee;
@Entity
@Table(name = "employee_financial")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class EmployeeFinancial implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "employee_id")
 private  Long employeeId;

@Column(name = "salary")
 private  Double salary;

@Column(name = "salary_adjust_date")
 private  Date salaryAdjustDate;

@Column(name = "threshold_id")
 private  Long thresholdId;

@Column(name = "social_security_plan_id")
 private  Long socialSecurityPlanId;

@Column(name = "social_security_base")
 private  Double socialSecurityBase;

@Column(name = "housing_fund_plan_id")
 private  Long housingFundPlanId;

@Column(name = "housing_fund_base")
 private  Double housingFundBase;

@Column(name = "housing_fund_account")
 private  String housingFundAccount;

@Temporal(TemporalType.TIMESTAMP)
@CreationTimestamp
@Column(name = "create_time", updatable = false)
 private  Date createTime;

@Temporal(TemporalType.TIMESTAMP)
@UpdateTimestamp
@Column(name = "update_time", insertable = false)
 private  Date updateTime;

@Transient
 private  Employee employee;

@Column(name = "id")
 private Long id;

@Transient
 private EmployeeRequest employeerequest = new EmployeeRequestImpl();;


}
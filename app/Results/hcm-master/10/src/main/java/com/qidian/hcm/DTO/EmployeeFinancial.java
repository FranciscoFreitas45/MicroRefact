package com.qidian.hcm.DTO;
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
public class EmployeeFinancial implements Serializable{

 private  long serialVersionUID;

 private  Long id;

 private  Long employeeId;

 private  Double salary;

 private  Date salaryAdjustDate;

 private  Long thresholdId;

 private  Long socialSecurityPlanId;

 private  Double socialSecurityBase;

 private  Long housingFundPlanId;

 private  Double housingFundBase;

 private  String housingFundAccount;

 private  Date createTime;

 private  Date updateTime;

 private  Employee employee;


}
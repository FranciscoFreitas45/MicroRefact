package com.qidian.hcm.module.salary.entity;
 import com.qidian.hcm.module.employee.entity.Employee;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import com.qidian.hcm.Request.EmployeeRequest;
import com.qidian.hcm.Request.Impl.EmployeeRequestImpl;
import com.qidian.hcm.DTO.Employee;
@Entity
@Table(name = "employee_salary_monthly")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class EmployeeSalaryMonthly implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private  Long id;

@Column(name = "employee_id")
@NotNull
 private  Long employeeId;

@Column(name = "include")
 private  Boolean include;

@Column(name = "date")
@NotNull(message = "统计月份yyyy-MM不能为空！")
 private  String date;

@Column(name = "items_result")
 private  String itemsResult;

@Transient
 private  Employee employee;

@Column(name = "id")
 private Long id;

@Transient
 private EmployeeRequest employeerequest = new EmployeeRequestImpl();;


}
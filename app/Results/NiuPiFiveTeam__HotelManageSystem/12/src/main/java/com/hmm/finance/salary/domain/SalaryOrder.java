package com.hmm.finance.salary.domain;
 import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hmm.department.entity.Department;
import com.hmm.employee.entity.Employee;
import com.hmm.Request.EmployeeRequest;
import com.hmm.Request.Impl.EmployeeRequestImpl;
import com.hmm.DTO.Employee;
@Entity
@Table(name = "t_salary_order")
public class SalaryOrder {

 private  Long salaryOrderId;

 private  float basicwage;

 private  float overtimefee;

 private  float allowance;

 private  float reducemoney;

 private  Date date;

 private  Employee employee;

@Column(name = "emp_idQ9YD")
 private Integer emp_idQ9YD;

@Transient
 private EmployeeRequest employeerequest = new EmployeeRequestImpl();;


public float getOvertimefee(){
    return overtimefee;
}


public float getAllowance(){
    return allowance;
}


public void setSalaryOrderId(Long salaryOrderId){
    this.salaryOrderId = salaryOrderId;
}


public void setAllowance(float allowance){
    this.allowance = allowance;
}


public void setEmployee(Employee employee){
this.emp_idQ9YD = employee.getEmployee() ;
employeerequest.setEmployee(employee,this.emp_idQ9YD);
 this.employee = employee;
}



public float getReducemoney(){
    return reducemoney;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long getSalaryOrderId(){
    return salaryOrderId;
}


public void setReducemoney(float reducemoney){
    this.reducemoney = reducemoney;
}


public float getBasicwage(){
    return basicwage;
}


public void setOvertimefee(float overtimefee){
    this.overtimefee = overtimefee;
}


@Transient
public Employee getEmployee(){
  this.employee = employeerequest.getEmployee(this.emp_idQ9YD);
return this.employee;
}}



public void setDate(Date date){
    this.date = date;
}


@JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
public Date getDate(){
    return date;
}


public void setBasicwage(float basicwage){
    this.basicwage = basicwage;
}


}
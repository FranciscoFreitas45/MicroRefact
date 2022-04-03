package com.hmm.finance.logisticst.domain;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hmm.activiti.domain.ProcessStatus;
import com.hmm.employee.entity.Employee;
import com.hmm.logistics.stock.entity.InDetailed;
import com.hmm.Request.InDetailedRequest;
import com.hmm.Request.Impl.InDetailedRequestImpl;
import com.hmm.DTO.InDetailed;
import com.hmm.Request.EmployeeRequest;
import com.hmm.Request.Impl.EmployeeRequestImpl;
import com.hmm.DTO.Employee;
@Entity
@Table(name = "t_inStorage")
public class InStorage {

 private  String inStorageId;

 private  Date inStorageDate;

 private  String vender;

 private  Float amount;

 private  Date applyTime;

 private  List<InDetailed> inDetaileds;

 private  Employee employee;

 private  ProcessStatus processStatus;

 private  String processInstanceId;

@Transient
 private InDetailedRequest indetailedrequest = new InDetailedRequestImpl();;

@Column(name = "emp_idIGL6")
 private Integer emp_idIGL6;

@Transient
 private EmployeeRequest employeerequest = new EmployeeRequestImpl();;


@Transient
public List<InDetailed> getInDetaileds(){
  this.inDetaileds = indetailedrequest.getInDetaileds(this.inStorageId);
return this.inDetaileds;
}}



public void setProcessStatus(ProcessStatus processStatus){
    this.processStatus = processStatus;
}


public void setEmployee(Employee employee){
this.emp_idIGL6 = employee.getEmployee() ;
employeerequest.setEmployee(employee,this.emp_idIGL6);
 this.employee = employee;
}



@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
@Column(nullable = true)
public Date getInStorageDate(){
    return inStorageDate;
}


@Enumerated(EnumType.STRING)
public ProcessStatus getProcessStatus(){
    return processStatus;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
@Column(nullable = true)
public Date getApplyTime(){
    return applyTime;
}


public void setApplyTime(Date applyTime){
    this.applyTime = applyTime;
}


public void setInDetaileds(List<InDetailed> inDetaileds){
indetailedrequest.setInDetaileds(inDetaileds,this.inStorageId);
 this.inDetaileds = inDetaileds;
}



public void setProcessInstanceId(String processInstanceId){
    this.processInstanceId = processInstanceId;
}


public void setInStorageDate(Date inStorageDate){
    this.inStorageDate = inStorageDate;
}


@Id
public String getInStorageId(){
    return inStorageId;
}


@Transient
public Employee getEmployee(){
  this.employee = employeerequest.getEmployee(this.emp_idIGL6);
return this.employee;
}}



public String getProcessInstanceId(){
    return processInstanceId;
}


public void setInStorageId(String inStorageId){
    this.inStorageId = inStorageId;
}


public void setAmount(Float amount){
    this.amount = amount;
}


public String getVender(){
    return vender;
}


public void setVender(String vender){
    this.vender = vender;
}


@Column(nullable = true)
public Float getAmount(){
    return amount;
}


}
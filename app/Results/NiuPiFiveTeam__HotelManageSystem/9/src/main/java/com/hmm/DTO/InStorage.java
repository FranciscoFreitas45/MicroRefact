package com.hmm.DTO;
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

 private Integer emp_idIGL6;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://5";


@Transient
public List<InDetailed> getInDetaileds(){
  this.inDetaileds = indetailedrequest.getInDetaileds(this.inStorageId);
return this.inDetaileds;
}}



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


public String getVender(){
    return vender;
}


@Column(nullable = true)
public Float getAmount(){
    return amount;
}


public void setInStorageId(String inStorageId){
    this.inStorageId = inStorageId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ inStorageId).concat("/setInStorageId"))

.queryParam("inStorageId",inStorageId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setProcessStatus(ProcessStatus processStatus){
    this.processStatus = processStatus;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ inStorageId).concat("/setProcessStatus"))

.queryParam("processStatus",processStatus)
;
restTemplate.put(builder.toUriString(),null);
}


public void setEmployee(Employee employee){
this.emp_idIGL6 = employee.getEmployee() ;
employeerequest.setEmployee(employee,this.emp_idIGL6);
 this.employee = employee;
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ inStorageId).concat("/setEmployee"))

.queryParam("employee",employee)
;
restTemplate.put(builder.toUriString(),null);
}


}
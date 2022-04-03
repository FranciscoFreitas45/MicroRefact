package com.hmm.DTO;
 import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hmm.activiti.domain.ProcessStatus;
import com.hmm.employee.entity.Employee;
import com.hmm.Request.EmployeeRequest;
import com.hmm.Request.Impl.EmployeeRequestImpl;
import com.hmm.DTO.Employee;
public class Travel {

 private  Long travelId;

 private  Date traStartTime;

 private  Date traEndTime;

 private  String empNo;

 private  Date realityStartTime;

 private  Date realityEndTime;

 private  ProcessStatus processStatus;

 private  String approval;

 private  String process;

 private  Float allowance;

 private  Date applyTime;

 private  String processInstanceId;

 private  Employee employ;

 private Integer emp_idPBMQ;


public Float getAllowance(){
    return allowance;
}


public String getApproval(){
    return approval;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
public Date getTraStartTime(){
    return traStartTime;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getApplyTime(){
    return applyTime;
}


public String getEmpNo(){
    return empNo;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long getTravelId(){
    return travelId;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getRealityEndTime(){
    return realityEndTime;
}


public String getProcessInstanceId(){
    return processInstanceId;
}


@Transient
public Employee getEmploy(){
  this.employ = employeerequest.getEmploy(this.emp_idPBMQ);
return this.employ;
}}



public String getProcess(){
    return process;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
public Date getTraEndTime(){
    return traEndTime;
}


public ProcessStatus getProcessStatus(){
    return processStatus;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getRealityStartTime(){
    return realityStartTime;
}


}
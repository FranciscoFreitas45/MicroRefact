package com.hmm.DTO;
 import java.io.Serializable;
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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hmm.activiti.domain.ProcessStatus;
import com.hmm.employee.entity.Employee;
import com.hmm.Request.EmployeeRequest;
import com.hmm.Request.Impl.EmployeeRequestImpl;
import com.hmm.DTO.Employee;
public class Leave {

 private  Long id;

 private  Date startTime;

 private  Date endTime;

 private  Date realityStartTime;

 private  Date realityEndTime;

 private  Date applyTime;

 private  String leaveType;

 private  String reason;

 private  ProcessStatus processStatus;

 private  String approval;

 private  String userId;

 private  String processInstanceId;

 private  Employee employ;

 private Integer emp_idB9PD;


public String getApproval(){
    return approval;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long getId(){
    return id;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getApplyTime(){
    return applyTime;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getEndTime(){
    return endTime;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getRealityEndTime(){
    return realityEndTime;
}


public String getProcessInstanceId(){
    return processInstanceId;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getStartTime(){
    return startTime;
}


@Transient
public Employee getEmploy(){
  this.employ = employeerequest.getEmploy(this.emp_idB9PD);
return this.employ;
}}



public String getReason(){
    return reason;
}


public String getLeaveType(){
    return leaveType;
}


public ProcessStatus getProcessStatus(){
    return processStatus;
}


public String getUserId(){
    return userId;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getRealityStartTime(){
    return realityStartTime;
}


}
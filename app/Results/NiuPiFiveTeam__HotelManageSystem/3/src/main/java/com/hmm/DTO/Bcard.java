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
public class Bcard {

 private  Long bCardid;

 private  Float worktime;

 private  Date ontudytime;

 private  Date offdutytime;

 private  Float overtime;

 private  String calendar;

 private  String bcardType;

 private  Date workDate;

 private  Employee employ;

 private  Date realityStartTime;

 private  Date realityEndTime;

 private  Date applyTime;

 private  String reason;

 private  ProcessStatus processStatus;

 private  String approval;

 private  String userId;

 private  String processInstanceId;

 private Integer emp_idR9B4;


public String getApproval(){
    return approval;
}


public String getCalendar(){
    return calendar;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long getbCardid(){
    return bCardid;
}


public Date getApplyTime(){
    return applyTime;
}


public Date getRealityEndTime(){
    return realityEndTime;
}


public String getProcessInstanceId(){
    return processInstanceId;
}


public Date getOffdutytime(){
    return offdutytime;
}


@Transient
public Employee getEmploy(){
  this.employ = employeerequest.getEmploy(this.emp_idR9B4);
return this.employ;
}}



public Float getOvertime(){
    return overtime;
}


public String getReason(){
    return reason;
}


public Float getWorktime(){
    return worktime;
}


public ProcessStatus getProcessStatus(){
    return processStatus;
}


public Date getWorkDate(){
    return workDate;
}


public String getBcardType(){
    return bcardType;
}


public Date getOntudytime(){
    return ontudytime;
}


public String getUserId(){
    return userId;
}


public Date getRealityStartTime(){
    return realityStartTime;
}


}
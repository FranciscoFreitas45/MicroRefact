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
@Entity
@Table(name = "t_travel")
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


public Float getAllowance(){
    return allowance;
}


public void setTravelId(Long travelId){
    this.travelId = travelId;
}


public void setTraStartTime(Date traStartTime){
    this.traStartTime = traStartTime;
}


public void setProcessStatus(ProcessStatus processStatus){
    this.processStatus = processStatus;
}


public void setTraEndTime(Date traEndTime){
    this.traEndTime = traEndTime;
}


public String getApproval(){
    return approval;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
public Date getTraStartTime(){
    return traStartTime;
}


public void setRealityStartTime(Date realityStartTime){
    this.realityStartTime = realityStartTime;
}


public void setEmpNo(String empNo){
    this.empNo = empNo;
}


public void setEmploy(Employee employ){
    this.employ = employ;
}


public void setRealityEndTime(Date realityEndTime){
    this.realityEndTime = realityEndTime;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getApplyTime(){
    return applyTime;
}


public void setApplyTime(Date applyTime){
    this.applyTime = applyTime;
}


public String getEmpNo(){
    return empNo;
}


public void setProcessInstanceId(String processInstanceId){
    this.processInstanceId = processInstanceId;
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


@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
@JoinColumn(name = "emp_id")
public Employee getEmploy(){
    return employ;
}


public String getProcess(){
    return process;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
public Date getTraEndTime(){
    return traEndTime;
}


public void setAllowance(Float allowance){
    this.allowance = allowance;
}


public void setProcess(String process){
    this.process = process;
}


public ProcessStatus getProcessStatus(){
    return processStatus;
}


public void setApproval(String approval){
    this.approval = approval;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getRealityStartTime(){
    return realityStartTime;
}


}
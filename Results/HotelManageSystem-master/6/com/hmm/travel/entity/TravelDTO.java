import java.util.Date;
import com.hmm.activiti.domain.ProcessStatus;
public class TravelDTO {

 private  Long travelId;

 private  Date traStartTime;

 private  Date traEndTime;

 private  Date realityStartTime;

 private  Date realityEndTime;

 private  ProcessStatus processStatus;

 private  String approval;

 private  String process;

 private  Float allowance;

 private  String empName;

 private  String empNo;

 private  String deptName;

 private  String taskId;

 private  String taskName;

 private  Date taskCreateTime;

 private  String assignee;

 private  String taskDefinitionKey;

 private  String processInstanceId;

 private  String processDefinitionId;

 private  boolean suspended;

 private  int version;


public void setTravelId(Long travelId){
    this.travelId = travelId;
}


public Float getAllowance(){
    return allowance;
}


public void setTraStartTime(Date traStartTime){
    this.traStartTime = traStartTime;
}


public String getTaskName(){
    return taskName;
}


public void setTraEndTime(Date traEndTime){
    this.traEndTime = traEndTime;
}


public void setProcessStatus(ProcessStatus processStatus){
    this.processStatus = processStatus;
}


public void setTaskDefinitionKey(String taskDefinitionKey){
    this.taskDefinitionKey = taskDefinitionKey;
}


public Date getTraStartTime(){
    return traStartTime;
}


public String getApproval(){
    return approval;
}


public void setRealityStartTime(Date realityStartTime){
    this.realityStartTime = realityStartTime;
}


public void setEmpNo(String empNo){
    this.empNo = empNo;
}


public void setTaskId(String taskId){
    this.taskId = taskId;
}


public Date getTaskCreateTime(){
    return taskCreateTime;
}


public String getAssignee(){
    return assignee;
}


public String getProcessDefinitionId(){
    return processDefinitionId;
}


public String getEmpName(){
    return empName;
}


public void setRealityEndTime(Date realityEndTime){
    this.realityEndTime = realityEndTime;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public String getEmpNo(){
    return empNo;
}


public void setProcessInstanceId(String processInstanceId){
    this.processInstanceId = processInstanceId;
}


public void setTaskCreateTime(Date taskCreateTime){
    this.taskCreateTime = taskCreateTime;
}


public Long getTravelId(){
    return travelId;
}


public void setAssignee(String assignee){
    this.assignee = assignee;
}


public Date getRealityEndTime(){
    return realityEndTime;
}


public String getProcessInstanceId(){
    return processInstanceId;
}


public String getTaskId(){
    return taskId;
}


public int getVersion(){
    return version;
}


public String getProcess(){
    return process;
}


public boolean isSuspended(){
    return suspended;
}


public Date getTraEndTime(){
    return traEndTime;
}


public void setAllowance(Float allowance){
    this.allowance = allowance;
}


public void setVersion(int version){
    this.version = version;
}


public void setProcess(String process){
    this.process = process;
}


public void setProcessDefinitionId(String processDefinitionId){
    this.processDefinitionId = processDefinitionId;
}


public String getDeptName(){
    return deptName;
}


public ProcessStatus getProcessStatus(){
    return processStatus;
}


public void setTaskName(String taskName){
    this.taskName = taskName;
}


public void setSuspended(boolean suspended){
    this.suspended = suspended;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public String getTaskDefinitionKey(){
    return taskDefinitionKey;
}


public void setApproval(String approval){
    this.approval = approval;
}


public Date getRealityStartTime(){
    return realityStartTime;
}


}
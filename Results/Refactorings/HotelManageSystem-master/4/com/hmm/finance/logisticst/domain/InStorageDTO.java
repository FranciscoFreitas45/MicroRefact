import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hmm.activiti.domain.ProcessStatus;
import com.hmm.employee.entity.Employee;
public class InStorageDTO {

 private  String inStorageId;

 private  Date inStorageDate;

 private  String vender;

 private  Float amount;

 private  Date applyTime;

 private  String employeeId;

 private  ProcessStatus processStatus;

 private  String processInstanceId;

 private  String taskId;

 private  String taskName;

 private  Date taskCreateTime;

 private  String assignee;

 private  String taskDefinitionKey;

 private  String processDefinitionId;

 private  boolean suspended;

 private  int version;


public void setEmployeeId(String employeeId){
    this.employeeId = employeeId;
}


public String getTaskName(){
    return taskName;
}


public void setProcessStatus(ProcessStatus processStatus){
    this.processStatus = processStatus;
}


public void setTaskDefinitionKey(String taskDefinitionKey){
    this.taskDefinitionKey = taskDefinitionKey;
}


public void setTaskId(String taskId){
    this.taskId = taskId;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getTaskCreateTime(){
    return taskCreateTime;
}


public String getAssignee(){
    return assignee;
}


public String getProcessDefinitionId(){
    return processDefinitionId;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getApplyTime(){
    return applyTime;
}


public void setApplyTime(Date applyTime){
    this.applyTime = applyTime;
}


public void setProcessInstanceId(String processInstanceId){
    this.processInstanceId = processInstanceId;
}


public void setTaskCreateTime(Date taskCreateTime){
    this.taskCreateTime = taskCreateTime;
}


public String getInStorageId(){
    return inStorageId;
}


@Override
public int hashCode(){
    return inStorageId.hashCode();
}


public void setAssignee(String assignee){
    this.assignee = assignee;
}


public String getProcessInstanceId(){
    return processInstanceId;
}


public String getEmployeeId(){
    return employeeId;
}


public void setAmount(Float amount){
    this.amount = amount;
}


public Float getAmount(){
    return amount;
}


public String getTaskId(){
    return taskId;
}


public int getVersion(){
    return version;
}


public boolean isSuspended(){
    return suspended;
}


public void setVersion(int version){
    this.version = version;
}


public void setProcessDefinitionId(String processDefinitionId){
    this.processDefinitionId = processDefinitionId;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getInStorageDate(){
    return inStorageDate;
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


public void setInStorageDate(Date inStorageDate){
    this.inStorageDate = inStorageDate;
}


@Override
public boolean equals(Object obj){
    if (obj == null) {
        return false;
    }
    if (this == obj) {
        return true;
    }
    if (obj instanceof InStorageDTO) {
        InStorageDTO inStorage = (InStorageDTO) obj;
        if (inStorage.inStorageId.equals(this.inStorageId))
            return true;
    }
    return false;
}


public String getTaskDefinitionKey(){
    return taskDefinitionKey;
}


public void setInStorageId(String inStorageId){
    this.inStorageId = inStorageId;
}


public String getVender(){
    return vender;
}


public void setVender(String vender){
    this.vender = vender;
}


}
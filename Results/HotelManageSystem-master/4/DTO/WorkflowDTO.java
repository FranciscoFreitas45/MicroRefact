import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
public class WorkflowDTO {

 private  String taskId;

 private  String taskName;

 private  Date taskCreateTime;

 private  String assignee;

 private  String taskDefinitionKey;

 private  String processInstanceId;

 private  String processDefinitionId;

 private  boolean suspended;

 private  int version;

 private  String businessKey;


public String getTaskId(){
    return taskId;
}


public int getVersion(){
    return version;
}


public String getTaskName(){
    return taskName;
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


public String getBusinessKey(){
    return businessKey;
}


public String getProcessInstanceId(){
    return processInstanceId;
}


public String getTaskDefinitionKey(){
    return taskDefinitionKey;
}


}
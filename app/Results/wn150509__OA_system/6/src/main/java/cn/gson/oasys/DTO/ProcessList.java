package cn.gson.oasys.DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import cn.gson.oasys.model.entity.note.Attachment;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.Request.UserRequest;
import cn.gson.oasys.Request.Impl.UserRequestImpl;
import cn.gson.oasys.DTO.User;
import cn.gson.oasys.Request.AttachmentRequest;
import cn.gson.oasys.Request.Impl.AttachmentRequestImpl;
import cn.gson.oasys.DTO.Attachment;
public class ProcessList {

 private  Long processId;

 private  String typeNmae;

 private  Long statusId;

 private  Long deeply;

 private  String processName;

 private  String processDescribe;

 private  User userId;

 private  Date applyTime;

 private  Boolean rejected;

 private  Date startTime;

 private  Date endTime;

 private  Double procseeDays;

 private  Attachment proFileid;

 private  String shenuser;


public String getProcessName(){
    return processName;
}


public void setProcseeDays(Double procseeDays){
    this.procseeDays = procseeDays;
}


public Attachment getProFileid(){
    return proFileid;
}


public String getProcessDescribe(){
    return processDescribe;
}


public Long getStatusId(){
    return statusId;
}


public Date getApplyTime(){
    return applyTime;
}


public void setProcessName(String processName){
    this.processName = processName;
}


public Date getEndTime(){
    return endTime;
}


public Long getDeeply(){
    return deeply;
}


public String getShenuser(){
    return shenuser;
}


public Long getProcessId(){
    return processId;
}


public Date getStartTime(){
    return startTime;
}


public Boolean getRejected(){
    return rejected;
}


public void setProcessId(Long processId){
    this.processId = processId;
}


public Double getProcseeDays(){
    return procseeDays;
}


public String getTypeNmae(){
    return typeNmae;
}


@Override
public String toString(){
    return "ProcessList [processId=" + processId + ", typeNmae=" + typeNmae + ", statusId=" + statusId + ", deeply=" + deeply + ", processName=" + processName + ", processDescribe=" + processDescribe + ", applyTime=" + applyTime + ", rejected=" + rejected + ", startTime=" + startTime + ", endTime=" + endTime + ", procseeDays=" + procseeDays + ", shenuser=" + shenuser + "]";
}


public User getUserId(){
    return userId;
}


public void setUserId(User userId){
    this.userId = userId;
}


}
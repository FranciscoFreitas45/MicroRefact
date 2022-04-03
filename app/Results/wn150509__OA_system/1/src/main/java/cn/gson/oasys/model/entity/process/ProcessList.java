package cn.gson.oasys.model.entity.process;
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
@Entity
@Table(name = "aoa_ProcessList")
public class ProcessList {

@Id
@Column(name = "process_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long processId;

@Column(name = "type_name")
 private  String typeNmae;

@Column(name = "status_id")
 private  Long statusId;

@Column(name = "deeply_id")
 private  Long deeply;

@Column(name = "process_name")
 private  String processName;

@Column(name = "process_des", columnDefinition = "text")
 private  String processDescribe;

@Transient
 private  User userId;

@Column(name = "apply_time")
 private  Date applyTime;

@Column(name = "is_checked")
 private  Boolean rejected;

@Column(name = "start_time")
 private  Date startTime;

@Column(name = "end_time")
 private  Date endTime;

@Column(name = "procsee_days")
 private  Double procseeDays;

@Transient
 private  Attachment proFileid;

 private  String shenuser;

@Column(name = "userIdv2")
 private Long userIdv2;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

@Column(name = "attachmentId")
 private Long attachmentId;

@Transient
 private AttachmentRequest attachmentrequest = new AttachmentRequestImpl();;


public String getProcessName(){
    return processName;
}


public void setStatusId(Long statusId){
    this.statusId = statusId;
}


public void setProcseeDays(Double procseeDays){
    this.procseeDays = procseeDays;
}


public Attachment getProFileid(){
  this.proFileid = attachmentrequest.getProFileid(this.attachmentId);
return this.proFileid;
}


public String getProcessDescribe(){
    return processDescribe;
}


public void setRejected(Boolean rejected){
    this.rejected = rejected;
}


public Long getStatusId(){
    return statusId;
}


public Date getApplyTime(){
    return applyTime;
}


public void setApplyTime(Date applyTime){
    this.applyTime = applyTime;
}


public void setProcessName(String processName){
    this.processName = processName;
}


public Date getEndTime(){
    return endTime;
}


public void setDeeply(Long deeply){
    this.deeply = deeply;
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


public void setProFileid(Attachment proFileid){
 attachmentrequest.setProFileid(proFileid,this.attachmentId);
}



public Date getStartTime(){
    return startTime;
}


public void setStartTime(Date startTime){
    this.startTime = startTime;
}


public Boolean getRejected(){
    return rejected;
}


public void setProcessDescribe(String processDescribe){
    this.processDescribe = processDescribe;
}


public void setProcessId(Long processId){
    this.processId = processId;
}


public void setShenuser(String shenuser){
    this.shenuser = shenuser;
}


public Double getProcseeDays(){
    return procseeDays;
}


public String getTypeNmae(){
    return typeNmae;
}


public void setEndTime(Date endTime){
    this.endTime = endTime;
}


@Override
public String toString(){
    return "ProcessList [processId=" + processId + ", typeNmae=" + typeNmae + ", statusId=" + statusId + ", deeply=" + deeply + ", processName=" + processName + ", processDescribe=" + processDescribe + ", applyTime=" + applyTime + ", rejected=" + rejected + ", startTime=" + startTime + ", endTime=" + endTime + ", procseeDays=" + procseeDays + ", shenuser=" + shenuser + "]";
}


public User getUserId(){
  this.userId = userrequest.getUserId(this.userIdv2);
return this.userId;
}


public void setTypeNmae(String typeNmae){
    this.typeNmae = typeNmae;
}


public void setUserId(User userId){
 userrequest.setUserId(userId,this.userIdv2);
}



}
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.Request.UserRequest;
import cn.gson.oasys.Request.Impl.UserRequestImpl;
import cn.gson.oasys.DTO.User;
public class Plan {

 private  Long planId;

 private  Long typeId;

 private  Long statusId;

 private  Long attachId;

 private  Date startTime;

 private  Date endTime;

 private  Date createTime;

 private  String title;

 private  String label;

 private  String planContent;

 private  String planSummary;

 private  String planComment;

 private  User user;

public Plan() {
}public Plan(Long typeId, Long statusId, Long attachId, Date startTime, Date endTime, Date createTime, String title, String label, String planContent, String planSummary, String planComment, User user) {
    super();
    this.typeId = typeId;
    this.statusId = statusId;
    this.attachId = attachId;
    this.startTime = startTime;
    this.endTime = endTime;
    this.createTime = createTime;
    this.title = title;
    this.label = label;
    this.planContent = planContent;
    this.planSummary = planSummary;
    this.planComment = planComment;
    this.user = user;
}
public Long getAttachId(){
    return attachId;
}


public Date getCreateTime(){
    return createTime;
}


public String getLabel(){
    return label;
}


public User getUser(){
    return user;
}


public String getPlanSummary(){
    return planSummary;
}


public Long getTypeId(){
    return typeId;
}


public Long getPlanId(){
    return planId;
}


public Long getStatusId(){
    return statusId;
}


public Date getEndTime(){
    return endTime;
}


public String getTitle(){
    return title;
}


public Date getStartTime(){
    return startTime;
}


public void setUser(User user){
    this.user = user;
}


public String getPlanContent(){
    return planContent;
}


public void setTitle(String title){
    this.title = title;
}


public void setPlanContent(String planContent){
    this.planContent = planContent;
}


public void setPlanId(Long planId){
    this.planId = planId;
}


public String getPlanComment(){
    return planComment;
}


public void setLabel(String label){
    this.label = label;
}


}
package cn.gson.oasys.model.entity.plan;
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
@Entity
@Table(name = "aoa_plan_list")
public class Plan {

@Id
@Column(name = "plan_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long planId;

@Column(name = "type_id")
 private  Long typeId;

@Column(name = "status_id")
 private  Long statusId;

@Column(name = "attach_id")
 private  Long attachId;

@Column(name = "start_time")
 private  Date startTime;

@Column(name = "end_time")
 private  Date endTime;

@Column(name = "create_time")
 private  Date createTime;

@NotEmpty(message = "标题输入框不能为空")
@Length(min = 0, max = 50)
 private  String title;

 private  String label;

@Column(name = "plan_content")
@NotEmpty(message = "计划输入框不能为空")
 private  String planContent;

@Column(name = "plan_summary")
 private  String planSummary;

@Column(name = "plan_comment")
 private  String planComment;

@Transient
 private  User user;

@Column(name = "userId")
 private Long userId;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

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


public void setStatusId(Long statusId){
    this.statusId = statusId;
}


public String getLabel(){
    return label;
}


public User getUser(){
  this.user = userrequest.getUser(this.userId);
return this.user;
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


public void setPlanSummary(String planSummary){
    this.planSummary = planSummary;
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


public void setStartTime(Date startTime){
    this.startTime = startTime;
}


public void setUser(User user){
 userrequest.setUser(user,this.userId);
}



public String getPlanContent(){
    return planContent;
}


public void setPlanComment(String planComment){
    this.planComment = planComment;
}


public void setTitle(String title){
    this.title = title;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setPlanContent(String planContent){
    this.planContent = planContent;
}


public void setTypeId(Long typeId){
    this.typeId = typeId;
}


public void setPlanId(Long planId){
    this.planId = planId;
}


public void setAttachId(Long attachId){
    this.attachId = attachId;
}


public String getPlanComment(){
    return planComment;
}


public void setEndTime(Date endTime){
    this.endTime = endTime;
}


public void setLabel(String label){
    this.label = label;
}


@Override
public String toString(){
    return "Plan [planId=" + planId + ", typeId=" + typeId + ", statusId=" + statusId + ", startTime=" + startTime + ", endTime=" + endTime + ", createTime=" + createTime + ", title=" + title + ", label=" + label + ", planContent=" + planContent + ", planSummary=" + planSummary + ", planComment=" + planComment + "]";
}


}
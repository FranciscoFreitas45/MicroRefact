package cn.gson.oasys.model.entity.discuss;
 import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.Request.UserRequest;
import cn.gson.oasys.Request.Impl.UserRequestImpl;
import cn.gson.oasys.DTO.User;
@Entity
@Table(name = "aoa_discuss_list")
public class Discuss {

@Id
@Column(name = "discuss_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long discussId;

@Column(name = "type_id")
 private  Long typeId;

@Column(name = "status_id")
 private  Long statusId;

@Column(name = "create_time")
 private  Date createTime;

@Column(name = "modify_time")
 private  Date modifyTime;

@Column(name = "visit_num")
 private  Integer visitNum;

@Column(name = "attachment_id")
 private  Integer attachmentId;

@NotEmpty(message = "标题不能为空")
 private  String title;

 private  String content;

@Transient
 private  User user;

@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@JoinColumn(name = "vote_id")
 private  VoteList voteList;

@OneToMany(mappedBy = "discuss", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
 private  Set<Reply> replys;

@ManyToMany
@JoinTable(name = "aoa_love_discuss_user", joinColumns = { @JoinColumn(name = "discuss_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
 private  Set<User> users;

@Column(name = "userId")
 private Long userId;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public Discuss(Long discussId, Date createTime, Integer visitNum, Integer attachmentId, String title, String content) {
    super();
    this.discussId = discussId;
    this.createTime = createTime;
    this.visitNum = visitNum;
    this.attachmentId = attachmentId;
    this.title = title;
    this.content = content;
}public Discuss() {
}
public VoteList getVoteList(){
    return voteList;
}


public void setDiscussId(Long discussId){
    this.discussId = discussId;
}


public Date getCreateTime(){
    return createTime;
}


public void setContent(String content){
    this.content = content;
}


public void setStatusId(Long statusId){
    this.statusId = statusId;
}


public User getUser(){
  this.user = userrequest.getUser(this.userId);
return this.user;
}


public String getContent(){
    return content;
}


public Long getTypeId(){
    return typeId;
}


public Long getStatusId(){
    return statusId;
}


public void setReplys(Set<Reply> replys){
    this.replys = replys;
}


public String getTitle(){
    return title;
}


public void setUsers(Set<User> users){
    this.users = users;
}


public Set<User> getUsers(){
    return users;
}


public Set<Reply> getReplys(){
    return replys;
}


public void setUser(User user){
 userrequest.setUser(user,this.userId);
}



public void setAttachmentId(Integer attachmentId){
    this.attachmentId = attachmentId;
}


public Long getDiscussId(){
    return discussId;
}


public Date getModifyTime(){
    return modifyTime;
}


public void setTitle(String title){
    this.title = title;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setTypeId(Long typeId){
    this.typeId = typeId;
}


public Integer getAttachmentId(){
    return attachmentId;
}


public void setModifyTime(Date modifyTime){
    this.modifyTime = modifyTime;
}


@Override
public String toString(){
    return "Discuss [discussId=" + discussId + ", typeId=" + typeId + ", statusId=" + statusId + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", visitNum=" + visitNum + ", attachmentId=" + attachmentId + ", title=" + title + ", content=" + content + "]";
}


public void setVisitNum(Integer visitNum){
    this.visitNum = visitNum;
}


public void setVoteList(VoteList voteList){
    this.voteList = voteList;
}


public Integer getVisitNum(){
    return visitNum;
}


}
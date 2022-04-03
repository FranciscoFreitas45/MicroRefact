package cn.gson.oasys.DTO;
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
public class Discuss {

 private  Long discussId;

 private  Long typeId;

 private  Long statusId;

 private  Date createTime;

 private  Date modifyTime;

 private  Integer visitNum;

 private  Integer attachmentId;

 private  String title;

 private  String content;

 private  User user;

 private  VoteList voteList;

 private  Set<Reply> replys;

 private  Set<User> users;

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
    return user;
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
    this.user = user;
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
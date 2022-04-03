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
import javax.persistence.Table;
import cn.gson.oasys.model.entity.user.User;
public class Reply {

 private  Long replyId;

 private  Date replayTime;

 private  String content;

 private  User user;

 private  Discuss discuss;

 private  Set<User> users;

 private  Set<Comment> comments;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://12";

public Reply(Date replayTime, String content, User user, Discuss discuss) {
    super();
    this.replayTime = replayTime;
    this.content = content;
    this.user = user;
    this.discuss = discuss;
}public Reply() {
}
public void setContent(String content){
    this.content = content;
}


public Long getReplyId(){
    return replyId;
}


public Discuss getDiscuss(){
    return discuss;
}


public String getContent(){
    return content;
}


public User getUser(){
    return user;
}


public Set<User> getUsers(){
    return users;
}


public Set<Comment> getComments(){
    return comments;
}


public Date getReplayTime(){
    return replayTime;
}


public void setComments(Set<Comment> comments){
    this.comments = comments;
}


public void setUsers(Set<User> users){
    this.users = users;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ replyId).concat("/setUsers"))

.queryParam("users",users)
;
restTemplate.put(builder.toUriString(),null);
}


}
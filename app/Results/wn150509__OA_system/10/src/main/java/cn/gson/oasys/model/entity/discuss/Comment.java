package cn.gson.oasys.model.entity.discuss;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.Request.UserRequest;
import cn.gson.oasys.Request.Impl.UserRequestImpl;
import cn.gson.oasys.DTO.User;
@Entity
@Table(name = "aoa_comment_list")
public class Comment {

@Id
@Column(name = "comment_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long commentId;

 private  Date time;

 private  String comment;

@Transient
 private  User user;

@ManyToOne
@JoinColumn(name = "reply_id")
 private  Reply reply;

@Column(name = "userId")
 private Long userId;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public Comment(Date time, String comment, User user, Reply reply) {
    super();
    this.time = time;
    this.comment = comment;
    this.user = user;
    this.reply = reply;
}public Comment() {
    super();
// TODO Auto-generated constructor stub
}
public Long getCommentId(){
    return commentId;
}


public Date getTime(){
    return time;
}


public Reply getReply(){
    return reply;
}


public User getUser(){
  this.user = userrequest.getUser(this.userId);
return this.user;
}


public void setReply(Reply reply){
    this.reply = reply;
}


public void setComment(String comment){
    this.comment = comment;
}


public String getComment(){
    return comment;
}


@Override
public String toString(){
    return "Comment [commentId=" + commentId + ", time=" + time + ", comment=" + comment + "]";
}


public void setUser(User user){
 userrequest.setUser(user,this.userId);
}



public void setCommentId(Long commentId){
    this.commentId = commentId;
}


public void setTime(Date time){
    this.time = time;
}


}
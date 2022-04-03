package com.dtdhehe.ptulife.entity;
 import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Comment {

@Id
 private  String id;

 private  String content;

 private  String createTime;

 private  String userId;

 private  String postId;

 private  String pid;

 private  String replyUserId;


public void setPostId(String postId){
    this.postId = postId;
}


public void setContent(String content){
    this.content = content;
}


public String getCreateTime(){
    return createTime;
}


public String getReplyUserId(){
    return replyUserId;
}


public String getContent(){
    return content;
}


public String getId(){
    return id;
}


public void setCreateTime(String createTime){
    this.createTime = createTime;
}


public void setPid(String pid){
    this.pid = pid;
}


public void setReplyUserId(String replyUserId){
    this.replyUserId = replyUserId;
}


public void setId(String id){
    this.id = id;
}


public String getPostId(){
    return postId;
}


public String getPid(){
    return pid;
}


public String getUserId(){
    return userId;
}


public void setUserId(String userId){
    this.userId = userId;
}


}
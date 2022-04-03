package com.dtdhehe.ptulife.vo;
 import com.dtdhehe.ptulife.entity.Comment;
import java.util.List;
import java.util.Map;
public class CommentVO {

 private  String id;

 private  String content;

 private  String createTime;

 private  String userId;

 private  String userName;

 private  String userHeadImg;

 private  String postId;

 private  String pid;

 private  String replyUserId;

 private  List<Map<String,Object>> commentList;


public void setPostId(String postId){
    this.postId = postId;
}


public void setContent(String content){
    this.content = content;
}


public String getCreateTime(){
    return createTime;
}


public void setCommentList(List<Map<String,Object>> commentList){
    this.commentList = commentList;
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


public void setUserHeadImg(String userHeadImg){
    this.userHeadImg = userHeadImg;
}


public List<Map<String,Object>> getCommentList(){
    return commentList;
}


public void setUserName(String userName){
    this.userName = userName;
}


public String getUserName(){
    return userName;
}


public void setId(String id){
    this.id = id;
}


public String getPostId(){
    return postId;
}


public String getUserHeadImg(){
    return userHeadImg;
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
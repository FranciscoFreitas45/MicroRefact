package com.ITBlog.ITBlogSiteBackEnd.Entity;
 import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Comment")
public class Comment {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  long commentId;

 private  long blogId;

 private  Date time;

 private  long authorId;

 private  String content;

public Comment() {
}public Comment(long blogId, long authorId, Date time, String content) {
    // 评论内容
    this.blogId = blogId;
    this.time = time;
    this.authorId = authorId;
    this.content = content;
}
public void setContent(String content){
    this.content = content;
}


public void setBlogId(long blogId){
    this.blogId = blogId;
}


public long getBlogId(){
    return blogId;
}


public long getCommentId(){
    return commentId;
}


public void setAuthorId(long authorId){
    this.authorId = authorId;
}


public Date getTime(){
    return time;
}


public String getContent(){
    return content;
}


public long getAuthorId(){
    return authorId;
}


public void setCommentId(long commentId){
    this.commentId = commentId;
}


public void setTime(Date time){
    this.time = time;
}


}
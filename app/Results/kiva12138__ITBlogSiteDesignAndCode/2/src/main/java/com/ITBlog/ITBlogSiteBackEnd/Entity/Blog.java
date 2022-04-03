package com.ITBlog.ITBlogSiteBackEnd.Entity;
 import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Blog")
public class Blog {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  long blogId;

@Column
 private  String title;

@Column
 private  String context;

@Column
 private  long authorId;

@Column
 private  Date time;

@Column
 private  int readNum;

@Column
 private  int commentsNum;

public Blog() {
}public Blog(String title, String context, long authorId, Date time) {
    this.title = title;
    this.context = context;
    this.authorId = authorId;
    this.time = time;
    this.readNum = 0;
    this.commentsNum = 0;
}
public long getBlogId(){
    return this.blogId;
}


public Date getTime(){
    return this.time;
}


public int getReadNum(){
    return this.readNum;
}


public void setTitle(String title){
    this.title = title;
}


public void CommentsNum(int commentsNum){
    this.commentsNum = commentsNum;
}


public void setReadNum(int readNum){
    this.readNum = readNum;
}


public void setBlogId(long blogId){
    this.blogId = blogId;
}


public String getTitle(){
    return this.title;
}


public void setAuthorId(long authorId){
    this.authorId = authorId;
}


public void setContext(String context){
    this.context = context;
}


public long getAuthorId(){
    return this.authorId;
}


public int getCommentsNum(){
    return this.commentsNum;
}


public String getContext(){
    return this.context;
}


public void setTime(Date time){
    this.time = time;
}


}
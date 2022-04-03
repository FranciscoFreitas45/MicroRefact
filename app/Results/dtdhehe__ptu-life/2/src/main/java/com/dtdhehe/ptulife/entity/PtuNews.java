package com.dtdhehe.ptulife.entity;
 import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class PtuNews {

@Id
 private  String newsId;

 private  String newsTitle;

 private  String newsDesc;

 private  String newsAuthor;

 private  String newsIcon;

 private  String newsDate;

 private  String newsComment;

 private  String userId;


public String getNewsDate(){
    return newsDate;
}


public void setNewsComment(String newsComment){
    this.newsComment = newsComment;
}


public void setNewsDesc(String newsDesc){
    this.newsDesc = newsDesc;
}


public String getNewsDesc(){
    return newsDesc;
}


public String getNewsAuthor(){
    return newsAuthor;
}


public void setNewsDate(String newsDate){
    this.newsDate = newsDate;
}


public String getNewsTitle(){
    return newsTitle;
}


public void setNewsTitle(String newsTitle){
    this.newsTitle = newsTitle;
}


public String getNewsId(){
    return newsId;
}


public void setNewsId(String newsId){
    this.newsId = newsId;
}


public String getNewsIcon(){
    return newsIcon;
}


public void setNewsAuthor(String newsAuthor){
    this.newsAuthor = newsAuthor;
}


public String getUserId(){
    return userId;
}


public void setUserId(String userId){
    this.userId = userId;
}


public void setNewsIcon(String newsIcon){
    this.newsIcon = newsIcon;
}


public String getNewsComment(){
    return newsComment;
}


}
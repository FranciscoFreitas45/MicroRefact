package com.dtdhehe.ptulife.DTO;
 import javax.persistence.Entity;
import javax.persistence.Id;
public class PtuNews {

 private  String newsId;

 private  String newsTitle;

 private  String newsDesc;

 private  String newsAuthor;

 private  String newsIcon;

 private  String newsDate;

 private  String newsComment;

 private  String userId;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public String getNewsDate(){
    return newsDate;
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


public String getNewsTitle(){
    return newsTitle;
}


public String getNewsId(){
    return newsId;
}


public String getNewsIcon(){
    return newsIcon;
}


public String getUserId(){
    return userId;
}


public void setNewsIcon(String newsIcon){
    this.newsIcon = newsIcon;
}


public String getNewsComment(){
    return newsComment;
}


public void setNewsDate(String newsDate){
    this.newsDate = newsDate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ newsId).concat("/setNewsDate"))

.queryParam("newsDate",newsDate)
;
restTemplate.put(builder.toUriString(),null);
}


}
package com.uec.imonitor.DTO;
 import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.uec.imonitor.common.base.BaseEntity;
import com.uec.imonitor.news.bean.NewsSpreadingAnalysisEntity;
public class RequestNewsEntity extends BaseEntity{

 private  Integer newsId;

 private  Integer requestId;

 private  String webpageCode;

 private  String originalCode;

 private  String webpageUrl;

 private  String title;

 private  String content;

 private  String classification;

 private  Integer newsType;

 private  String newsAuthor;

 private  String newsSource;

 private  String newsSection;

 private  Date reportDatetime;

 private  Date createDatetime;

 private  Date startDatetime;

 private  Date endDatetime;

 private  String comment;

 private  Integer isDeleted;

 private  String picPath;

 private  Date picDatetime;

 private  String subtitle;

 private  String newsSectionId;

 private  List<NewsSpreadingAnalysisEntity> reprintNewsList;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


public String getContent(){
    return content;
}


public String getSubtitle(){
    return subtitle;
}


public List<NewsSpreadingAnalysisEntity> getReprintNewsList(){
    return reprintNewsList;
}


public String getNewsAuthor(){
    return newsAuthor;
}


public String getOriginalCode(){
    return originalCode;
}


public Integer getIsDeleted(){
    return isDeleted;
}


public Date getPicDatetime(){
    return picDatetime;
}


public String getTitle(){
    return title;
}


public String getPicPath(){
    return picPath;
}


public Integer getNewsId(){
    return newsId;
}


public String getNewsSource(){
    return newsSource;
}


public Date getStartDatetime(){
    return startDatetime;
}


public String getComment(){
    return comment;
}


public String getNewsSectionId(){
    return newsSectionId;
}


public Integer getNewsType(){
    return newsType;
}


public Integer getRequestId(){
    return requestId;
}


public Date getEndDatetime(){
    return endDatetime;
}


public Date getReportDatetime(){
    return reportDatetime;
}


public String getNewsSection(){
    return newsSection;
}


public String getWebpageUrl(){
    return webpageUrl;
}


public Date getCreateDatetime(){
    return createDatetime;
}


public String getWebpageCode(){
    return webpageCode;
}


public String getClassification(){
    return classification;
}


public void setNewsSectionId(String newsSectionId){
    this.newsSectionId = newsSectionId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNewsSectionId"))

.queryParam("newsSectionId",newsSectionId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNewsSection(String newsSection){
    this.newsSection = newsSection;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNewsSection"))

.queryParam("newsSection",newsSection)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTitle(String title){
    this.title = title;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTitle"))

.queryParam("title",title)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSubtitle(String subtitle){
    this.subtitle = subtitle;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSubtitle"))

.queryParam("subtitle",subtitle)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNewsAuthor(String newsAuthor){
    this.newsAuthor = newsAuthor;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNewsAuthor"))

.queryParam("newsAuthor",newsAuthor)
;
restTemplate.put(builder.toUriString(),null);
}


public void setContent(String content){
    this.content = content;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setContent"))

.queryParam("content",content)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIsDeleted(Integer isDeleted){
    this.isDeleted = isDeleted;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIsDeleted"))

.queryParam("isDeleted",isDeleted)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRequestId(Integer requestId){
    this.requestId = requestId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRequestId"))

.queryParam("requestId",requestId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNewsSource(String newsSource){
    this.newsSource = newsSource;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNewsSource"))

.queryParam("newsSource",newsSource)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOriginalCode(String originalCode){
    this.originalCode = originalCode;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOriginalCode"))

.queryParam("originalCode",originalCode)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWebpageCode(String webpageCode){
    this.webpageCode = webpageCode;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setWebpageCode"))

.queryParam("webpageCode",webpageCode)
;
restTemplate.put(builder.toUriString(),null);
}


public void setReportDatetime(Date reportDatetime){
    this.reportDatetime = reportDatetime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setReportDatetime"))

.queryParam("reportDatetime",reportDatetime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCreateDatetime(Date createDatetime){
    this.createDatetime = createDatetime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCreateDatetime"))

.queryParam("createDatetime",createDatetime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setStartDatetime(Date startDatetime){
    this.startDatetime = startDatetime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setStartDatetime"))

.queryParam("startDatetime",startDatetime)
;
restTemplate.put(builder.toUriString(),null);
}


public void setEndDatetime(Date endDatetime){
    this.endDatetime = endDatetime;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setEndDatetime"))

.queryParam("endDatetime",endDatetime)
;
restTemplate.put(builder.toUriString(),null);
}


}
package com.uec.imonitor.request.bean;
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
@Entity
@Table(name = "request_news")
public class RequestNewsEntity extends BaseEntity{

// 指明这个属性映射为数据库的主键
@Id
@GeneratedValue
@Column(name = "news_id", nullable = false)
 private  Integer newsId;

@Column(name = "request_id")
 private  Integer requestId;

@Column(name = "webpage_code")
 private  String webpageCode;

@Column(name = "original_code")
 private  String originalCode;

@Column(name = "webpage_url")
 private  String webpageUrl;

@Column(name = "title")
 private  String title;

@Column(name = "content")
 private  String content;

@Column(name = "classification")
 private  String classification;

@Column(name = "news_type")
 private  Integer newsType;

@Column(name = "news_author")
 private  String newsAuthor;

@Column(name = "news_source")
 private  String newsSource;

@Column(name = "news_section")
 private  String newsSection;

@Column(name = "report_datetime")
 private  Date reportDatetime;

@Column(name = "create_datetime")
 private  Date createDatetime;

@Column(name = "start_datetime")
 private  Date startDatetime;

@Column(name = "end_datetime")
 private  Date endDatetime;

@Column(name = "comment")
 private  String comment;

@Column(name = "is_deleted")
 private  Integer isDeleted;

@Column(name = "pic_path")
 private  String picPath;

@Column(name = "pic_datetime")
 private  Date picDatetime;

@Column(name = "subtitle")
 private  String subtitle;

@Column(name = "news_section_id")
 private  String newsSectionId;

@OneToMany(cascade = { CascadeType.ALL })
@JoinColumn(name = "news_id")
 private  List<NewsSpreadingAnalysisEntity> reprintNewsList;


public void setContent(String content){
    this.content = content;
}


public void setOriginalCode(String originalCode){
    this.originalCode = originalCode;
}


public void setWebpageCode(String webpageCode){
    this.webpageCode = webpageCode;
}


public String getContent(){
    return content;
}


public void setPicDatetime(Date picDatetime){
    this.picDatetime = picDatetime;
}


public String getSubtitle(){
    return subtitle;
}


public void setStartDatetime(Date startDatetime){
    this.startDatetime = startDatetime;
}


public void setNewsSection(String newsSection){
    this.newsSection = newsSection;
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


public void setNewsSource(String newsSource){
    this.newsSource = newsSource;
}


public Integer getIsDeleted(){
    return isDeleted;
}


public void setIsDeleted(Integer isDeleted){
    this.isDeleted = isDeleted;
}


public Date getPicDatetime(){
    return picDatetime;
}


public String getTitle(){
    return title;
}


public void setRequestId(Integer requestId){
    this.requestId = requestId;
}


public String getPicPath(){
    return picPath;
}


public Integer getNewsId(){
    return newsId;
}


public void setNewsId(Integer newsId){
    this.newsId = newsId;
}


public void setWebpageUrl(String webpageUrl){
    this.webpageUrl = webpageUrl;
}


public String getNewsSource(){
    return newsSource;
}


public void setNewsType(Integer newsType){
    this.newsType = newsType;
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


public void setSubtitle(String subtitle){
    this.subtitle = subtitle;
}


public Integer getNewsType(){
    return newsType;
}


public void setNewsAuthor(String newsAuthor){
    this.newsAuthor = newsAuthor;
}


public Integer getRequestId(){
    return requestId;
}


public Date getEndDatetime(){
    return endDatetime;
}


public void setNewsSectionId(String newsSectionId){
    this.newsSectionId = newsSectionId;
}


public void setTitle(String title){
    this.title = title;
}


public void setCreateDatetime(Date createDatetime){
    this.createDatetime = createDatetime;
}


public Date getReportDatetime(){
    return reportDatetime;
}


public String getNewsSection(){
    return newsSection;
}


public void setClassification(String classification){
    this.classification = classification;
}


public void setEndDatetime(Date endDatetime){
    this.endDatetime = endDatetime;
}


public void setReportDatetime(Date reportDatetime){
    this.reportDatetime = reportDatetime;
}


public String getWebpageUrl(){
    return webpageUrl;
}


public Date getCreateDatetime(){
    return createDatetime;
}


public void setPicPath(String picPath){
    this.picPath = picPath;
}


public void setComment(String comment){
    this.comment = comment;
}


public void setReprintNewsList(List<NewsSpreadingAnalysisEntity> reprintNewsList){
    this.reprintNewsList = reprintNewsList;
}


public String getWebpageCode(){
    return webpageCode;
}


public String getClassification(){
    return classification;
}


}
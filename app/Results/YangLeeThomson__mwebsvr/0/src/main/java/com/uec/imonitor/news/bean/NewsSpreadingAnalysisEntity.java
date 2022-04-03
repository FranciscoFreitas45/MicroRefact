package com.uec.imonitor.news.bean;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "news_spreading_analysis")
public class NewsSpreadingAnalysisEntity {

// 指明这个属性映射为数据库的主键
@Id
@GeneratedValue
@Column(name = "innerid", nullable = false)
 private  Integer innerid;

@Column(name = "webpage_code")
 private  String webpageCode;

@Column(name = "request_id")
 private  Integer requestId;

@Column(name = "news_id")
 private  Integer newsId;

@Column(name = "title")
 private  String title;

@Column(name = "req_news_title")
 private  String reqNewsTitle;

@Column(name = "status")
 private  Integer status;

@Column(name = "is_deleted")
 private  Integer isDeleted;

@Column(name = "reprint_type")
 private  Integer reprintType;

@Column(name = "create_datetime")
 private  Date createDatetime;

@Column(name = "update_datetime")
 private  Date updateDatetime;

@Column(name = "title_similarity")
 private  Double titleSimilarity;

@Column(name = "content_similarity")
 private  Double contentSimilarity;


public void setReprintType(Integer reprintType){
    this.reprintType = reprintType;
}


public Date getUpdateDatetime(){
    return updateDatetime;
}


public String getReqNewsTitle(){
    return reqNewsTitle;
}


public void setWebpageCode(String webpageCode){
    this.webpageCode = webpageCode;
}


public void setReqNewsTitle(String reqNewsTitle){
    this.reqNewsTitle = reqNewsTitle;
}


public void setContentSimilarity(Double contentSimilarity){
    this.contentSimilarity = contentSimilarity;
}


public Integer getStatus(){
    return status;
}


public Integer getIsDeleted(){
    return isDeleted;
}


public void setIsDeleted(Integer isDeleted){
    this.isDeleted = isDeleted;
}


public String getTitle(){
    return title;
}


public Integer getInnerid(){
    return innerid;
}


public void setRequestId(Integer requestId){
    this.requestId = requestId;
}


public Integer getNewsId(){
    return newsId;
}


public void setNewsId(Integer newsId){
    this.newsId = newsId;
}


public Double getContentSimilarity(){
    return contentSimilarity;
}


public Integer getRequestId(){
    return requestId;
}


public void setTitle(String title){
    this.title = title;
}


public void setCreateDatetime(Date createDatetime){
    this.createDatetime = createDatetime;
}


public void setStatus(Integer status){
    this.status = status;
}


public void setInnerid(Integer innerid){
    this.innerid = innerid;
}


public Integer getReprintType(){
    return reprintType;
}


public Date getCreateDatetime(){
    return createDatetime;
}


public void setUpdateDatetime(Date updateDatetime){
    this.updateDatetime = updateDatetime;
}


public void setTitleSimilarity(Double titleSimilarity){
    this.titleSimilarity = titleSimilarity;
}


public Double getTitleSimilarity(){
    return titleSimilarity;
}


public String getWebpageCode(){
    return webpageCode;
}


}
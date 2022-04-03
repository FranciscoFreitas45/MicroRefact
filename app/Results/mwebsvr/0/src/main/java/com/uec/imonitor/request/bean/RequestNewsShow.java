package com.uec.imonitor.request.bean;
 import java.util.Date;
import javax.persistence.Id;
import org.springframework.beans.BeanUtils;
import com.uec.imonitor.common.base.BaseEntity;
public class RequestNewsShow extends BaseEntity{

// 指明这个属性映射为数据库的主键
@Id
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

 private  Date startDatetime;

 private  Date endDatetime;

 private  Integer isDeleted;

 private  String picPath;

 private  Date picDatetime;

 private  String subtitle;

 private  String newsSectionId;

 private  String newsTypeName;

 private  Integer wordCount;

 private  Integer reprintCount;

public RequestNewsShow() {
}public RequestNewsShow(RequestNewsEntity reqNews) {
    BeanUtils.copyProperties(reqNews, this);
    super.setEsPrimaryId(String.valueOf(reqNews.getNewsId()));
}
public Integer getWordCount(){
    return wordCount;
}


public void setOriginalCode(String originalCode){
    this.originalCode = originalCode;
}


public void setContent(String content){
    this.content = content;
}


public void setWebpageCode(String webpageCode){
    this.webpageCode = webpageCode;
}


public void setPicDatetime(Date picDatetime){
    this.picDatetime = picDatetime;
}


public String getSubtitle(){
    return subtitle;
}


public String getContent(){
    return content;
}


public void setStartDatetime(Date startDatetime){
    this.startDatetime = startDatetime;
}


public Integer getReprintCount(){
    return reprintCount;
}


public void setNewsSection(String newsSection){
    this.newsSection = newsSection;
}


public String getOriginalCode(){
    return originalCode;
}


public String getNewsAuthor(){
    return newsAuthor;
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


public String getNewsSectionId(){
    return newsSectionId;
}


public void setSubtitle(String subtitle){
    this.subtitle = subtitle;
}


public void setReprintCount(Integer reprintCount){
    this.reprintCount = reprintCount;
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


public void setWordCount(Integer wordCount){
    this.wordCount = wordCount;
}


public String getNewsTypeName(){
    return newsTypeName;
}


public void setNewsSectionId(String newsSectionId){
    this.newsSectionId = newsSectionId;
}


public void setTitle(String title){
    this.title = title;
}


public Date getReportDatetime(){
    return reportDatetime;
}


public void setNewsTypeName(String newsTypeName){
    this.newsTypeName = newsTypeName;
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


public void setPicPath(String picPath){
    this.picPath = picPath;
}


public String getWebpageCode(){
    return webpageCode;
}


public String getClassification(){
    return classification;
}


}
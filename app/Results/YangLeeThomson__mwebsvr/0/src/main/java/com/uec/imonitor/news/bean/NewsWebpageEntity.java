package com.uec.imonitor.news.bean;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.uec.imonitor.common.base.BaseEntity;
@Entity
@Table(name = "news_webpage")
public class NewsWebpageEntity extends BaseEntity{

// 指明这个属性映射为数据库的主键
@Id
@GeneratedValue
@Column(name = "webpage_code")
 private  String webpageCode;

@Column(name = "news_type")
 private  Integer newsType;

@Column(name = "news_section")
 private  String newsSection;

@Column(name = "webpage_url")
 private  String webpageUrl;

@Column(name = "title")
 private  String title;

@Column(name = "keywords")
 private  String keywords;

@Column(name = "source_report")
 private  String sourceReport;

@Column(name = "source_crawl")
 private  String sourceCrawl;

@Column(name = "region")
 private  Integer region;

@Column(name = "classification")
 private  String classification;

@Column(name = "author")
 private  String author;

@Column(name = "crawl_datetime")
 private  Date crawlDatetime;

@Column(name = "release_datetime_str")
 private  String releaseDatetimeStr;

@Column(name = "release_datetime")
 private  Date releaseDatetime;

@Column(name = "update_datetime")
 private  Date updateDatetime;

@Column(name = "is_deleted")
 private  Integer isDeleted;

@Column(name = "weibo_id")
 private  String weiboId;

@Column(name = "weibo_pid")
 private  String weiboPid;

@Column(name = "weibo_root_id")
 private  String weiboRootId;

@Column(name = "pic_path")
 private  String picPath;

@Column(name = "pic_datetime")
 private  Date picDatetime;

@Column(name = "subtitle")
 private  String subtitle;


public String getAuthor(){
    return author;
}


public void setReleaseDatetimeStr(String releaseDatetimeStr){
    this.releaseDatetimeStr = releaseDatetimeStr;
}


public Date getUpdateDatetime(){
    return updateDatetime;
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


public void setNewsSection(String newsSection){
    this.newsSection = newsSection;
}


public String getWeiboId(){
    return weiboId;
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


public void setReleaseDatetime(Date releaseDatetime){
    this.releaseDatetime = releaseDatetime;
}


public String getPicPath(){
    return picPath;
}


public void setWebpageUrl(String webpageUrl){
    this.webpageUrl = webpageUrl;
}


public String getWeiboPid(){
    return weiboPid;
}


public void setNewsType(Integer newsType){
    this.newsType = newsType;
}


public Date getCrawlDatetime(){
    return crawlDatetime;
}


public String getWeiboRootId(){
    return weiboRootId;
}


public void setSubtitle(String subtitle){
    this.subtitle = subtitle;
}


public void setWeiboPid(String weiboPid){
    this.weiboPid = weiboPid;
}


public Integer getNewsType(){
    return newsType;
}


public void setRegion(Integer region){
    this.region = region;
}


public void setSourceReport(String sourceReport){
    this.sourceReport = sourceReport;
}


public String getKeywords(){
    return keywords;
}


public void setTitle(String title){
    this.title = title;
}


public String getSourceCrawl(){
    return sourceCrawl;
}


public String getSourceReport(){
    return sourceReport;
}


public void setCrawlDatetime(Date crawlDatetime){
    this.crawlDatetime = crawlDatetime;
}


public void setKeywords(String keywords){
    this.keywords = keywords;
}


public String getReleaseDatetimeStr(){
    return releaseDatetimeStr;
}


public void setSourceCrawl(String sourceCrawl){
    this.sourceCrawl = sourceCrawl;
}


public String getNewsSection(){
    return newsSection;
}


public void setClassification(String classification){
    this.classification = classification;
}


public Date getReleaseDatetime(){
    return releaseDatetime;
}


public void setWeiboId(String weiboId){
    this.weiboId = weiboId;
}


public String getWebpageUrl(){
    return webpageUrl;
}


public void setUpdateDatetime(Date updateDatetime){
    this.updateDatetime = updateDatetime;
}


public void setPicPath(String picPath){
    this.picPath = picPath;
}


public void setAuthor(String author){
    this.author = author;
}


public Integer getRegion(){
    return region;
}


public void setWeiboRootId(String weiboRootId){
    this.weiboRootId = weiboRootId;
}


public String getWebpageCode(){
    return webpageCode;
}


public String getClassification(){
    return classification;
}


}
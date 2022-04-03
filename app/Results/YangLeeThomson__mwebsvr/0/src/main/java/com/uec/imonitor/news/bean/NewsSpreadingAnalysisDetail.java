package com.uec.imonitor.news.bean;
 import java.util.Date;
import org.springframework.beans.BeanUtils;
import com.uec.imonitor.common.base.CommonDate;
import com.uec.imonitor.Interface.CommonDate;
import com.uec.imonitor.Interface.CommonDate;
import com.uec.imonitor.Interface.CommonDate;
import com.uec.imonitor.Interface.CommonDate;
public class NewsSpreadingAnalysisDetail extends NewsWebpageEntity{

 private  Integer innerid;

 private  Integer newsId;

 private  String reqNewsTitle;

 private  Integer status;

 private  Integer reprintType;

 private  Date createDatetime;

 private  String statusName;

 private  String reprintTypeName;

 private  String newsTypeName;

 private  String regionName;

 private  String country;

 private  String province;

 private  String provinceShort;

 private  String city;

 private  String cityShort;

 private  Double titleSimilarity;

 private  Double contentSimilarity;

 private  Integer wordCount;

 private  Integer requestId;

 private  CommonDate crawlDatetimeDetail;

 private  CommonDate releaseDatetimeDetail;

 private  Date reqReportDatetime;

public NewsSpreadingAnalysisDetail() {
}public NewsSpreadingAnalysisDetail(NewsWebpageEntity entity) {
    BeanUtils.copyProperties(entity, this);
    Date crawlDatetime = this.getCrawlDatetime();
    if (null != crawlDatetime) {
        crawlDatetimeDetail = new CommonDate(crawlDatetime);
    }
    Date releaseDatetime = this.getReleaseDatetime();
    if (null != releaseDatetime) {
        releaseDatetimeDetail = new CommonDate(releaseDatetime);
    }
}
public void setCountry(String country){
    this.country = country;
}


public Integer getWordCount(){
    return wordCount;
}


public void setCrawlDatetimeDetail(CommonDate crawlDatetimeDetail){
    this.crawlDatetimeDetail = crawlDatetimeDetail;
}


public void setReprintType(Integer reprintType){
    this.reprintType = reprintType;
}


public String getCountry(){
    return country;
}


public void setProvince(String province){
    this.province = province;
}


public String getReprintTypeName(){
    return reprintTypeName;
}


public String getReqNewsTitle(){
    return reqNewsTitle;
}


public void setReqNewsTitle(String reqNewsTitle){
    this.reqNewsTitle = reqNewsTitle;
}


public void setProvinceShort(String provinceShort){
    this.provinceShort = provinceShort;
}


public void setContentSimilarity(Double contentSimilarity){
    this.contentSimilarity = contentSimilarity;
}


public Integer getStatus(){
    return status;
}


public String getRegionName(){
    return regionName;
}


public Date getReqReportDatetime(){
    return reqReportDatetime;
}


public CommonDate getCrawlDatetimeDetail(){
    return crawlDatetimeDetail;
}


public String getCityShort(){
    return cityShort;
}


public Integer getInnerid(){
    return innerid;
}


public String getStatusName(){
    return statusName;
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


public String getProvince(){
    return province;
}


public void setRegionName(String regionName){
    this.regionName = regionName;
}


public Double getContentSimilarity(){
    return contentSimilarity;
}


public Integer getRequestId(){
    return requestId;
}


public void setReprintTypeName(String reprintTypeName){
    this.reprintTypeName = reprintTypeName;
}


public String getCity(){
    return city;
}


public void setWordCount(Integer wordCount){
    this.wordCount = wordCount;
}


public String getNewsTypeName(){
    return newsTypeName;
}


public void setCity(String city){
    this.city = city;
}


public void setReqReportDatetime(Date reqReportDatetime){
    this.reqReportDatetime = reqReportDatetime;
}


public void setCreateDatetime(Date createDatetime){
    this.createDatetime = createDatetime;
}


public void setCityShort(String cityShort){
    this.cityShort = cityShort;
}


public void setStatus(Integer status){
    this.status = status;
}


public void setNewsTypeName(String newsTypeName){
    this.newsTypeName = newsTypeName;
}


public void setReleaseDatetimeDetail(CommonDate releaseDatetimeDetail){
    this.releaseDatetimeDetail = releaseDatetimeDetail;
}


public void setInnerid(Integer innerid){
    this.innerid = innerid;
}


public CommonDate getReleaseDatetimeDetail(){
    return releaseDatetimeDetail;
}


public Integer getReprintType(){
    return reprintType;
}


public Date getCreateDatetime(){
    return createDatetime;
}


public void setStatusName(String statusName){
    this.statusName = statusName;
}


public void setTitleSimilarity(Double titleSimilarity){
    this.titleSimilarity = titleSimilarity;
}


public Double getTitleSimilarity(){
    return titleSimilarity;
}


public String getProvinceShort(){
    return provinceShort;
}


}
package com.uec.imonitor.DTO;
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
public Integer getWordCount(){
    return wordCount;
}


public void setReprintType(Integer reprintType){
    this.reprintType = reprintType;
}


public String getCountry(){
    return country;
}


public String getReprintTypeName(){
    return reprintTypeName;
}


public String getReqNewsTitle(){
    return reqNewsTitle;
}


public void setProvinceShort(String provinceShort){
    this.provinceShort = provinceShort;
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


public Integer getNewsId(){
    return newsId;
}


public String getProvince(){
    return province;
}


public Double getContentSimilarity(){
    return contentSimilarity;
}


public Integer getRequestId(){
    return requestId;
}


public String getCity(){
    return city;
}


public String getNewsTypeName(){
    return newsTypeName;
}


public void setReqReportDatetime(Date reqReportDatetime){
    this.reqReportDatetime = reqReportDatetime;
}


public void setCityShort(String cityShort){
    this.cityShort = cityShort;
}


public void setNewsTypeName(String newsTypeName){
    this.newsTypeName = newsTypeName;
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
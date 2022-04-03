package com.uec.imonitor.news.bean;
 import java.util.Date;
import java.util.List;
import com.uec.imonitor.es.bean.params.AggsTermParams;
import com.uec.imonitor.Interface.AggsTermParams;
public class QueryNewsParam {

 private  Integer requestId;

 private  List<Integer> newsIds;

 private  Integer reprintType;

 private  Integer status;

 private  String sourceCrawl;

 private  String queryStr;

 private  Date startTime;

 private  Date endTime;

 private  String sortField;

 private  String sortType;

 private  Integer pageStart;

 private  Integer pageSize;

 private  AggsTermParams aggsParams;

 private  Date reportStartTime;

 private  Date reportEndTime;


public void setReprintType(Integer reprintType){
    this.reprintType = reprintType;
}


public void setQueryStr(String queryStr){
    this.queryStr = queryStr;
}


public String getSortType(){
    return sortType;
}


public void setReportEndTime(Date reportEndTime){
    this.reportEndTime = reportEndTime;
}


public Integer getStatus(){
    return status;
}


public Date getEndTime(){
    return endTime;
}


public Date getReportStartTime(){
    return reportStartTime;
}


public void setRequestId(Integer requestId){
    this.requestId = requestId;
}


public String getSortField(){
    return sortField;
}


public void setPageStart(Integer pageStart){
    this.pageStart = pageStart;
}


public void setReportStartTime(Date reportStartTime){
    this.reportStartTime = reportStartTime;
}


public void setSortType(String sortType){
    this.sortType = sortType;
}


public Date getStartTime(){
    return startTime;
}


public void setStartTime(Date startTime){
    this.startTime = startTime;
}


public AggsTermParams getAggsParams(){
    return aggsParams;
}


public Date getReportEndTime(){
    return reportEndTime;
}


public Integer getRequestId(){
    return requestId;
}


public void setSortField(String sortField){
    this.sortField = sortField;
}


public void setNewsIds(List<Integer> newsIds){
    this.newsIds = newsIds;
}


public String getSourceCrawl(){
    return sourceCrawl;
}


public void setAggsParams(AggsTermParams aggsParams){
    this.aggsParams = aggsParams;
}


public void setStatus(Integer status){
    this.status = status;
}


public Integer getPageStart(){
    return pageStart;
}


public void setSourceCrawl(String sourceCrawl){
    this.sourceCrawl = sourceCrawl;
}


public String getQueryStr(){
    return queryStr;
}


public Integer getReprintType(){
    return reprintType;
}


public Integer getPageSize(){
    return pageSize;
}


public void setEndTime(Date endTime){
    this.endTime = endTime;
}


public List<Integer> getNewsIds(){
    return newsIds;
}


public void setPageSize(Integer pageSize){
    this.pageSize = pageSize;
}


}
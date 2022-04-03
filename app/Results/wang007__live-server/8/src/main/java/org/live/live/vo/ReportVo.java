package org.live.live.vo;
 import java.util.Date;
public class ReportVo {

 private  String id;

 private  String reportNum;

 private  String reporterNum;

 private  String reportName;

 private  String liveRoomNum;

 private  String liveRoomName;

 private  Date createTime;

 private  boolean handleType;

public ReportVo() {
}public ReportVo(String id, String reportNum, String reporterNum, String reportName, String liveRoomNum, String liveRoomName, Date createTime, boolean handleType) {
    this.id = id;
    this.reportNum = reportNum;
    this.reporterNum = reporterNum;
    this.reportName = reportName;
    this.liveRoomNum = liveRoomNum;
    this.liveRoomName = liveRoomName;
    this.createTime = createTime;
    this.handleType = handleType;
}
public String getReportNum(){
    return reportNum;
}


public void setReportNum(String reportNum){
    this.reportNum = reportNum;
}


public void setReportName(String reportName){
    this.reportName = reportName;
}


public Date getCreateTime(){
    return createTime;
}


public String getLiveRoomNum(){
    return liveRoomNum;
}


public void setLiveRoomName(String liveRoomName){
    this.liveRoomName = liveRoomName;
}


public String getLiveRoomName(){
    return liveRoomName;
}


public String getReportName(){
    return reportName;
}


public String getId(){
    return id;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setLiveRoomNum(String liveRoomNum){
    this.liveRoomNum = liveRoomNum;
}


public boolean isHandleType(){
    return handleType;
}


public String getReporterNum(){
    return reporterNum;
}


public void setHandleType(boolean handleType){
    this.handleType = handleType;
}


public void setId(String id){
    this.id = id;
}


public void setReporterNum(String reporterNum){
    this.reporterNum = reporterNum;
}


}
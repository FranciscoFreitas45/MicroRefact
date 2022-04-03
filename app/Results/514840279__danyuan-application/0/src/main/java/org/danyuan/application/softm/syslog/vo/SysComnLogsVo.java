package org.danyuan.application.softm.syslog.vo;
 import org.danyuan.application.common.base.Pagination;
import org.danyuan.application.softm.syslog.po.SysComnLogs;
public class SysComnLogsVo extends Pagination<SysComnLogs>{

 private String strartCreateTime;

 private String endCreateTime;


public void setStrartCreateTime(String strartCreateTime){
    this.strartCreateTime = strartCreateTime;
}


public void setEndCreateTime(String endCreateTime){
    this.endCreateTime = endCreateTime;
}


public String getEndCreateTime(){
    return endCreateTime;
}


@Override
public String toString(){
    return "SysComnLogsVo [pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", uuid=" + uuid + ", searchText=" + searchText + ", username=" + username + ", info=" + info + "]";
}


public String getStrartCreateTime(){
    return strartCreateTime;
}


}
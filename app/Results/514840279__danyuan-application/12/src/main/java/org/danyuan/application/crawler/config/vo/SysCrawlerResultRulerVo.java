package org.danyuan.application.crawler.config.vo;
 import java.util.ArrayList;
import java.util.List;
import org.danyuan.application.crawler.config.po.SysCrawlerResultRulerInfo;
public class SysCrawlerResultRulerVo {

 public  String tableUuid;

 public  String rulerUuid;

 public  List<SysCrawlerResultRulerInfo> list;


public String getRulerUuid(){
    return rulerUuid;
}


public void setRulerUuid(String rulerUuid){
    this.rulerUuid = rulerUuid;
}


public String getTableUuid(){
    return tableUuid;
}


public List<SysCrawlerResultRulerInfo> getList(){
    return list;
}


public void setTableUuid(String tableUuid){
    this.tableUuid = tableUuid;
}


public void setList(List<SysCrawlerResultRulerInfo> list){
    this.list = list;
}


}
package org.danyuan.application.crawler.task.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_crawler_task_info")
@NamedQuery(name = "SysCrawlerTaskInfo.findAll", query = "SELECT s FROM SysCrawlerTaskInfo s")
public class SysCrawlerTaskInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "excute_flag", precision = 10)
 private  Integer excuteFlag;

@Column(name = "task_flag", precision = 10)
 private  Integer taskFlag;

@Column(name = "url_type")
 private  String urlType;

@Column(name = "request_data")
 private  String requestData;

@Column(name = "charset")
 private  String charset;

@Column(name = "detail_conf")
 private  String detailConf;

@Column(name = "request_type")
 private  String requestType;

@Column(name = "url_name")
 private  String urlName;

@Column(name = "task_name")
 private  String taskName;

@Column(name = "dict_conf")
 private  String dictConf;

@Column(name = "web_icon")
 private  String webIcon;

@Column(name = "excute_batch")
 private  String excuteBatch;

@Column(name = "url")
 private  String url;

@Column(name = "list_conf")
 private  String listConf;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysCrawlerTaskInfo() {
    super();
}
public String getDictConf(){
    return dictConf;
}


public void setCharset(String charset){
    this.charset = charset;
}


public String getUrlName(){
    return urlName;
}


public String getTaskName(){
    return taskName;
}


public String getWebIcon(){
    return webIcon;
}


public void setExcuteFlag(Integer excuteFlag){
    this.excuteFlag = excuteFlag;
}


public void setTaskFlag(Integer taskFlag){
    this.taskFlag = taskFlag;
}


public String getCharset(){
    return charset;
}


public void setUrlName(String urlName){
    this.urlName = urlName;
}


public void setWebIcon(String webIcon){
    this.webIcon = webIcon;
}


public void setDetailConf(String detailConf){
    this.detailConf = detailConf;
}


public String getRequestType(){
    return requestType;
}


public String getExcuteBatch(){
    return excuteBatch;
}


public void setDictConf(String dictConf){
    this.dictConf = dictConf;
}


public void setRequestData(String requestData){
    this.requestData = requestData;
}


public void setListConf(String listConf){
    this.listConf = listConf;
}


public void setUrlType(String urlType){
    this.urlType = urlType;
}


public String getListConf(){
    return listConf;
}


public Integer getExcuteFlag(){
    return excuteFlag;
}


public Integer getTaskFlag(){
    return taskFlag;
}


public void setRequestType(String requestType){
    this.requestType = requestType;
}


public void setTaskName(String taskName){
    this.taskName = taskName;
}


public void setUrl(String url){
    this.url = url;
}


public String getUrl(){
    return url;
}


public String getRequestData(){
    return requestData;
}


public String getUrlType(){
    return urlType;
}


public void setExcuteBatch(String excuteBatch){
    this.excuteBatch = excuteBatch;
}


public String getDetailConf(){
    return detailConf;
}


}
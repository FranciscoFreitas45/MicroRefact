package org.danyuan.application.crawler.task.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_crawler_task_err_info")
@NamedQuery(name = "SysCrawlerTaskErrInfo.findAll", query = "SELECT s FROM SysCrawlerTaskErrInfo s")
public class SysCrawlerTaskErrInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "task_uuid")
 private  String taskUuid;

@Column(name = "error_msg")
 private  String errorMsg;

@Column(name = "url")
 private  String url;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysCrawlerTaskErrInfo() {
    super();
}
public String getUrl(){
    return url;
}


public void setTaskUuid(String taskUuid){
    this.taskUuid = taskUuid;
}


public void setErrorMsg(String errorMsg){
    this.errorMsg = errorMsg;
}


public String getTaskUuid(){
    return taskUuid;
}


public String getErrorMsg(){
    return errorMsg;
}


public void setUrl(String url){
    this.url = url;
}


}
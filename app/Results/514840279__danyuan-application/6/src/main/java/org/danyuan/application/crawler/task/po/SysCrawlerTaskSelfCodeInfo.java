package org.danyuan.application.crawler.task.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_crawler_task_self_code_info")
@NamedQuery(name = "SysCrawlerTaskSelfCodeInfo.findAll", query = "SELECT s FROM SysCrawlerTaskSelfCodeInfo s")
public class SysCrawlerTaskSelfCodeInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "zip_file_name")
 private  String zipFileName;

@Column(name = "file_path")
 private  String filePath;

@Column(name = "task_uuid")
 private  String taskUuid;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysCrawlerTaskSelfCodeInfo() {
    super();
}
public String getZipFileName(){
    return zipFileName;
}


public void setTaskUuid(String taskUuid){
    this.taskUuid = taskUuid;
}


public String getFilePath(){
    return filePath;
}


public void setFilePath(String filePath){
    this.filePath = filePath;
}


public String getTaskUuid(){
    return taskUuid;
}


public void setZipFileName(String zipFileName){
    this.zipFileName = zipFileName;
}


}
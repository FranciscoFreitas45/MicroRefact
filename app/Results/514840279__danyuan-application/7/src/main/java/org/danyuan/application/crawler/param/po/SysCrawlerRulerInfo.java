package org.danyuan.application.crawler.param.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_crawler_ruler_info")
@NamedQuery(name = "SysCrawlerRulerInfo.findAll", query = "SELECT s FROM SysCrawlerRulerInfo s")
public class SysCrawlerRulerInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "name")
 private  String name;

@Column(name = "content_info")
 private  String contentInfo;

@Column(name = "task_uuid")
 private  String taskUuid;

@Column(name = "statue")
 private  String statue;

@Column(name = "type")
 private  String type;

@Column(name = "content_json_info")
 private  String contentJsonInfo;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysCrawlerRulerInfo() {
    super();
}
public void setName(String name){
    this.name = name;
}


public void setContentInfo(String contentInfo){
    this.contentInfo = contentInfo;
}


public void setTaskUuid(String taskUuid){
    this.taskUuid = taskUuid;
}


public String getContentJsonInfo(){
    return contentJsonInfo;
}


public String getName(){
    return name;
}


public String getType(){
    return type;
}


public void setStatue(String statue){
    this.statue = statue;
}


public void setType(String type){
    this.type = type;
}


public String getContentInfo(){
    return contentInfo;
}


public String getTaskUuid(){
    return taskUuid;
}


public String getStatue(){
    return statue;
}


public void setContentJsonInfo(String contentJsonInfo){
    this.contentJsonInfo = contentJsonInfo;
}


}
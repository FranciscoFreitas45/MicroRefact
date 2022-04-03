package org.danyuan.application.file.upload.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_file_upload_info")
@NamedQuery(name = "SysFileUploadInfo.findAll", query = "SELECT s FROM SysFileUploadInfo s")
public class SysFileUploadInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "name")
 private  String name;

@Column(name = "size", precision = 10)
 private  Integer size;

@Column(name = "type")
 private  String type;

@Column(name = "path")
 private  String path;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysFileUploadInfo() {
    super();
}
public void setName(String name){
    this.name = name;
}


public Integer getSize(){
    return size;
}


public String getName(){
    return name;
}


public void setSize(Integer size){
    this.size = size;
}


public String getType(){
    return type;
}


public String getPath(){
    return path;
}


public void setPath(String path){
    this.path = path;
}


public void setType(String type){
    this.type = type;
}


}
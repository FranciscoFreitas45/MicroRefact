package org.danyuan.application.resume.modal.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_modal_info")
@NamedQuery(name = "SysModalInfo.findAll", query = "SELECT s FROM SysModalInfo s")
public class SysModalInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "from_user")
 private  String fromUser;

@Column(name = "major")
 private  String major;

@Column(name = "file_path")
 private  String filePath;

@Column(name = "file_paht")
 private  String filePaht;

@Column(name = "modal_file_path")
 private  String modalFilePath;

@Column(name = "name")
 private  String name;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysModalInfo() {
    super();
}/**
 *  构造方法：
 *  描    述： 构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysModalInfo(String uuid) {
    super(uuid);
}
public void setName(String name){
    this.name = name;
}


public String getFromUser(){
    return fromUser;
}


public String getModalFilePath(){
    return modalFilePath;
}


public void setFilePaht(String filePaht){
    this.filePaht = filePaht;
}


public String getName(){
    return name;
}


public void setFromUser(String fromUser){
    this.fromUser = fromUser;
}


public String getFilePath(){
    return filePath;
}


public String getMajor(){
    return major;
}


public void setFilePath(String filePath){
    this.filePath = filePath;
}


public void setModalFilePath(String modalFilePath){
    this.modalFilePath = modalFilePath;
}


public void setMajor(String major){
    this.major = major;
}


public String getFilePaht(){
    return filePaht;
}


}
package org.danyuan.application.softm.sysmenu.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_system_info")
@NamedQuery(name = "SysSystemInfo.findAll", query = "SELECT s FROM SysSystemInfo s")
public class SysSystemInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "system_name")
 private  String systemName;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysSystemInfo() {
    super();
}
public String getSystemName(){
    return systemName;
}


public void setSystemName(String systemName){
    this.systemName = systemName;
}


}
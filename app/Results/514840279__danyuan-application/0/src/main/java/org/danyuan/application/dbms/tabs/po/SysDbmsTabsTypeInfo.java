package org.danyuan.application.dbms.tabs.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_dbms_tabs_type_info")
@NamedQuery(name = "SysDbmsTabsTypeInfo.findAll", query = "SELECT s FROM SysDbmsTabsTypeInfo s")
public class SysDbmsTabsTypeInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "type_order", precision = 10)
 private  Integer typeOrder;

@Column(name = "type_class")
 private  String typeClass;

@Column(name = "type_icon")
 private  String typeIcon;

@Column(name = "type_name")
 private  String typeName;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysDbmsTabsTypeInfo() {
    super();
}
public Integer getTypeOrder(){
    return typeOrder;
}


public void setTypeOrder(Integer typeOrder){
    this.typeOrder = typeOrder;
}


public void setTypeIcon(String typeIcon){
    this.typeIcon = typeIcon;
}


public void setTypeName(String typeName){
    this.typeName = typeName;
}


public void setTypeClass(String typeClass){
    this.typeClass = typeClass;
}


public String getTypeClass(){
    return typeClass;
}


public String getTypeIcon(){
    return typeIcon;
}


public String getTypeName(){
    return typeName;
}


}
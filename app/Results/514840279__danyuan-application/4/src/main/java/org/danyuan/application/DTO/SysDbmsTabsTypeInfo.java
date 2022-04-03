package org.danyuan.application.DTO;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
public class SysDbmsTabsTypeInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

 private  Integer typeOrder;

 private  String typeClass;

 private  String typeIcon;

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


public void setTypeIcon(String typeIcon){
    this.typeIcon = typeIcon;
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
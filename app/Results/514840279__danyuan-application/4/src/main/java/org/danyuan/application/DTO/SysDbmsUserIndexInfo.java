package org.danyuan.application.DTO;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
public class SysDbmsUserIndexInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

 private  Integer userOrder;

 private  String userPlaceholder;

 private  Integer multeity;

 private  String userIndex;

 private  String userDesc;

 private  Integer chart;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysDbmsUserIndexInfo() {
    super();
}
public String getUserIndex(){
    return userIndex;
}


public void setMulteity(Integer multeity){
    this.multeity = multeity;
}


public String getUserDesc(){
    return userDesc;
}


public void setUserPlaceholder(String userPlaceholder){
    this.userPlaceholder = userPlaceholder;
}


public String getUserPlaceholder(){
    return userPlaceholder;
}


public Integer getChart(){
    return chart;
}


public Integer getUserOrder(){
    return userOrder;
}


public Integer getMulteity(){
    return multeity;
}


}
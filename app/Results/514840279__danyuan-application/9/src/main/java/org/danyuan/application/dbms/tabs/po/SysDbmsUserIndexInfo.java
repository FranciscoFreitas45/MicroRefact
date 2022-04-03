package org.danyuan.application.dbms.tabs.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_dbms_user_index_info")
@NamedQuery(name = "SysDbmsUserIndexInfo.findAll", query = "SELECT s FROM SysDbmsUserIndexInfo s")
public class SysDbmsUserIndexInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "user_order", precision = 10)
 private  Integer userOrder;

@Column(name = "user_placeholder")
 private  String userPlaceholder;

@Column(name = "multeity", precision = 10)
 private  Integer multeity;

@Column(name = "user_index")
 private  String userIndex;

@Column(name = "user_desc")
 private  String userDesc;

@Column(name = "chart", precision = 10)
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


public void setUserIndex(String userIndex){
    this.userIndex = userIndex;
}


public void setMulteity(Integer multeity){
    this.multeity = multeity;
}


public String getUserDesc(){
    return userDesc;
}


public void setChart(Integer chart){
    this.chart = chart;
}


public void setUserPlaceholder(String userPlaceholder){
    this.userPlaceholder = userPlaceholder;
}


public void setUserDesc(String userDesc){
    this.userDesc = userDesc;
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


public void setUserOrder(Integer userOrder){
    this.userOrder = userOrder;
}


public Integer getMulteity(){
    return multeity;
}


}
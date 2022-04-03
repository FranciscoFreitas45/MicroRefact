package org.danyuan.application.dbms.echarts.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_dbms_chart_dimension_group")
@NamedQuery(name = "SysDbmsChartDimensionGroup.findAll", query = "SELECT s FROM SysDbmsChartDimensionGroup s")
public class SysDbmsChartDimensionGroup extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "theme")
 private  String theme;

@Column(name = "group_order", precision = 10)
 private  Integer groupOrder;

@Column(name = "title")
 private  String title;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysDbmsChartDimensionGroup() {
    super();
}
public String getTheme(){
    return theme;
}


public void setGroupOrder(Integer groupOrder){
    this.groupOrder = groupOrder;
}


public String getTitle(){
    return title;
}


public void setTheme(String theme){
    this.theme = theme;
}


public void setTitle(String title){
    this.title = title;
}


public Integer getGroupOrder(){
    return groupOrder;
}


}
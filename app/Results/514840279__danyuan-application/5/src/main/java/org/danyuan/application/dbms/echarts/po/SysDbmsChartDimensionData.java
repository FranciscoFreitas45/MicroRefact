package org.danyuan.application.dbms.echarts.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_dbms_chart_dimension_data")
@NamedQuery(name = "SysDbmsChartDimensionData.findAll", query = "SELECT s FROM SysDbmsChartDimensionData s")
public class SysDbmsChartDimensionData extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "cols_desc")
 private  String colsDesc;

@Column(name = "symbol")
 private  String symbol;

@Column(name = "cols_uuid")
 private  String colsUuid;

@Column(name = "dime_uuid")
 private  String dimeUuid;

@Column(name = "conditions")
 private  String conditions;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysDbmsChartDimensionData() {
    super();
}
public void setColsUuid(String colsUuid){
    this.colsUuid = colsUuid;
}


public void setConditions(String conditions){
    this.conditions = conditions;
}


public void setDimeUuid(String dimeUuid){
    this.dimeUuid = dimeUuid;
}


public String getColsDesc(){
    return colsDesc;
}


public String getConditions(){
    return conditions;
}


public String getDimeUuid(){
    return dimeUuid;
}


public void setColsDesc(String colsDesc){
    this.colsDesc = colsDesc;
}


public String getSymbol(){
    return symbol;
}


public String getColsUuid(){
    return colsUuid;
}


public void setSymbol(String symbol){
    this.symbol = symbol;
}


}
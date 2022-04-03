package org.danyuan.application.dbms.echarts.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_dbms_chart_dimension")
@NamedQuery(name = "SysDbmsChartDimension.findAll", query = "SELECT s FROM SysDbmsChartDimension s")
public class SysDbmsChartDimension extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "count_type")
 private  String countType;

@Column(name = "height")
 private  String height;

@Column(name = "dime_order", precision = 10)
 private  Integer dimeOrder;

@Column(name = "start_num", precision = 10)
 private  Integer startNum;

@Column(name = "theme")
 private  String theme;

@Column(name = "lable_uuid4")
 private  String lableUuid4;

@Column(name = "group_uuid")
 private  String groupUuid;

@Column(name = "db_uuid")
 private  String dbUuid;

@Column(name = "table_uuid")
 private  String tableUuid;

@Column(name = "heigth")
 private  String heigth;

@Column(name = "end_num", precision = 10)
 private  Integer endNum;

@Column(name = "lable_uuid")
 private  String lableUuid;

@Column(name = "width")
 private  String width;

@Column(name = "lable_uuid2")
 private  String lableUuid2;

@Column(name = "chart_type")
 private  String chartType;

@Column(name = "title")
 private  String title;

@Column(name = "lable_uuid3")
 private  String lableUuid3;

@Column(name = "table_type_uuid")
 private  String tableTypeUuid;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysDbmsChartDimension() {
    super();
}
public void setGroupUuid(String groupUuid){
    this.groupUuid = groupUuid;
}


public String getDbUuid(){
    return dbUuid;
}


public Integer getDimeOrder(){
    return dimeOrder;
}


public void setLableUuid(String lableUuid){
    this.lableUuid = lableUuid;
}


public void setStartNum(Integer startNum){
    this.startNum = startNum;
}


public String getHeigth(){
    return heigth;
}


public String getWidth(){
    return width;
}


public String getCountType(){
    return countType;
}


public Integer getEndNum(){
    return endNum;
}


public void setEndNum(Integer endNum){
    this.endNum = endNum;
}


public String getHeight(){
    return height;
}


public void setLableUuid2(String lableUuid2){
    this.lableUuid2 = lableUuid2;
}


public String getTitle(){
    return title;
}


public void setLableUuid4(String lableUuid4){
    this.lableUuid4 = lableUuid4;
}


public String getTableUuid(){
    return tableUuid;
}


public String getLableUuid3(){
    return lableUuid3;
}


public String getLableUuid4(){
    return lableUuid4;
}


public void setHeigth(String heigth){
    this.heigth = heigth;
}


public void setLableUuid3(String lableUuid3){
    this.lableUuid3 = lableUuid3;
}


public String getLableUuid2(){
    return lableUuid2;
}


public void setTableUuid(String tableUuid){
    this.tableUuid = tableUuid;
}


public String getTheme(){
    return theme;
}


public void setDbUuid(String dbUuid){
    this.dbUuid = dbUuid;
}


public void setDimeOrder(Integer dimeOrder){
    this.dimeOrder = dimeOrder;
}


public void setTitle(String title){
    this.title = title;
}


public Integer getStartNum(){
    return startNum;
}


public String getChartType(){
    return chartType;
}


public String getTableTypeUuid(){
    return tableTypeUuid;
}


public void setHeight(String height){
    this.height = height;
}


public String getGroupUuid(){
    return groupUuid;
}


public void setWidth(String width){
    this.width = width;
}


public String getLableUuid(){
    return lableUuid;
}


public void setCountType(String countType){
    this.countType = countType;
}


public void setTableTypeUuid(String tableTypeUuid){
    this.tableTypeUuid = tableTypeUuid;
}


public void setChartType(String chartType){
    this.chartType = chartType;
}


public void setTheme(String theme){
    this.theme = theme;
}


}
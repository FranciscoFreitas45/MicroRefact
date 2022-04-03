package org.danyuan.application.crawler.config.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_crawler_result_ruler_info")
@NamedQuery(name = "SysCrawlerResultRulerInfo.findAll", query = "SELECT s FROM SysCrawlerResultRulerInfo s")
public class SysCrawlerResultRulerInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "cols_name")
 private  String colsName;

@Column(name = "ruler_colum_name")
 private  String rulerColumName;

@Column(name = "table_uuid")
 private  String tableUuid;

@Column(name = "ruler_colum_uuid")
 private  String rulerColumUuid;

@Column(name = "cols_uuid")
 private  String colsUuid;

@Column(name = "ruler_uuid")
 private  String rulerUuid;

/**
 *  构造方法：
 *  描    述： 默认构造函数
 *  参    数：
 *  作    者 ： test
 *  @throws
 */
public SysCrawlerResultRulerInfo() {
    super();
}
public void setRulerColumName(String rulerColumName){
    this.rulerColumName = rulerColumName;
}


public String getRulerUuid(){
    return rulerUuid;
}


public void setRulerUuid(String rulerUuid){
    this.rulerUuid = rulerUuid;
}


public String getColsName(){
    return colsName;
}


public void setColsUuid(String colsUuid){
    this.colsUuid = colsUuid;
}


public String getTableUuid(){
    return tableUuid;
}


public String getRulerColumName(){
    return rulerColumName;
}


public String getRulerColumUuid(){
    return rulerColumUuid;
}


public void setTableUuid(String tableUuid){
    this.tableUuid = tableUuid;
}


public String getColsUuid(){
    return colsUuid;
}


public void setColsName(String colsName){
    this.colsName = colsName;
}


public void setRulerColumUuid(String rulerColumUuid){
    this.rulerColumUuid = rulerColumUuid;
}


}
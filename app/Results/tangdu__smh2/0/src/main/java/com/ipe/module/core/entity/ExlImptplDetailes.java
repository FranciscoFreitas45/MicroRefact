package com.ipe.module.core.entity;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipe.common.entity.IDEntity;
import javax.persistence;
@Entity
@Table(name = "t_exl_imptpl_detailes", schema = "", catalog = "db_work")
@JsonIgnoreProperties(value = { "exlImptpl" })
public class ExlImptplDetailes extends IDEntity{

 private  Integer exlCol;

 private  String tableCol;

 private  String colType;

 private  String colDesc;

 private  String defValue;

 private  ExlImptpl exlImptpl;


@Basic
@Column(name = "exl_col")
public Integer getExlCol(){
    return exlCol;
}


@Basic
@Column(name = "table_col", nullable = true, insertable = true, updatable = true, length = 50, precision = 0)
public String getTableCol(){
    return tableCol;
}


public void setColType(String colType){
    this.colType = colType;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "tpl_id")
public ExlImptpl getExlImptpl(){
    return exlImptpl;
}


public void setTableCol(String tableCol){
    this.tableCol = tableCol;
}


@Basic
@Column(name = "col_desc", nullable = true, insertable = true, updatable = true, length = 50, precision = 0)
public String getColDesc(){
    return colDesc;
}


public void setExlCol(Integer exlCol){
    this.exlCol = exlCol;
}


public void setDefValue(String defValue){
    this.defValue = defValue;
}


public void setColDesc(String colDesc){
    this.colDesc = colDesc;
}


@Basic
@Column(name = "col_type", nullable = true, insertable = true, updatable = true, length = 50, precision = 0)
public String getColType(){
    return colType;
}


public void setExlImptpl(ExlImptpl exlImptpl){
    this.exlImptpl = exlImptpl;
}


@Basic
@Column(name = "def_value")
public String getDefValue(){
    return defValue;
}


}
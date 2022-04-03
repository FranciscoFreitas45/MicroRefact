package org.jeecgframework.web.graphreport.entity.core;
 import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "jform_graphreport_item", schema = "")
@SuppressWarnings("serial")
public class JformGraphreportItemEntity {

 private  String id;

@Excel(name = "字段名")
 private  String fieldName;

@Excel(name = "字段文本")
 private  String fieldTxt;

@Excel(name = "排序")
 private  Integer orderNum;

@Excel(name = "字段类型")
 private  String fieldType;

@Excel(name = "是否显示")
 private  String isShow;

@Excel(name = "是否查询")
 private  String searchFlag;

@Excel(name = "查询模式")
 private  String searchMode;

@Excel(name = "字典Code")
 private  String dictCode;

@Excel(name = "显示图表")
 private  String isGraph;

@Excel(name = "图表类型")
 private  String graphType;

@Excel(name = "图表名称")
 private  String graphName;

@Excel(name = "标签名称")
 private  String tabName;

@Excel(name = "字段href")
 private  String fieldHref;

@Excel(name = "取值表达式")
 private  String replaceVa;

 private  String cgreportHeadId;


@Column(name = "REPLACE_VA", nullable = true, length = 36)
public String getReplaceVa(){
    return this.replaceVa;
}


@Column(name = "IS_SHOW", nullable = true, length = 5)
public String getIsShow(){
    return this.isShow;
}


public void setFieldName(String fieldName){
    this.fieldName = fieldName;
}


@Column(name = "SEARCH_MODE", nullable = true, length = 10)
public String getSearchMode(){
    return this.searchMode;
}


public void setIsGraph(String isGraph){
    this.isGraph = isGraph;
}


@Column(name = "FIELD_TYPE", nullable = false, length = 10)
public String getFieldType(){
    return this.fieldType;
}


@Column(name = "DICT_CODE", nullable = true, length = 500)
public String getDictCode(){
    return this.dictCode;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 36)
public String getId(){
    return this.id;
}


@Column(name = "GRAPH_TYPE", nullable = true, length = 50)
public String getGraphType(){
    return this.graphType;
}


@Column(name = "SEARCH_FLAG", nullable = true, length = 2)
public String getSearchFlag(){
    return this.searchFlag;
}


@Column(name = "IS_GRAPH", nullable = true, length = 5)
public String getIsGraph(){
    return this.isGraph;
}


public void setId(String id){
    this.id = id;
}


@Column(name = "FIELD_HREF", nullable = true, length = 120)
public String getFieldHref(){
    return this.fieldHref;
}


public void setTabName(String tabName){
    this.tabName = tabName;
}


public void setReplaceVa(String replaceVa){
    this.replaceVa = replaceVa;
}


@Column(name = "CGREPORT_HEAD_ID", nullable = true, length = 36)
public String getCgreportHeadId(){
    return this.cgreportHeadId;
}


public void setSearchMode(String searchMode){
    this.searchMode = searchMode;
}


@Column(name = "ORDER_NUM", nullable = true, length = 10)
public Integer getOrderNum(){
    return this.orderNum;
}


public void setFieldHref(String fieldHref){
    this.fieldHref = fieldHref;
}


public void setFieldTxt(String fieldTxt){
    this.fieldTxt = fieldTxt;
}


public void setGraphType(String graphType){
    this.graphType = graphType;
}


public void setDictCode(String dictCode){
    this.dictCode = dictCode;
}


public void setSearchFlag(String searchFlag){
    this.searchFlag = searchFlag;
}


public void setCgreportHeadId(String cgreportHeadId){
    this.cgreportHeadId = cgreportHeadId;
}


@Column(name = "FIELD_TXT", nullable = true, length = 1000)
public String getFieldTxt(){
    return this.fieldTxt;
}


@Column(name = "GRAPH_NAME", nullable = true, length = 100)
public String getGraphName(){
    return this.graphName;
}


public void setFieldType(String fieldType){
    this.fieldType = fieldType;
}


@Column(name = "TAB_NAME", nullable = true, length = 50)
public String getTabName(){
    return this.tabName;
}


@Column(name = "FIELD_NAME", nullable = true, length = 36)
public String getFieldName(){
    return this.fieldName;
}


public void setIsShow(String isShow){
    this.isShow = isShow;
}


public void setOrderNum(Integer orderNum){
    this.orderNum = orderNum;
}


public void setGraphName(String graphName){
    this.graphName = graphName;
}


}
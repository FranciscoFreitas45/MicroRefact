package org.jeecgframework.web.graphreport.entity.core;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
@Entity
@Table(name = "jform_graphreport_head", schema = "")
@SuppressWarnings("serial")
public class JformGraphreportHeadEntity {

 private  String id;

@Excel(name = "名称")
 private  String name;

@Excel(name = "编码")
 private  String code;

@Excel(name = "查询数据SQL")
 private  String cgrSql;

@Excel(name = "描述")
 private  String content;

@Excel(name = "y轴文字")
 private  String ytext;

@Excel(name = "x轴数据")
 private  String categories;

@Excel(name = "是否显示明细")
 private  String isShowList;

@Excel(name = "扩展JS")
 private  String xpageJs;

 private  java.util.Date createDate;

 private  java.lang.String createBy;

 private  java.lang.String createName;

 private  java.util.Date updateDate;

 private  java.lang.String updateBy;

 private  java.lang.String updateName;


public void setName(String name){
    this.name = name;
}


public void setContent(String content){
    this.content = content;
}


@Column(name = "name", nullable = false, length = 100)
public String getName(){
    return this.name;
}


@Column(name = "create_name", nullable = true, length = 32)
public java.lang.String getCreateName(){
    return this.createName;
}


@Column(name = "content", nullable = false, length = 1000)
public String getContent(){
    return this.content;
}


public void setUpdateName(java.lang.String updateName){
    this.updateName = updateName;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "id", nullable = false, length = 36)
public String getId(){
    return this.id;
}


@Column(name = "ytext", nullable = false, length = 100)
public String getYtext(){
    return this.ytext;
}


@Column(name = "create_date", nullable = true)
public java.util.Date getCreateDate(){
    return this.createDate;
}


@Column(name = "categories", nullable = false, length = 1000)
public String getCategories(){
    return this.categories;
}


public void setCreateName(java.lang.String createName){
    this.createName = createName;
}


@Column(name = "update_date", nullable = true)
public java.util.Date getUpdateDate(){
    return this.updateDate;
}


@Column(name = "update_by", nullable = true, length = 32)
public java.lang.String getUpdateBy(){
    return this.updateBy;
}


public void setId(String id){
    this.id = id;
}


@Column(name = "code", nullable = false, length = 36)
public String getCode(){
    return this.code;
}


public void setIsShowList(String isShowList){
    this.isShowList = isShowList;
}


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


public void setUpdateBy(java.lang.String updateBy){
    this.updateBy = updateBy;
}


@Column(name = "x_page_js", nullable = true, length = 1000)
public String getXpageJs(){
    return xpageJs;
}


public void setCreateBy(java.lang.String createBy){
    this.createBy = createBy;
}


public void setCode(String code){
    this.code = code;
}


public void setYtext(String ytext){
    this.ytext = ytext;
}


@Column(name = "update_name", nullable = true, length = 32)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "cgr_sql", nullable = false, length = 2000)
public String getCgrSql(){
    return this.cgrSql;
}


@Column(name = "create_by", nullable = true, length = 32)
public java.lang.String getCreateBy(){
    return this.createBy;
}


public void setCategories(String categories){
    this.categories = categories;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


@Column(name = "is_show_list", nullable = true, length = 5)
public String getIsShowList(){
    return this.isShowList;
}


public void setCgrSql(String cgrSql){
    this.cgrSql = cgrSql;
}


public void setXpageJs(String xpageJs){
    this.xpageJs = xpageJs;
}


}
package org.jeecgframework.web.cgreport.entity.core;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "jform_cgreport_item", schema = "")
@SuppressWarnings("serial")
public class CgreportConfigItemEntity {

 private  java.lang.String id;

 private  java.lang.String fieldName;

 private  java.lang.Integer orderNum;

 private  java.lang.String fieldTxt;

 private  java.lang.String fieldType;

 private  String fieldHref;

 private  String isShow;

 private  java.lang.String sMode;

 private  java.lang.String replaceVa;

 private  java.lang.String dictCode;

 private  java.lang.String sFlag;

 private  java.lang.String cgrheadId;


@Column(name = "REPLACE_VA", nullable = true, length = 36)
public java.lang.String getReplaceVa(){
    return this.replaceVa;
}


public void setReplaceVa(java.lang.String replaceVa){
    this.replaceVa = replaceVa;
}


@Column(name = "IS_SHOW", nullable = true, length = 5)
public String getIsShow(){
    return isShow;
}


public void setFieldName(java.lang.String fieldName){
    this.fieldName = fieldName;
}


@Column(name = "ORDER_NUM", nullable = true, length = 10)
public java.lang.Integer getOrderNum(){
    return this.orderNum;
}


public void setFieldHref(String fieldHref){
    this.fieldHref = fieldHref;
}


@Column(name = "FIELD_TYPE", nullable = true, length = 10)
public java.lang.String getFieldType(){
    return this.fieldType;
}


@Column(name = "DICT_CODE", nullable = true, length = 36)
public java.lang.String getDictCode(){
    return this.dictCode;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 36)
public java.lang.String getId(){
    return this.id;
}


public void setFieldTxt(java.lang.String fieldTxt){
    this.fieldTxt = fieldTxt;
}


@Column(name = "CGRHEAD_ID", nullable = true, length = 36)
public java.lang.String getCgrheadId(){
    return this.cgrheadId;
}


public void setCgrheadId(java.lang.String cgrheadId){
    this.cgrheadId = cgrheadId;
}


public void setDictCode(java.lang.String dictCode){
    this.dictCode = dictCode;
}


public void setSMode(java.lang.String sMode){
    this.sMode = sMode;
}


@Column(name = "FIELD_TXT", nullable = true, length = 1000)
public java.lang.String getFieldTxt(){
    return this.fieldTxt;
}


@Column(name = "S_MODE", nullable = true, length = 10)
public java.lang.String getSMode(){
    return this.sMode;
}


public void setFieldType(java.lang.String fieldType){
    this.fieldType = fieldType;
}


public void setId(java.lang.String id){
    this.id = id;
}


@Column(name = "S_FLAG", nullable = true, length = 2)
public java.lang.String getSFlag(){
    return this.sFlag;
}


@Column(name = "FIELD_NAME", nullable = true, length = 36)
public java.lang.String getFieldName(){
    return this.fieldName;
}


public void setIsShow(String isShow){
    this.isShow = isShow;
}


public void setSFlag(java.lang.String sFlag){
    this.sFlag = sFlag;
}


public void setOrderNum(java.lang.Integer orderNum){
    this.orderNum = orderNum;
}


@Column(name = "FIELD_HREF", nullable = true, length = 120)
public String getFieldHref(){
    return fieldHref;
}


}
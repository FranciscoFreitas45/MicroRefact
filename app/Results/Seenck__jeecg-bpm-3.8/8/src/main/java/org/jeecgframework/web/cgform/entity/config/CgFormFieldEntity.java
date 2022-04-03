package org.jeecgframework.web.cgform.entity.config;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "cgform_field", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@JsonAutoDetect
@SuppressWarnings("serial")
public class CgFormFieldEntity {

 private  java.lang.String id;

 private  java.lang.String fieldName;

 private  CgFormHeadEntity table;

 private  java.lang.Integer length;

 private  java.lang.Integer pointLength;

 private  java.lang.String type;

 private  java.lang.String isNull;

 private  java.lang.Integer orderNum;

 private  java.lang.String isKey;

 private  java.lang.String isShow;

 private  java.lang.String isShowList;

 private  java.lang.String showType;

 private  java.lang.String isQuery;

 private  java.lang.Integer fieldLength;

 private  java.lang.String fieldHref;

 private  java.lang.String fieldValidType;

 private  String fieldMustInput;

 private  java.lang.String queryMode;

 private  java.lang.String content;

 private  java.util.Date createDate;

 private  java.lang.String createBy;

 private  java.lang.String createName;

 private  java.util.Date updateDate;

 private  java.lang.String updateBy;

 private  java.lang.String updateName;

 private  java.lang.String dictField;

 private  java.lang.String dictTable;

 private  java.lang.String dictText;

 private  java.lang.String mainTable;

 private  java.lang.String mainField;

 private  java.lang.String oldFieldName;

 private  java.lang.String fieldDefault;

 private  java.lang.String extendJson;

 private  String fillRuleCode;


@Column(name = "is_show", nullable = true, length = 5)
public java.lang.String getIsShow(){
    return this.isShow;
}


public void setFieldDefault(java.lang.String fieldDefault){
    this.fieldDefault = fieldDefault;
}


@Column(name = "is_key", nullable = true, length = 2)
public java.lang.String getIsKey(){
    return isKey;
}


public void setOldFieldName(java.lang.String oldFieldName){
    this.oldFieldName = oldFieldName;
}


@Column(name = "main_table", nullable = true, length = 100)
public java.lang.String getMainTable(){
    return mainTable;
}


public void setExtendJson(java.lang.String extendJson){
    this.extendJson = extendJson;
}


@Column(name = "fill_rule_code")
public String getFillRuleCode(){
    return fillRuleCode;
}


@Column(name = "create_date", nullable = true)
public java.util.Date getCreateDate(){
    return this.createDate;
}


@Column(name = "update_date", nullable = true)
public java.util.Date getUpdateDate(){
    return this.updateDate;
}


public void setId(java.lang.String id){
    this.id = id;
}


@Column(name = "main_field", nullable = true, length = 100)
public java.lang.String getMainField(){
    return mainField;
}


public void setMainField(java.lang.String mainField){
    this.mainField = mainField;
}


@Column(name = "extend_json", nullable = true, length = 500)
public java.lang.String getExtendJson(){
    return extendJson;
}


public void setIsNull(java.lang.String isNull){
    this.isNull = isNull;
}


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


@Column(name = "dict_field", nullable = true, length = 100)
public java.lang.String getDictField(){
    return dictField;
}


@Column(name = "order_num", nullable = true, length = 4)
public java.lang.Integer getOrderNum(){
    return orderNum;
}


public void setFieldHref(java.lang.String field_href){
    this.fieldHref = field_href;
}


@Column(name = "is_query", nullable = true, length = 5)
public java.lang.String getIsQuery(){
    return this.isQuery;
}


public void setLength(java.lang.Integer length){
    this.length = length;
}


@Column(name = "create_by", nullable = true)
public java.lang.String getCreateBy(){
    return this.createBy;
}


@ManyToOne
@JoinColumn(name = "table_id", nullable = false, referencedColumnName = "id")
@JsonIgnore
@ForeignKey(name = "null")
public CgFormHeadEntity getTable(){
    return this.table;
}


@Column(name = "point_length", nullable = true, precision = 10, scale = 0)
public java.lang.Integer getPointLength(){
    return this.pointLength;
}


@Column(name = "type", nullable = false, length = 32)
public java.lang.String getType(){
    return this.type;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


public void setTable(CgFormHeadEntity table){
    this.table = table;
}


public void setShowType(java.lang.String showType){
    this.showType = showType;
}


@Column(name = "is_show_list", nullable = true, length = 5)
public java.lang.String getIsShowList(){
    return isShowList;
}


public void setIsKey(java.lang.String isKey){
    this.isKey = isKey;
}


@Column(name = "field_name", nullable = false, length = 32)
public java.lang.String getFieldName(){
    return this.fieldName;
}


public void setIsShow(java.lang.String isShow){
    this.isShow = isShow;
}


@Column(name = "field_length", nullable = true, length = 10)
public java.lang.Integer getFieldLength(){
    return fieldLength;
}


@Column(name = "dict_text", nullable = true, length = 100)
public java.lang.String getDictText(){
    return dictText;
}


public void setQueryMode(java.lang.String queryMode){
    this.queryMode = queryMode;
}


public void setContent(java.lang.String content){
    this.content = content;
}


public void setFieldName(java.lang.String fieldName){
    this.fieldName = fieldName;
}


@Column(name = "content", nullable = false, length = 200)
public java.lang.String getContent(){
    return this.content;
}


@Column(name = "create_name", nullable = true, length = 32)
public java.lang.String getCreateName(){
    return this.createName;
}


public void setUpdateName(java.lang.String updateName){
    this.updateName = updateName;
}


@Column(name = "field_valid_type", nullable = true, length = 300)
public java.lang.String getFieldValidType(){
    return fieldValidType;
}


@Column(name = "old_field_name", nullable = true, length = 32)
public java.lang.String getOldFieldName(){
    return oldFieldName;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "id", nullable = false, length = 32)
public java.lang.String getId(){
    return this.id;
}


public void setFieldValidType(java.lang.String field_valid_type){
    this.fieldValidType = field_valid_type;
}


@Column(name = "dict_table", nullable = true, length = 100)
public java.lang.String getDictTable(){
    return dictTable;
}


public void setFillRuleCode(String fillRuleCode){
    this.fillRuleCode = fillRuleCode;
}


public void setDictTable(java.lang.String dictTable){
    this.dictTable = dictTable;
}


@Column(name = "field_must_input")
public String getFieldMustInput(){
    return fieldMustInput;
}


public void setCreateName(java.lang.String createName){
    this.createName = createName;
}


@Column(name = "update_by", nullable = true, length = 32)
public java.lang.String getUpdateBy(){
    return this.updateBy;
}


@Column(name = "field_href", nullable = true, length = 200)
public java.lang.String getFieldHref(){
    return fieldHref;
}


public void setIsShowList(java.lang.String isShowList){
    this.isShowList = isShowList;
}


@Column(name = "query_mode", nullable = true, length = 10)
public java.lang.String getQueryMode(){
    return this.queryMode;
}


@Column(name = "show_type", nullable = true, length = 10)
public java.lang.String getShowType(){
    return this.showType;
}


public void setUpdateBy(java.lang.String updateBy){
    this.updateBy = updateBy;
}


public void setFieldLength(java.lang.Integer field_length){
    this.fieldLength = field_length;
}


public void setCreateBy(java.lang.String createBy){
    this.createBy = createBy;
}


public void setIsQuery(java.lang.String isQuery){
    this.isQuery = isQuery;
}


public void setFieldMustInput(String fieldMustInput){
    this.fieldMustInput = fieldMustInput;
}


public void setType(java.lang.String type){
    this.type = type;
}


public void setPointLength(java.lang.Integer pointLength){
    this.pointLength = pointLength;
}


@Column(name = "update_name", nullable = true, length = 32)
public java.lang.String getUpdateName(){
    return this.updateName;
}


public void setMainTable(java.lang.String mainTable){
    this.mainTable = mainTable;
}


public void setDictText(java.lang.String dictText){
    this.dictText = dictText;
}


@Column(name = "length", nullable = false, precision = 10, scale = 0)
public java.lang.Integer getLength(){
    return this.length;
}


@Column(name = "is_null", nullable = true, length = 5)
public java.lang.String getIsNull(){
    return this.isNull;
}


public void setDictField(java.lang.String dictField){
    this.dictField = dictField;
}


public void setOrderNum(java.lang.Integer orderNum){
    this.orderNum = orderNum;
}


@Column(name = "field_default", nullable = true, length = 20)
public java.lang.String getFieldDefault(){
    if (fieldDefault != null) {
        fieldDefault = fieldDefault.trim();
    }
    return fieldDefault;
}


}
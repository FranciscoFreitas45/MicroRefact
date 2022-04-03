package DTO;
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


@Column(name = "is_key", nullable = true, length = 2)
public java.lang.String getIsKey(){
    return isKey;
}


@Column(name = "main_table", nullable = true, length = 100)
public java.lang.String getMainTable(){
    return mainTable;
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


@Column(name = "main_field", nullable = true, length = 100)
public java.lang.String getMainField(){
    return mainField;
}


@Column(name = "extend_json", nullable = true, length = 500)
public java.lang.String getExtendJson(){
    return extendJson;
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


@Column(name = "is_query", nullable = true, length = 5)
public java.lang.String getIsQuery(){
    return this.isQuery;
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


public void setTable(CgFormHeadEntity table){
    this.table = table;
}


@Column(name = "is_show_list", nullable = true, length = 5)
public java.lang.String getIsShowList(){
    return isShowList;
}


@Column(name = "field_name", nullable = false, length = 32)
public java.lang.String getFieldName(){
    return this.fieldName;
}


@Column(name = "field_length", nullable = true, length = 10)
public java.lang.Integer getFieldLength(){
    return fieldLength;
}


@Column(name = "dict_text", nullable = true, length = 100)
public java.lang.String getDictText(){
    return dictText;
}


public void setContent(java.lang.String content){
    this.content = content;
}


@Column(name = "content", nullable = false, length = 200)
public java.lang.String getContent(){
    return this.content;
}


@Column(name = "create_name", nullable = true, length = 32)
public java.lang.String getCreateName(){
    return this.createName;
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


@Column(name = "dict_table", nullable = true, length = 100)
public java.lang.String getDictTable(){
    return dictTable;
}


public void setDictTable(java.lang.String dictTable){
    this.dictTable = dictTable;
}


@Column(name = "field_must_input")
public String getFieldMustInput(){
    return fieldMustInput;
}


@Column(name = "update_by", nullable = true, length = 32)
public java.lang.String getUpdateBy(){
    return this.updateBy;
}


@Column(name = "field_href", nullable = true, length = 200)
public java.lang.String getFieldHref(){
    return fieldHref;
}


@Column(name = "query_mode", nullable = true, length = 10)
public java.lang.String getQueryMode(){
    return this.queryMode;
}


@Column(name = "show_type", nullable = true, length = 10)
public java.lang.String getShowType(){
    return this.showType;
}


public void setFieldLength(java.lang.Integer field_length){
    this.fieldLength = field_length;
}


public void setIsQuery(java.lang.String isQuery){
    this.isQuery = isQuery;
}


public void setType(java.lang.String type){
    this.type = type;
}


@Column(name = "update_name", nullable = true, length = 32)
public java.lang.String getUpdateName(){
    return this.updateName;
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
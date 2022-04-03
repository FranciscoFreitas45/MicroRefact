package DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
public class CgFormIndexEntity {

 private  java.lang.String id;

 private  java.lang.String createName;

 private  java.lang.String createBy;

 private  java.util.Date createDate;

 private  java.lang.String updateName;

 private  java.lang.String updateBy;

 private  java.util.Date updateDate;

 private  java.lang.String indexName;

 private  java.lang.String indexField;

 private  java.lang.String indexType;

 private  CgFormHeadEntity table;


public void setIndexField(java.lang.String indexField){
    this.indexField = indexField;
}


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


public void setUpdateBy(java.lang.String updateBy){
    this.updateBy = updateBy;
}


public void setCreateBy(java.lang.String createBy){
    this.createBy = createBy;
}


@Column(name = "CREATE_NAME", nullable = true, length = 50)
public java.lang.String getCreateName(){
    return this.createName;
}


public void setIndexName(java.lang.String indexName){
    this.indexName = indexName;
}


public void setUpdateName(java.lang.String updateName){
    this.updateName = updateName;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 36)
public java.lang.String getId(){
    return this.id;
}


@Column(name = "INDEX_NAME", nullable = true, length = 100)
public java.lang.String getIndexName(){
    return this.indexName;
}


@Column(name = "UPDATE_NAME", nullable = true, length = 50)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "CREATE_BY", nullable = true, length = 50)
public java.lang.String getCreateBy(){
    return this.createBy;
}


@Column(name = "CREATE_DATE", nullable = true, length = 20)
public java.util.Date getCreateDate(){
    return this.createDate;
}


public void setIndexType(java.lang.String indexType){
    this.indexType = indexType;
}


@ManyToOne
@JoinColumn(name = "table_id", nullable = false, referencedColumnName = "id")
@JsonIgnore
@ForeignKey(name = "null")
public CgFormHeadEntity getTable(){
    return this.table;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


public void setCreateName(java.lang.String createName){
    this.createName = createName;
}


public void setTable(CgFormHeadEntity table){
    this.table = table;
}


@Column(name = "UPDATE_DATE", nullable = true, length = 20)
public java.util.Date getUpdateDate(){
    return this.updateDate;
}


public void setId(java.lang.String id){
    this.id = id;
}


@Column(name = "UPDATE_BY", nullable = true, length = 50)
public java.lang.String getUpdateBy(){
    return this.updateBy;
}


@Column(name = "INDEX_FIELD", nullable = true, length = 500)
public java.lang.String getIndexField(){
    return this.indexField;
}


@Column(name = "INDEX_TYPE", nullable = true, length = 32)
public java.lang.String getIndexType(){
    return this.indexType;
}


}
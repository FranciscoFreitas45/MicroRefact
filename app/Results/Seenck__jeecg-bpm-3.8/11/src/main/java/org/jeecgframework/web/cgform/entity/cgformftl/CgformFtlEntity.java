package org.jeecgframework.web.cgform.entity.cgformftl;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "cgform_ftl", schema = "")
@SuppressWarnings("serial")
public class CgformFtlEntity {

 private  java.lang.String id;

 private  java.lang.String cgformId;

 private  java.lang.String cgformName;

 private  java.lang.Integer ftlVersion;

 private  String ftlContent;

 private  java.lang.String ftlStatus;

 private  java.lang.String ftlWordUrl;

 private  java.lang.String createBy;

 private  java.lang.String createName;

 private  java.util.Date createDate;

 private  java.lang.String updateBy;

 private  java.lang.String updateName;

 private  java.util.Date updateDate;

 private  java.lang.String editorType;


@Column(name = "EDITOR_TYPE", nullable = false, length = 32)
public java.lang.String getEditorType(){
    return editorType;
}


@Lob
@Column(name = "FTL_CONTENT", nullable = true)
public String getFtlContent(){
    return this.ftlContent;
}


@Column(name = "CREATE_NAME", nullable = true, length = 32)
public java.lang.String getCreateName(){
    return this.createName;
}


public void setUpdateName(java.lang.String updateName){
    this.updateName = updateName;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 32)
public java.lang.String getId(){
    return this.id;
}


public void setFtlVersion(java.lang.Integer ftlVersion){
    this.ftlVersion = ftlVersion;
}


public void setCgformId(java.lang.String cgformId){
    this.cgformId = cgformId;
}


public void setFtlStatus(java.lang.String ftlStatus){
    this.ftlStatus = ftlStatus;
}


public void setFtlWordUrl(java.lang.String ftlWordUrl){
    this.ftlWordUrl = ftlWordUrl;
}


@Column(name = "CREATE_DATE", nullable = true)
public java.util.Date getCreateDate(){
    return this.createDate;
}


public void setCreateName(java.lang.String createName){
    this.createName = createName;
}


public void setCgformName(java.lang.String cgformName){
    this.cgformName = cgformName;
}


@Column(name = "UPDATE_DATE", nullable = true)
public java.util.Date getUpdateDate(){
    return this.updateDate;
}


public void setId(java.lang.String id){
    this.id = id;
}


@Column(name = "UPDATE_BY", nullable = true, length = 36)
public java.lang.String getUpdateBy(){
    return this.updateBy;
}


@Column(name = "FTL_WORD_URL", nullable = true, length = 200)
public java.lang.String getFtlWordUrl(){
    return this.ftlWordUrl;
}


@Column(name = "CGFORM_NAME", nullable = false, length = 100)
public java.lang.String getCgformName(){
    return this.cgformName;
}


@Column(name = "FTL_VERSION", nullable = false, length = 10)
public java.lang.Integer getFtlVersion(){
    return this.ftlVersion;
}


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


public void setFtlContent(String ftlContent){
    this.ftlContent = ftlContent;
}


public void setUpdateBy(java.lang.String updateBy){
    this.updateBy = updateBy;
}


public void setCreateBy(java.lang.String createBy){
    this.createBy = createBy;
}


public void setEditorType(java.lang.String editorType){
    this.editorType = editorType;
}


@Column(name = "CGFORM_ID", nullable = false, length = 36)
public java.lang.String getCgformId(){
    return this.cgformId;
}


@Column(name = "UPDATE_NAME", nullable = true, length = 32)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "CREATE_BY", nullable = true, length = 36)
public java.lang.String getCreateBy(){
    return this.createBy;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


@Column(name = "FTL_STATUS", nullable = true, length = 50)
public java.lang.String getFtlStatus(){
    return this.ftlStatus;
}


}
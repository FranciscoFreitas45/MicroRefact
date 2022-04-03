package org.jeecgframework.web.system.sms.entity;
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
@Table(name = "t_s_sms", schema = "")
@SuppressWarnings("serial")
public class TSSmsEntity {

 private  java.lang.String id;

 private  java.lang.String createName;

 private  java.lang.String createBy;

 private  java.util.Date createDate;

 private  java.lang.String updateName;

 private  java.lang.String updateBy;

 private  java.util.Date updateDate;

@Excel(name = "消息标题")
 private  java.lang.String esTitle;

@Excel(name = "消息类型")
 private  java.lang.String esType;

@Excel(name = "发送人")
 private  java.lang.String esSender;

@Excel(name = "接收人")
 private  java.lang.String esReceiver;

@Excel(name = "内容")
 private  java.lang.String esContent;

@Excel(name = "发送时间")
 private  java.util.Date esSendtime;

@Excel(name = "发送状态")
 private  java.lang.String esStatus;

@Excel(name = "备注")
 private  java.lang.String remark;

 private  Integer isRead;


public void setEsSendtime(java.util.Date esSendtime){
    this.esSendtime = esSendtime;
}


@Column(name = "CREATE_NAME", nullable = true, length = 50)
public java.lang.String getCreateName(){
    return this.createName;
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


public void setEsStatus(java.lang.String esStatus){
    this.esStatus = esStatus;
}


@Column(name = "ES_TYPE", nullable = true, length = 1)
public java.lang.String getEsType(){
    return this.esType;
}


@Column(name = "CREATE_DATE", nullable = true, length = 20)
public java.util.Date getCreateDate(){
    return this.createDate;
}


public void setRemark(java.lang.String remark){
    this.remark = remark;
}


public void setIsRead(Integer isRead){
    this.isRead = isRead;
}


public void setCreateName(java.lang.String createName){
    this.createName = createName;
}


@Column(name = "remark", nullable = true, length = 1)
public java.lang.String getRemark(){
    return this.remark;
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


@Column(name = "ES_SENDTIME", nullable = true, length = 32)
public java.util.Date getEsSendtime(){
    return this.esSendtime;
}


public void setUpdateDate(java.util.Date updateDate){
    this.updateDate = updateDate;
}


public void setUpdateBy(java.lang.String updateBy){
    this.updateBy = updateBy;
}


@Column(name = "ES_TITLE", nullable = true, length = 32)
public java.lang.String getEsTitle(){
    return this.esTitle;
}


public void setCreateBy(java.lang.String createBy){
    this.createBy = createBy;
}


@Column(name = "ES_STATUS", nullable = true, length = 1)
public java.lang.String getEsStatus(){
    return this.esStatus;
}


public void setEsType(java.lang.String esType){
    this.esType = esType;
}


@Column(name = "is_read", nullable = false)
public Integer getIsRead(){
    return isRead;
}


public void setEsTitle(java.lang.String esTitle){
    this.esTitle = esTitle;
}


@Column(name = "ES_RECEIVER", nullable = true, length = 50)
public java.lang.String getEsReceiver(){
    return this.esReceiver;
}


@Column(name = "UPDATE_NAME", nullable = true, length = 50)
public java.lang.String getUpdateName(){
    return this.updateName;
}


@Column(name = "ES_CONTENT", nullable = true, length = 1000)
public java.lang.String getEsContent(){
    return this.esContent;
}


@Column(name = "CREATE_BY", nullable = true, length = 50)
public java.lang.String getCreateBy(){
    return this.createBy;
}


public void setCreateDate(java.util.Date createDate){
    this.createDate = createDate;
}


@Column(name = "ES_SENDER", nullable = true, length = 50)
public java.lang.String getEsSender(){
    return this.esSender;
}


public void setEsReceiver(java.lang.String esReceiver){
    this.esReceiver = esReceiver;
}


public void setEsContent(java.lang.String esContent){
    this.esContent = esContent;
}


public void setEsSender(java.lang.String esSender){
    this.esSender = esSender;
}


}
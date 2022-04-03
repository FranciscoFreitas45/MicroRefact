package org.jeecgframework.web.cgform.entity.enhance;
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
@Table(name = "cgform_enhance_java", schema = "")
@SuppressWarnings("serial")
public class CgformEnhanceJavaEntity {

 private  java.lang.String id;

 private  java.lang.String buttonCode;

@Excel(name = "类型")
 private  java.lang.String cgJavaType;

@Excel(name = "数值")
 private  java.lang.String cgJavaValue;

@Excel(name = "表单ID")
 private  java.lang.String formId;

@Excel(name = "生效状态")
 private  java.lang.String activeStatus;


public void setCgJavaType(java.lang.String cgJavaType){
    this.cgJavaType = cgJavaType;
}


@Column(name = "FORM_ID", nullable = false, length = 32)
public java.lang.String getFormId(){
    return this.formId;
}


@Column(name = "CG_JAVA_TYPE", nullable = true, length = 32)
public java.lang.String getCgJavaType(){
    return this.cgJavaType;
}


@Column(name = "CG_JAVA_VALUE", nullable = false, length = 200)
public java.lang.String getCgJavaValue(){
    return this.cgJavaValue;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 36)
public java.lang.String getId(){
    return this.id;
}


public void setCgJavaValue(java.lang.String cgJavaValue){
    this.cgJavaValue = cgJavaValue;
}


public void setFormId(java.lang.String formId){
    this.formId = formId;
}


@Column(name = "BUTTON_CODE", nullable = true, length = 50)
public java.lang.String getButtonCode(){
    return buttonCode;
}


@Column(name = "ACTIVE_STATUS", nullable = true, length = 2)
public java.lang.String getActiveStatus(){
    return this.activeStatus;
}


public void setId(java.lang.String id){
    this.id = id;
}


@Override
public String toString(){
    return "CgformEnhanceJavaEntity [id=" + id + ", buttonCode=" + buttonCode + ", cgJavaType=" + cgJavaType + ", cgJavaValue=" + cgJavaValue + ", formId=" + formId + ", activeStatus=" + activeStatus + "]";
}


public void setButtonCode(java.lang.String buttonCode){
    this.buttonCode = buttonCode;
}


public void setActiveStatus(java.lang.String activeStatus){
    this.activeStatus = activeStatus;
}


}
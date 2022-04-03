package DTO;
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
public class CgformEnhanceJavaEntity {

 private  java.lang.String id;

 private  java.lang.String buttonCode;

 private  java.lang.String cgJavaType;

 private  java.lang.String cgJavaValue;

 private  java.lang.String formId;

 private  java.lang.String activeStatus;


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


@Column(name = "BUTTON_CODE", nullable = true, length = 50)
public java.lang.String getButtonCode(){
    return buttonCode;
}


@Column(name = "ACTIVE_STATUS", nullable = true, length = 2)
public java.lang.String getActiveStatus(){
    return this.activeStatus;
}


}
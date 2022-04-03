package DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
public class CgformButtonSqlEntity {

 private  java.lang.String id;

 private  java.lang.String formId;

 private  java.lang.String buttonCode;

 private  java.lang.String cgbSqlName;

 private  byte[] cgbSql;

 private  String cgbSqlStr;

 private  java.lang.String content;


@Column(name = "FORM_ID", nullable = true, length = 32)
public java.lang.String getFormId(){
    return this.formId;
}


@Column(name = "CGB_SQL_NAME", nullable = true, length = 50)
public java.lang.String getCgbSqlName(){
    return this.cgbSqlName;
}


@Column(name = "CGB_SQL", nullable = true)
public byte[] getCgbSql(){
    return this.cgbSql;
}


@Transient
public String getCgbSqlStr(){
    if (cgbSql != null) {
        cgbSqlStr = new String(cgbSql);
    }
    return cgbSqlStr;
}


@Column(name = "CONTENT", nullable = true, length = 1000)
public java.lang.String getContent(){
    return this.content;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 32)
public java.lang.String getId(){
    return this.id;
}


@Column(name = "BUTTON_CODE", nullable = true, length = 50)
public java.lang.String getButtonCode(){
    return this.buttonCode;
}


}
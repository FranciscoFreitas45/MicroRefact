package DTO;
 import java.io;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
public class CgformEnhanceJsEntity {

 private  java.lang.String id;

 private  java.lang.String formId;

 private  java.lang.String cgJsType;

 private  byte[] cgJs;

 private  String cgJsStr;

 private  java.lang.String content;


@Column(name = "FORM_ID", nullable = true, length = 32)
public java.lang.String getFormId(){
    return this.formId;
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


@Column(name = "CG_JS_TYPE", nullable = true, length = 20)
public java.lang.String getCgJsType(){
    return this.cgJsType;
}


@Transient
public String getCgJsStr(){
    if (cgJs != null) {
        try {
            cgJsStr = new String(cgJs, "utf-8");
        } catch (Exception e) {
        }
    }
    return cgJsStr;
}


@Column(name = "CG_JS", nullable = true, length = 65535)
public byte[] getCgJs(){
    return this.cgJs;
}


}
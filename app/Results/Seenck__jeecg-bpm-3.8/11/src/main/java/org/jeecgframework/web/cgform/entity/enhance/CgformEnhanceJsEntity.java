package org.jeecgframework.web.cgform.entity.enhance;
 import java.io;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "cgform_enhance_js", schema = "")
@SuppressWarnings("serial")
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


public void setContent(java.lang.String content){
    this.content = content;
}


public void setCgJsType(java.lang.String cgJsType){
    this.cgJsType = cgJsType;
}


public void setCgJs(byte[] cgJs){
    this.cgJs = cgJs;
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


public void setFormId(java.lang.String formId){
    this.formId = formId;
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


public CgformEnhanceJsEntity deepCopy(){
    // 将该对象序列化成流,因为写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。所以利用这个特性可以实现对象的深拷贝
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(bos);
    oos.writeObject(this);
    // 将流序列化成对象
    ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
    ObjectInputStream ois = new ObjectInputStream(bis);
    return (CgformEnhanceJsEntity) ois.readObject();
}


public void setCgJsStr(String cgJsStr){
    this.cgJsStr = cgJsStr;
    if (cgJsStr != null) {
        try {
            this.cgJs = cgJsStr.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}


public void setId(java.lang.String id){
    this.id = id;
}


@Column(name = "CG_JS", nullable = true, length = 65535)
public byte[] getCgJs(){
    return this.cgJs;
}


}
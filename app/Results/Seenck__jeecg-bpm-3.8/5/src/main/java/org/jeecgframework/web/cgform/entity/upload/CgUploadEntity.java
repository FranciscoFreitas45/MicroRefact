package org.jeecgframework.web.cgform.entity.upload;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.jeecgframework.web.system.pojo.base.TSAttachment;
@Entity
@Table(name = "cgform_uploadfiles", schema = "")
@PrimaryKeyJoinColumn(name = "id")
@SuppressWarnings("serial")
public class CgUploadEntity extends TSAttachment{

 private  String cgformName;

 private  String cgformId;

 private  String cgformField;


@Column(name = "CGFORM_NAME", nullable = false, length = 100)
public String getCgformName(){
    return cgformName;
}


@Column(name = "CGFORM_FIELD", nullable = false, length = 100)
public String getCgformField(){
    return cgformField;
}


public void setCgformField(String cgformField){
    this.cgformField = cgformField;
}


public void setCgformName(String cgformName){
    this.cgformName = cgformName;
}


@Column(name = "CGFORM_ID", nullable = false, length = 36)
public String getCgformId(){
    return cgformId;
}


public void setCgformId(String cgformId){
    this.cgformId = cgformId;
}


}
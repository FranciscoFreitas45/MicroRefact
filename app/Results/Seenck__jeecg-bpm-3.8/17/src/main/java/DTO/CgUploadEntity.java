package DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.jeecgframework.web.system.pojo.base.TSAttachment;
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


@Column(name = "CGFORM_ID", nullable = false, length = 36)
public String getCgformId(){
    return cgformId;
}


}
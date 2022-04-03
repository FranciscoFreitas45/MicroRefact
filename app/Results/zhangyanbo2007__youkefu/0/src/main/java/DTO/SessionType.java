package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
public class SessionType {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  String ctype;

 private  String parentid;

 private  String dicid;

 private  String description;

 private  Date createtime;

 private  String creater;

 private  Date updatetime;

 private  String orgi;

 private  String organ;


public String getCtype(){
    return ctype;
}


public String getName(){
    return name;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setDicid(String dicid){
    this.dicid = dicid;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getDescription(){
    return description;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public String getDicid(){
    return dicid;
}


public Date getCreatetime(){
    return createtime;
}


public String getOrgan(){
    return organ;
}


public String getOrgi(){
    return orgi;
}


public void setParentid(String parentid){
    this.parentid = parentid;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public String getCode(){
    return code;
}


public String getParentid(){
    return parentid;
}


}
package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
public class AreaType {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String code;

 private  Date createtime;

 private  String creater;

 private  String username;

 private  String area;

 private  Date updatetime;

 private  String parentid;

 private  String orgi;


public String getName(){
    return name;
}


public Date getUpdatetime(){
    return updatetime;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getUsername(){
    return username;
}


public Date getCreatetime(){
    return createtime;
}


public String getOrgi(){
    return orgi;
}


public String getCreater(){
    return creater;
}


public String getCode(){
    return code;
}


public String getArea(){
    return area;
}


public String getParentid(){
    return parentid;
}


}
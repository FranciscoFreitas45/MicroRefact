package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
public class OrgiSkillRel {

 private  long serialVersionUID;

 private  String id;

 private  String skillid;

 private  Date createtime;

 private  String creater;

 private  Date updatetime;

 private  String orgi;


public Date getCreatetime(){
    return createtime;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getOrgi(){
    return orgi;
}


public void setId(String id){
    this.id = id;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getCreater(){
    return creater;
}


public String getSkillid(){
    return skillid;
}


}
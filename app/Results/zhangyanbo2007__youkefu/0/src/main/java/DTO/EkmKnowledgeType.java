package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
public class EkmKnowledgeType {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  int total;

 private  int viewnum;

 private  int collectnum;

 private  boolean audit;

 private  String auditer;

 private  String organ;

 private  String parentid;

 private  String knowbaseid;

 private  Date createtime;

 private  String creater;

 private  String orgi;

 private  boolean datastatus;

 private  boolean navshow;

 private  boolean deskshow;

 private  String icon;


public String getName(){
    return name;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public Date getCreatetime(){
    return createtime;
}


public String getCreater(){
    return creater;
}


public String getParentid(){
    return parentid;
}


public int getCollectnum(){
    return collectnum;
}


public String getKnowbaseid(){
    return knowbaseid;
}


public int getViewnum(){
    return viewnum;
}


public String getIcon(){
    return icon;
}


public String getAuditer(){
    return auditer;
}


public String getOrgan(){
    return organ;
}


public String getOrgi(){
    return orgi;
}


public int getTotal(){
    return total;
}


}
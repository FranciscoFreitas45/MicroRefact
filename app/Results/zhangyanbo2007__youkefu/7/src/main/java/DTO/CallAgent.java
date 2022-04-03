package DTO;
 import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
public class CallAgent {

 private  long serialVersionUID;

 private  String id;

 private  String orgi;

 private  String organ;

 private  String creater;

 private  String name;

 private  Date createtime;

 private  Date updatetime;

 private  String parentid;

 private  String actid;

 private  String batid;

 private  String filtertype;

 private  String tableid;

 private  String distype;

 private  String distarget;

 private  int disnum;

 private  String datastatus;

 private  String status;

 private  String description;

 private  AtomicInteger disnames;


public String getName(){
    return name;
}


public String getDistarget(){
    return distarget;
}


@Transient
public AtomicInteger getDisnames(){
    return disnames;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getBatid(){
    return batid;
}


public String getStatus(){
    return status;
}


public String getDescription(){
    return description;
}


public String getTableid(){
    return tableid;
}


public Date getCreatetime(){
    return createtime;
}


public String getActid(){
    return actid;
}


public String getFiltertype(){
    return filtertype;
}


public int getDisnum(){
    return disnum;
}


public String getCreater(){
    return creater;
}


public String getParentid(){
    return parentid;
}


public String getDatastatus(){
    return datastatus;
}


public String getDistype(){
    return distype;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getOrgan(){
    return organ;
}


public String getOrgi(){
    return orgi;
}


}
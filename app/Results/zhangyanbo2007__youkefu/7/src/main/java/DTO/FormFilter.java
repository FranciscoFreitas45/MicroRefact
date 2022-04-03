package DTO;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
public class FormFilter implements Serializable{

 private  long serialVersionUID;

 private  String id;

 private  String orgi;

 private  String organ;

 private  String creater;

 private  String name;

 private  Date createtime;

 private  Date updatetime;

 private  String parentid;

 private  String batid;

 private  String filtertype;

 private  String tableid;

 private  String datastatus;

 private  String status;

 private  int filternum;

 private  int conditional;

 private  int execnum;

 private  String description;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


public String getName(){
    return name;
}


public int getConditional(){
    return conditional;
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


public String getFiltertype(){
    return filtertype;
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


public Date getUpdatetime(){
    return updatetime;
}


public String getOrgan(){
    return organ;
}


public String getOrgi(){
    return orgi;
}


public int getExecnum(){
    return execnum;
}


public int getFilternum(){
    return filternum;
}


public void setFilternum(int filternum){
    this.filternum = filternum;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setFilternum"))

.queryParam("filternum",filternum)
;
restTemplate.put(builder.toUriString(),null);
}


}
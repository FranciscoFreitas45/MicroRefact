package DTO;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
public class Tenant {

 private  long serialVersionUID;

 private  String id;

 private  String datasourceid;

 private  String tenantname;

 private  String tenantcode;

 private  boolean systemtenant;

 private  boolean inited;

 private  boolean inites;

 private  boolean initdb;

 private  String adminuser;

 private  String remark;

 private  Date lastmenutime;

 private  Date lastbasetime;

 private  String tenantlogo;

 private  String tenantvalid;

 private  Date createtime;

 private  String password;

 private  String genpasstype;

 private  String sign;

 private  String orgid;


public String getTenantcode(){
    return tenantcode;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public Date getLastbasetime(){
    return lastbasetime;
}


public Date getCreatetime(){
    return createtime;
}


public String getRemark(){
    return remark;
}


public String getTenantlogo(){
    return tenantlogo;
}


public String getDatasourceid(){
    return datasourceid;
}


public String getGenpasstype(){
    return genpasstype;
}


public Date getLastmenutime(){
    return lastmenutime;
}


public String getOrgid(){
    return orgid;
}


public String getTenantvalid(){
    return tenantvalid;
}


public String getAdminuser(){
    return adminuser;
}


public String getPassword(){
    return password;
}


public String getSign(){
    return sign;
}


public String getTenantname(){
    return tenantname;
}


}
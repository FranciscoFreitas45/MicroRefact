package org.jeecgframework.web.system.pojo.base;
 import org.jeecgframework.core.common.entity.IdEntity;
import javax.persistence;
@Entity
@Table(name = "t_s_role_org")
public class TSRoleOrg extends IdEntity{

 private  TSDepart tsDepart;

 private  TSRole tsRole;


public void setTsDepart(TSDepart tsDepart){
    this.tsDepart = tsDepart;
}


@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "org_id")
public TSDepart getTsDepart(){
    return tsDepart;
}


public void setTsRole(TSRole tsRole){
    this.tsRole = tsRole;
}


@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "role_id")
public TSRole getTsRole(){
    return tsRole;
}


}
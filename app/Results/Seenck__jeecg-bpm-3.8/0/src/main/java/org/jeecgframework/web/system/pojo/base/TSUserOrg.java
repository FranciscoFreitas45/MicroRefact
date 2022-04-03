package org.jeecgframework.web.system.pojo.base;
 import org.jeecgframework.core.common.entity.IdEntity;
import javax.persistence;
@Entity
@Table(name = "t_s_user_org")
public class TSUserOrg extends IdEntity{

 private  TSUser tsUser;

 private  TSDepart tsDepart;


public void setTsDepart(TSDepart tsDepart){
    this.tsDepart = tsDepart;
}


@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "org_id")
public TSDepart getTsDepart(){
    return tsDepart;
}


@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "user_id")
public TSUser getTsUser(){
    return tsUser;
}


public void setTsUser(TSUser tsDepart){
    this.tsUser = tsDepart;
}


}
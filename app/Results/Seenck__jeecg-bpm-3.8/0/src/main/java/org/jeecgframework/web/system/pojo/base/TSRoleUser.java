package org.jeecgframework.web.system.pojo.base;
 import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.jeecgframework.core.common.entity.IdEntity;
@Entity
@Table(name = "t_s_role_user")
public class TSRoleUser extends IdEntity{

 private  TSUser TSUser;

 private  TSRole TSRole;


public void setTSRole(TSRole TSRole){
    this.TSRole = TSRole;
}


@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "userid")
public TSUser getTSUser(){
    return this.TSUser;
}


public void setTSUser(TSUser TSUser){
    this.TSUser = TSUser;
}


@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "roleid")
public TSRole getTSRole(){
    return this.TSRole;
}


}
package DTO;
 import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.jeecgframework.core.common.entity.IdEntity;
public class TSRoleUser extends IdEntity{

 private  TSUser TSUser;

 private  TSRole TSRole;


@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "userid")
public TSUser getTSUser(){
    return this.TSUser;
}


@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "roleid")
public TSRole getTSRole(){
    return this.TSRole;
}


}
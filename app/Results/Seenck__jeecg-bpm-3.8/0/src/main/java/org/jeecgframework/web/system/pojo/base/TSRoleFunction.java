package org.jeecgframework.web.system.pojo.base;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.jeecgframework.core.common.entity.IdEntity;
@Entity
@Table(name = "t_s_role_function")
public class TSRoleFunction extends IdEntity{

 private  TSFunction TSFunction;

 private  TSRole TSRole;

 private  String operation;

 private  String dataRule;


public void setTSRole(TSRole TSRole){
    this.TSRole = TSRole;
}


@Column(name = "datarule", length = 100)
public String getDataRule(){
    return dataRule;
}


@Column(name = "operation", length = 100)
public String getOperation(){
    return this.operation;
}


public void setOperation(String operation){
    this.operation = operation;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "roleid")
public TSRole getTSRole(){
    return this.TSRole;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "functionid")
public TSFunction getTSFunction(){
    return this.TSFunction;
}


public void setDataRule(String dataRule){
    this.dataRule = dataRule;
}


public void setTSFunction(TSFunction TSFunction){
    this.TSFunction = TSFunction;
}


}
package org.jeecgframework.web.system.pojo.base;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.jeecgframework.core.common.entity.IdEntity;
@Entity
@Table(name = "t_s_operation")
public class TSOperation extends IdEntity{

 private  String operationname;

 private  String operationcode;

 private  String operationicon;

 private  Short status;

 private  TSIcon TSIcon;

 private  TSFunction TSFunction;

 private  String processnodeId;

 private  Short operationType;


public void setProcessnodeId(String processnodeId){
    this.processnodeId = processnodeId;
}


@Column(name = "PROCESSNODE_ID", nullable = true, length = 32)
public String getProcessnodeId(){
    return processnodeId;
}


public void setOperationicon(String operationicon){
    this.operationicon = operationicon;
}


public void setTSIcon(TSIcon tSIcon){
    TSIcon = tSIcon;
}


public void setOperationcode(String operationcode){
    this.operationcode = operationcode;
}


@Column(name = "status")
public Short getStatus(){
    return this.status;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "functionid")
public TSFunction getTSFunction(){
    return TSFunction;
}


public void setOperationname(String operationname){
    this.operationname = operationname;
}


public void setOperationType(Short operationType){
    this.operationType = operationType;
}


@Column(name = "operationicon", length = 100)
public String getOperationicon(){
    return this.operationicon;
}


public void setStatus(Short status){
    this.status = status;
}


@Column(name = "operationtype")
public Short getOperationType(){
    return operationType;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "iconid")
public TSIcon getTSIcon(){
    return TSIcon;
}


@Override
public int hashCode(){
    String in = super.getId() + operationname;
    return in.hashCode();
}


@Override
public boolean equals(Object obj){
    if (this == obj)
        return false;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    TSOperation other = (TSOperation) obj;
    if (getId().equals(other.getId())) {
        return true;
    } else {
        return false;
    }
}


@Column(name = "operationname", length = 50)
public String getOperationname(){
    return this.operationname;
}


@Column(name = "operationcode", length = 50)
public String getOperationcode(){
    return this.operationcode;
}


public void setTSFunction(TSFunction tSFunction){
    TSFunction = tSFunction;
}


}
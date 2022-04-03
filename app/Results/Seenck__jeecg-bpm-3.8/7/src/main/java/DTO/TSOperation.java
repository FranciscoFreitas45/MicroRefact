package DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.jeecgframework.core.common.entity.IdEntity;
public class TSOperation extends IdEntity{

 private  String operationname;

 private  String operationcode;

 private  String operationicon;

 private  Short status;

 private  TSIcon TSIcon;

 private  TSFunction TSFunction;

 private  String processnodeId;

 private  Short operationType;


@Column(name = "PROCESSNODE_ID", nullable = true, length = 32)
public String getProcessnodeId(){
    return processnodeId;
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


@Column(name = "operationicon", length = 100)
public String getOperationicon(){
    return this.operationicon;
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


@Column(name = "operationname", length = 50)
public String getOperationname(){
    return this.operationname;
}


@Column(name = "operationcode", length = 50)
public String getOperationcode(){
    return this.operationcode;
}


}
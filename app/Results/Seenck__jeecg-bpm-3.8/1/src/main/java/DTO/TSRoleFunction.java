package DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.jeecgframework.core.common.entity.IdEntity;
public class TSRoleFunction extends IdEntity{

 private  TSFunction TSFunction;

 private  TSRole TSRole;

 private  String operation;

 private  String dataRule;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


@Column(name = "datarule", length = 100)
public String getDataRule(){
    return dataRule;
}


@Column(name = "operation", length = 100)
public String getOperation(){
    return this.operation;
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


public void setOperation(String operation){
    this.operation = operation;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setOperation"))

.queryParam("operation",operation)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTSFunction(TSFunction TSFunction){
    this.TSFunction = TSFunction;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTSFunction"))

.queryParam("TSFunction",TSFunction)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTSRole(TSRole TSRole){
    this.TSRole = TSRole;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTSRole"))

.queryParam("TSRole",TSRole)
;
restTemplate.put(builder.toUriString(),null);
}


}
package com.cym.model;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.InitValue;
import cn.craccd.sqlHelper.config.SingleIndex;
import cn.craccd.sqlHelper.config.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("证书解析码")
@Table
public class CertCode extends BaseModel{

@ApiModelProperty("证书id")
 private String certId;

@ApiModelProperty("域名")
 private String domain;

@ApiModelProperty("解析类型")
 private String type;

@ApiModelProperty("解析值")
 private String value;


public String getDomain(){
    return domain;
}


public void setCertId(String certId){
    this.certId = certId;
}


public String getValue(){
    return value;
}


public String getType(){
    return type;
}


public void setDomain(String domain){
    this.domain = domain;
}


public void setValue(String value){
    this.value = value;
}


public void setType(String type){
    this.type = type;
}


public String getCertId(){
    return certId;
}


}
package com.cym.model;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("额外参数")
@Table
public class Param extends BaseModel{

@ApiModelProperty("所属反向代理id")
 private String serverId;

@ApiModelProperty("所属代理目标id")
 private String locationId;

@ApiModelProperty("所属负载均衡id")
 private String upstreamId;

@ApiModelProperty(hidden = true)
 private String templateId;

@ApiModelProperty("参数名")
 private String name;

@ApiModelProperty("参数值")
 private String value;

@ApiModelProperty(hidden = true)
 private String templateValue;

@ApiModelProperty(hidden = true)
 private String templateName;


public void setName(String name){
    this.name = name;
}


public String getLocationId(){
    return locationId;
}


public String getName(){
    return name;
}


public void setTemplateValue(String templateValue){
    this.templateValue = templateValue;
}


public void setServerId(String serverId){
    this.serverId = serverId;
}


public void setTemplateId(String templateId){
    this.templateId = templateId;
}


public void setTemplateName(String templateName){
    this.templateName = templateName;
}


public String getUpstreamId(){
    return upstreamId;
}


public String getTemplateId(){
    return templateId;
}


public String getValue(){
    return value;
}


public void setValue(String value){
    this.value = value;
}


public String getTemplateName(){
    return templateName;
}


public String getServerId(){
    return serverId;
}


public void setLocationId(String locationId){
    this.locationId = locationId;
}


public String getTemplateValue(){
    return templateValue;
}


public void setUpstreamId(String upstreamId){
    this.upstreamId = upstreamId;
}


}
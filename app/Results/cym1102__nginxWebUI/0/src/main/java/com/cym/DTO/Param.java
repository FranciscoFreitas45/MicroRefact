package com.cym.DTO;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
public class Param extends BaseModel{

 private String serverId;

 private String locationId;

 private String upstreamId;

 private String templateId;

 private String name;

 private String value;

 private String templateValue;

 private String templateName;


public String getLocationId(){
    return locationId;
}


public String getName(){
    return name;
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


public String getTemplateName(){
    return templateName;
}


public String getServerId(){
    return serverId;
}


public String getTemplateValue(){
    return templateValue;
}


}
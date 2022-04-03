package com.cym.DTO;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.InitValue;
import cn.craccd.sqlHelper.config.Table;
public class Remote extends BaseModel{

 private String protocol;

 private String ip;

 private Integer port;

 private Integer status;

 private String creditKey;

 private String name;

 private String pass;

 private String version;

 private String system;

 private String descr;

 private Integer monitor;

 private String parentId;

 private Integer type;

 private Integer nginx;


public String getName(){
    return name;
}


public String getPass(){
    return pass;
}


public String getDescr(){
    return descr;
}


public String getIp(){
    return ip;
}


public Integer getStatus(){
    return status;
}


public String getCreditKey(){
    return creditKey;
}


public String getSystem(){
    return system;
}


public Integer getMonitor(){
    return monitor;
}


public String getVersion(){
    return version;
}


public String getProtocol(){
    return protocol;
}


public Integer getPort(){
    return port;
}


public Integer getType(){
    return type;
}


public Integer getNginx(){
    return nginx;
}


public String getParentId(){
    return parentId;
}


}
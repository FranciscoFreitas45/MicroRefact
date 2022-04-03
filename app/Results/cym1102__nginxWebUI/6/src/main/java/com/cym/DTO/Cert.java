package com.cym.DTO;
 import cn.craccd.sqlHelper.bean.BaseModel;
import cn.craccd.sqlHelper.config.InitValue;
import cn.craccd.sqlHelper.config.SingleIndex;
import cn.craccd.sqlHelper.config.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
public class Cert extends BaseModel{

 private String domain;

 private String pem;

 private String key;

 private Integer type;

 private Long makeTime;

 private Integer autoRenew;

 private String dnsType;

 private String dpId;

 private String dpKey;

 private String aliKey;

 private String aliSecret;

 private String cfEmail;

 private String cfKey;

 private String gdKey;

 private String gdSecret;

 private String hwUsername;

 private String hwPassword;

 private String hwProjectID;


public String getDpId(){
    return dpId;
}


public String getCfKey(){
    return cfKey;
}


public String getHwPassword(){
    return hwPassword;
}


public String getAliSecret(){
    return aliSecret;
}


public String getHwUsername(){
    return hwUsername;
}


public String getHwProjectID(){
    return hwProjectID;
}


public Long getMakeTime(){
    return makeTime;
}


public String getAliKey(){
    return aliKey;
}


public Integer getAutoRenew(){
    return autoRenew;
}


public String getCfEmail(){
    return cfEmail;
}


public String getGdSecret(){
    return gdSecret;
}


public String getKey(){
    return key;
}


public String getDnsType(){
    return dnsType;
}


public String getPem(){
    return pem;
}


public String getDpKey(){
    return dpKey;
}


public String getDomain(){
    return domain;
}


public Integer getType(){
    return type;
}


public String getGdKey(){
    return gdKey;
}


}
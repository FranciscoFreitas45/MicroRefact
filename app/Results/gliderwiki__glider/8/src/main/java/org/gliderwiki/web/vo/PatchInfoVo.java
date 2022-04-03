package org.gliderwiki.web.vo;
 import java.util.Date;
public class PatchInfoVo extends BaseObjectBean{

 private String weDomain;

 private String weActiveKey;

 private String weEmail;

 private Date weInstallDate;

 private Date weAuthDate;

 private String weAuthYn;

 private String weVersionInfo;

 private String weServerVerionInfo;


public void setWeInstallDate(Date weInstallDate){
    this.weInstallDate = weInstallDate;
}


public String getWeServerVerionInfo(){
    return weServerVerionInfo;
}


public Date getWeInstallDate(){
    return weInstallDate;
}


public void setWeAuthYn(String weAuthYn){
    this.weAuthYn = weAuthYn;
}


public void setWeServerVerionInfo(String weServerVerionInfo){
    this.weServerVerionInfo = weServerVerionInfo;
}


public String getWeAuthYn(){
    return weAuthYn;
}


public void setWeActiveKey(String weActiveKey){
    this.weActiveKey = weActiveKey;
}


public Date getWeAuthDate(){
    return weAuthDate;
}


public void setWeEmail(String weEmail){
    this.weEmail = weEmail;
}


public String getWeEmail(){
    return weEmail;
}


public String getWeActiveKey(){
    return weActiveKey;
}


public String getWeVersionInfo(){
    return weVersionInfo;
}


public void setWeDomain(String weDomain){
    this.weDomain = weDomain;
}


public String getWeDomain(){
    return weDomain;
}


public void setWeVersionInfo(String weVersionInfo){
    this.weVersionInfo = weVersionInfo;
}


public void setWeAuthDate(Date weAuthDate){
    this.weAuthDate = weAuthDate;
}


}
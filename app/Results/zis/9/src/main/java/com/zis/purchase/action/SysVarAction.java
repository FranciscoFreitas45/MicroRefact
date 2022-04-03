package com.zis.purchase.action;
 import java.util.List;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zis.common.cache.SysVarCache;
import com.zis.requirement.bean.SysVar;
public class SysVarAction extends ActionSupport{

 private  long serialVersionUID;

 private  SysVarCache sysVarCache;

 private  String depKey;


public String updateSysVarPre(){
    Integer depValue = sysVarCache.getSystemVar(depKey);
    ActionContext ctx = ActionContext.getContext();
    ctx.put("depKey", depKey);
    ctx.put("depValue", depValue);
    return SUCCESS;
}


public void setSysVarCache(SysVarCache sysVarCache){
    this.sysVarCache = sysVarCache;
}


public void setDepKey(String depKey){
    this.depKey = depKey;
}


public String getDepKey(){
    return depKey;
}


public SysVarCache getSysVarCache(){
    return sysVarCache;
}


public String queryAllSysVar(){
    List<SysVar> list = sysVarCache.getAllSysVars();
    ActionContext.getContext().put("SYSVARLIST", list);
    return SUCCESS;
}


}
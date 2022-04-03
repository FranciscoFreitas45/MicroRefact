package com.zis.purchase.action;
 import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.ConversionErrorFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.zis.common.cache.SysVarCache;
public class SysVarUpdateAction extends ActionSupport{

 private  long serialVersionUID;

 private  SysVarCache sysVarCache;

 private  Integer depValue;

 private  String depKey;


public void setDepValue(Integer depValue){
    this.depValue = depValue;
}


public void setSysVarCache(SysVarCache sysVarCache){
    this.sysVarCache = sysVarCache;
}


public Integer getDepValue(){
    return depValue;
}


public void setDepKey(String depKey){
    this.depKey = depKey;
}


@Validations(requiredFields = { @RequiredFieldValidator(fieldName = "depValue", key = "常量值不能为空") }, conversionErrorFields = { @ConversionErrorFieldValidator(fieldName = "depValue", key = "常量值只能填数字", shortCircuit = true) })
public String updateSysVar(){
    sysVarCache.updateSysVar(depKey, depValue);
    // SysVar sv = doPurchaseService.querySysVarByDepId(depId);
    // sv.setDepValue(depValue);
    // doPurchaseService.updateSysVar(sv);
    return SUCCESS;
}


public String getDepKey(){
    return depKey;
}


public SysVarCache getSysVarCache(){
    return sysVarCache;
}


}
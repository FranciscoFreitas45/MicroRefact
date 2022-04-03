package com.zis.purchase.calculate;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.zis.common.cache.SysVarCache;
import com.zis.common.cache.SysVarConstant;
@Component(value = "defaultCalculater")
public class DefaultCalculater implements BookAmountCalculateInterface{

@Autowired
 private  SysVarCache sysVarCache;


public Integer calculate(int bookId,Integer repoId){
    return sysVarCache.getSystemVar(SysVarConstant.PURCHASE_DEFAULT_REQURIE_AMT.getKeyName());
}


}
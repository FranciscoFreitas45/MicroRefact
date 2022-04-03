package com.zis.purchase.calculate;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.zis.common.cache.SysVarCache;
import com.zis.common.cache.SysVarConstant;
@Component(value = "mixedCalculater")
public class MixedCalculater implements BookAmountCalculateInterface{

@Autowired
 private  SalesCalculater salesCalculater;

@Autowired
 private  RequirementCalculater requirementCalculater;

@Autowired
 private  SysVarCache sysVarCache;


public Integer calculate(int bookId,Integer repoId){
    Integer salesPercent = sysVarCache.getSystemVar(SysVarConstant.PURCHASE_SALES_PERCENT.getKeyName());
    // List<SysVar> list1 = sysVarDao.findByDepKey("requirementPercent");
    // List<SysVar> list2 = sysVarDao.findByDepKey("salesPercent");
    int salesAmount = salesCalculater.calculate(bookId, repoId);
    int requirementAmount = requirementCalculater.calculate(bookId, repoId);
    if (salesAmount >= 0 && requirementAmount >= 0) {
        Integer part1 = salesAmount * salesPercent / 100;
        Integer part2 = requirementAmount * (1 - salesPercent / 100);
        return part1 + part2;
    } else {
        return 0;
    }
}


}
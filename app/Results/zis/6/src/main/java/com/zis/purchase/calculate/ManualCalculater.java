package com.zis.purchase.calculate;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.zis.purchase.bean.PurchasePlan;
import com.zis.purchase.repository.PurchasePlanDao;
@Component(value = "manualCalculater")
public class ManualCalculater implements BookAmountCalculateInterface{

@Autowired
 private  PurchasePlanDao purchasePlanDao;


@Override
public Integer calculate(int bookId,Integer repoId){
    PurchasePlan plan = this.purchasePlanDao.findByBookId(bookId, repoId);
    if (plan == null) {
        throw new RuntimeException("没有采购计划，bookId=" + bookId);
    }
    return plan.getManualDecision();
}


}
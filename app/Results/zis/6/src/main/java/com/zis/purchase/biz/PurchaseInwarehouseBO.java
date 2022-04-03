package com.zis.purchase.biz;
 import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.zis.common.util.ZisUtils;
import com.zis.purchase.bean.Inwarehouse;
import com.zis.purchase.bean.PurchaseDetail;
import com.zis.purchase.bean.PurchaseDetailStatus;
import com.zis.purchase.bean.PurchasePlan;
import com.zis.purchase.repository.PurchaseDetailDao;
@Deprecated
public class PurchaseInwarehouseBO extends InwarehouseBO{

@Autowired
 private  PurchaseDetailDao purchaseDetailDao;


public List<PurchaseDetail> findPurchaseDetail(String purchaseOperator,Integer bookId){
    return purchaseDetailDao.findByOperatorAndStatusAndBookId(purchaseOperator, PurchaseDetailStatus.PURCHASED, bookId);
}


public void afterPut(Inwarehouse in,Integer bookId,Integer amount){
    super.afterPut(in, bookId, amount);
    // 遍历所有采购明细，逐个更新
    List<PurchaseDetail> purchasedList = findPurchaseDetail(in.getSource(), bookId);
    if (purchasedList == null || purchasedList.isEmpty()) {
        throw new RuntimeException("数据错误，不存在未入库的采购明细，采购员=" + in.getSource() + "bookId=" + bookId);
    }
    // 剩余未入库数量
    Integer stillRemains = amount;
    int i = 0;
    do {
        PurchaseDetail pur = purchasedList.get(i);
        // 当前采购明细尚未入库的数量（简称“当前明细”）
        Integer purAmountNotIn = pur.getPurchasedAmount() - pur.getInwarehouseAmount();
        if (stillRemains >= purAmountNotIn) {
            // 剩余数量 >= 当前明细
            pur.setInwarehouseAmount(pur.getPurchasedAmount());
            pur.setStatus(PurchaseDetailStatus.CHECKED);
            pur.setGmtModify(ZisUtils.getTS());
            this.purchaseDetailDao.save(pur);
            stillRemains = stillRemains - purAmountNotIn;
        } else {
            // 剩余数量<当前明细
            pur.setInwarehouseAmount(pur.getInwarehouseAmount() + stillRemains);
            pur.setGmtModify(ZisUtils.getTS());
            this.purchaseDetailDao.save(pur);
            stillRemains = 0;
        }
        i++;
    } while (stillRemains > 0);
// // 更新在途库存量
// PurchasePlan plan = this.purchasePlanDao.findByBookId(bookId);
// if (plan == null) {
// // 计划不存在，则不更新
// return;
// }
// int remains = plan.getPurchasedAmount() - amount;
// remains = remains > 0 ? remains : 0;
// plan.setPurchasedAmount(remains);
// plan.setGmtModify(ZisUtils.getTS());
// this.purchasePlanDao.save(plan);
}


@Override
public String checkForDoInwarehouse(Inwarehouse in,Integer bookId,Integer amount){
    // 查询尚未入库的采购明细，并检查数量
    List<PurchaseDetail> purchasedList = findPurchaseDetail(in.getSource(), bookId);
    if (purchasedList == null || purchasedList.isEmpty()) {
        return "该记录已超出采购清单" + amount + "本";
    }
    // 已采购数量
    Integer amountPurchased = 0;
    // 已入库数量
    Integer amountChecked = 0;
    for (PurchaseDetail order : purchasedList) {
        amountPurchased += order.getPurchasedAmount();
        amountChecked += order.getInwarehouseAmount();
    }
    // 检查本次入库数量是否超过采购明细中的未入库数量
    int amountAfterThisCheck = amountPurchased - amountChecked - amount;
    if (amountAfterThisCheck < 0) {
        return "该记录已超出采购清单" + Math.abs(amountAfterThisCheck) + "本";
    }
    return StringUtils.EMPTY;
}


public void afterPutNew(String purchaseOperator,Integer bookId,Integer amount,Integer repoId){
    // super.afterPut(in, bookId, amount);
    // 遍历所有采购明细，逐个更新
    List<PurchaseDetail> purchasedList = findPurchaseDetail(purchaseOperator, bookId);
    if (purchasedList == null || purchasedList.isEmpty()) {
        throw new RuntimeException("数据错误，不存在未入库的采购明细，采购员=" + purchaseOperator + "bookId=" + bookId);
    }
    // 剩余未入库数量
    Integer stillRemains = amount;
    int i = 0;
    do {
        PurchaseDetail pur = purchasedList.get(i);
        // 当前采购明细尚未入库的数量（简称“当前明细”）
        Integer purAmountNotIn = pur.getPurchasedAmount() - pur.getInwarehouseAmount();
        if (stillRemains >= purAmountNotIn) {
            // 剩余数量 >= 当前明细
            pur.setInwarehouseAmount(pur.getPurchasedAmount());
            pur.setStatus(PurchaseDetailStatus.CHECKED);
            pur.setGmtModify(ZisUtils.getTS());
            this.purchaseDetailDao.save(pur);
            stillRemains = stillRemains - purAmountNotIn;
        } else {
            // 剩余数量<当前明细
            pur.setInwarehouseAmount(pur.getInwarehouseAmount() + stillRemains);
            pur.setGmtModify(ZisUtils.getTS());
            this.purchaseDetailDao.save(pur);
            stillRemains = 0;
        }
        i++;
    } while (stillRemains > 0);
    // 更新在途库存量
    PurchasePlan plan = this.purchasePlanDao.findByBookId(bookId, repoId);
    if (plan == null) {
        // 计划不存在，则不更新
        return;
    }
    int remains = plan.getPurchasedAmount() - amount;
    remains = remains > 0 ? remains : 0;
    plan.setPurchasedAmount(remains);
    plan.setGmtModify(ZisUtils.getTS());
    this.purchasePlanDao.save(plan);
}


public String checkForDoInwarehouseNew(String purchaseOperator,Integer bookId,Integer amount){
    // 查询尚未入库的采购明细，并检查数量
    List<PurchaseDetail> purchasedList = findPurchaseDetail(purchaseOperator, bookId);
    if (purchasedList == null || purchasedList.isEmpty()) {
        return "该记录已超出采购清单" + amount + "本";
    }
    // 已采购数量
    Integer amountPurchased = 0;
    // 已入库数量
    Integer amountChecked = 0;
    for (PurchaseDetail order : purchasedList) {
        amountPurchased += order.getPurchasedAmount();
        amountChecked += order.getInwarehouseAmount();
    }
    // 检查本次入库数量是否超过采购明细中的未入库数量
    int amountAfterThisCheck = amountPurchased - amountChecked - amount;
    if (amountAfterThisCheck < 0) {
        return "该记录已超出采购清单" + Math.abs(amountAfterThisCheck) + "本";
    }
    return StringUtils.EMPTY;
}


}
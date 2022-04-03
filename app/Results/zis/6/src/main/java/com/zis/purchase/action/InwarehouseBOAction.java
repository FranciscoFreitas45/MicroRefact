package com.zis.purchase.action;
 import org.apache.commons.lang3.StringUtils;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.purchase.dto.InwarehouseCreateDTO;
import com.zis.purchase.dto.InwarehouseCreateResult;
import com.zis.purchase.dto.InwarehouseDealtResult;
public class InwarehouseBOAction {

 private  DoPurchaseService doPurchaseService;


public String deleteInwarehouse(Integer inwarehouseId){
    try {
        this.doPurchaseService.deleteInwarehouse(inwarehouseId);
        return StringUtils.EMPTY;
    } catch (Exception e) {
        return "操作失败," + e.getMessage();
    }
}


public InwarehouseDealtResult doInwarehouse(Integer inwarehouseId,String posLabel,Integer bookId,Integer amount){
    try {
        return this.doPurchaseService.applyInwarehouse(inwarehouseId, posLabel, bookId, amount);
    } catch (Exception e) {
        InwarehouseDealtResult result = new InwarehouseDealtResult();
        result.setSuccess(false);
        result.setFailReason(e.getMessage());
        e.printStackTrace();
        return result;
    }
}


public String deleteInwarehouseDetail(Integer detailId){
    try {
        this.doPurchaseService.deleteInwarehouseDetail(detailId);
        return StringUtils.EMPTY;
    } catch (Exception e) {
        return "操作失败," + e.getMessage();
    }
}


public InwarehouseCreateResult createInwarehouse(InwarehouseCreateDTO inwarehouse){
    try {
        return this.doPurchaseService.createInwarehouse(inwarehouse);
    } catch (Exception e) {
        InwarehouseCreateResult result = new InwarehouseCreateResult();
        result.setSuccess(false);
        result.setFailReason(e.getMessage());
        return result;
    }
}


public void setDoPurchaseService(DoPurchaseService doPurchaseService){
    this.doPurchaseService = doPurchaseService;
}


}
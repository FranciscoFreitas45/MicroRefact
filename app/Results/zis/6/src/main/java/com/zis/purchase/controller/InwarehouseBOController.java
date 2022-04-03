package com.zis.purchase.controller;
 import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.directwebremoting.annotations.RemoteMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.purchase.dto.InwarehouseCreateDTO;
import com.zis.purchase.dto.InwarehouseCreateResult;
import com.zis.purchase.dto.InwarehouseDealtResult;
@Controller
public class InwarehouseBOController {

@Autowired
 private  DoPurchaseService doPurchaseService;


@RemoteMethod
@RequiresPermissions(value = "stock:delete")
public String deleteInwarehouse(Integer inwarehouseId){
    try {
        this.doPurchaseService.deleteInwarehouse(inwarehouseId);
        return StringUtils.EMPTY;
    } catch (Exception e) {
        return "操作失败," + e.getMessage();
    }
}


@RemoteMethod
@RequiresPermissions(value = "stock:input")
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


@RemoteMethod
@RequiresPermissions(value = "stock:delete")
public String deleteInwarehouseDetail(Integer detailId){
    try {
        this.doPurchaseService.deleteInwarehouseDetail(detailId);
        return StringUtils.EMPTY;
    } catch (Exception e) {
        return "操作失败," + e.getMessage();
    }
}


@RemoteMethod
@RequiresPermissions(value = "stock:input")
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


}
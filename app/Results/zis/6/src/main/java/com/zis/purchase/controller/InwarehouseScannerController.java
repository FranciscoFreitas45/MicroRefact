package com.zis.purchase.controller;
 import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.purchase.dto.InwarehouseView;
@Controller
@RequestMapping(value = "/purchase")
public class InwarehouseScannerController {

@Autowired
 private  DoPurchaseService doPurchaseService;


@RequiresPermissions(value = { "stock:input" })
@RequestMapping(value = "/recoverScan")
public String recoverScan(Integer inwarehouseId,ModelMap context){
    try {
        InwarehouseView view = this.doPurchaseService.findInwarehouseViewById(inwarehouseId);
        String[] positionLabels = view.getPositionLabel();
        if (positionLabels == null || positionLabels.length == 0) {
            // 如果没有可用库位，进行如下设置，方便页面展示
            positionLabels = new String[] { "无可用库位" };
        }
        // 参数传递到下一个页面，展示用
        context.put("inwarehouse", view);
        context.put("inwarehouseId", view.getId());
        context.put("stockPosLabel", positionLabels);
        context.put("bizTypeDisplay", view.getBizTypeDisplay());
        context.put("bizType", view.getBizType());
        context.put("purchaseOperator", view.getSource());
        context.put("inwarehouseOperator", view.getInwarehouseOperator());
        context.put("curPosition", positionLabels[0]);
        context.put("memo", view.getMemo());
        return "purchase/inwarehouseScanner";
    } catch (Exception e) {
        context.put("actionError", e.getMessage());
        return "error";
    }
}


@RequiresPermissions(value = { "stock:input" })
@RequestMapping(value = "/terminateInwarehouse")
public String terminate(Integer inwarehouseId,ModelMap map){
    try {
        this.doPurchaseService.applyTerminateInwarehouse(inwarehouseId);
        return "redirect:/purchase/viewInwarehouseList";
    } catch (Exception e) {
        map.put("actionError", e.getMessage());
        return "error";
    }
}


}
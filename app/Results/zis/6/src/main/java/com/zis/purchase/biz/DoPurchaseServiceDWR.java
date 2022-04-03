package com.zis.purchase.biz;
 import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.zis.storage.util.StorageUtil;
@Component
public class DoPurchaseServiceDWR {

@Autowired
 private  DoPurchaseService doPurchaseService;


@RequiresPermissions(value = "purchase:management")
public String removeManualDecision(Integer bookId){
    return this.doPurchaseService.removeManualDecision(bookId, StorageUtil.getRepoId());
}


@RequiresPermissions(value = "purchase:management")
public String addBlackList(Integer bookId){
    return this.doPurchaseService.addBlackList(bookId, StorageUtil.getRepoId());
}


@RequiresPermissions(value = "purchase:management")
public String updateAssociateTempImportDetailWithBookInfo(Integer tempImportDetailId,Integer bookId){
    return this.doPurchaseService.updateAssociateTempImportDetailWithBookInfo(tempImportDetailId, bookId);
}


@RequiresPermissions(value = "purchase:management")
public String addWhiteList(Integer bookId){
    return this.doPurchaseService.addWhiteList(bookId, StorageUtil.getRepoId());
}


@RequiresPermissions(value = "purchase:management")
public String recalculateRequireAmount(Integer bookId){
    return this.doPurchaseService.recalculateRequireAmount(bookId, StorageUtil.getRepoId());
}


@RequiresPermissions(value = "purchase:management")
public String updateBookStock(Integer bookId,Integer amount){
    return this.doPurchaseService.updateBookStock(bookId, amount, StorageUtil.getRepoId());
}


@RequiresPermissions(value = "purchase:management")
public String addManualDecision(Integer bookId,Integer amount){
    return this.doPurchaseService.addManualDecision(bookId, amount, StorageUtil.getRepoId());
}


@RequiresPermissions(value = "purchase:management")
public String deleteBlackOrWhiteList(Integer bookId){
    return this.doPurchaseService.deleteBlackOrWhiteList(bookId, StorageUtil.getRepoId());
}


}
package com.zis.purchase.controller;
 import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.bookinfo.bean.ShopItemInfoShopName;
import com.zis.bookinfo.bean.ShopItemInfoStatus;
import com.zis.bookinfo.dto.ShopItemInfoDTO;
import com.zis.bookinfo.service.BookService;
import com.zis.purchase.bean.TempImportDetailStatus;
import com.zis.purchase.bean.TempImportTask;
import com.zis.purchase.bean.TempImportTaskBizTypeEnum;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.purchase.dto.StockDTO;
import com.zis.purchase.dto.TempImportDetailView;
import com.zis.storage.util.StorageUtil;
import com.zis.Interface.BookService;
import com.zis.Interface.DoPurchaseService;
@Controller
@RequestMapping(value = "/purchase")
public class TempImportDetailTransferController {

 private  Logger logger;

@Autowired
 private  BookService bookService;

@Autowired
 private  DoPurchaseService doPurchaseService;


@RequiresPermissions(value = { "data:dataInfo" })
@RequestMapping(value = "/transferTempImportDetailForMatched")
public String transfer(Integer taskId,ModelMap map){
    TempImportTask task = doPurchaseService.findTempImportTaskByTaskId(taskId);
    if (task == null) {
        logger.error("临时导入记录转换成其他类型时发成错误，无此记录，taskId=" + taskId);
        map.put("actionError", "系统错误，无此记录，taskId=" + taskId);
        return "error";
    }
    try {
        switch(TempImportTaskBizTypeEnum.parseEnum(task.getBizType())) {
            case STOCK:
                transferToBookStock(taskId);
                break;
            case SHOP_STATUS:
                transferToShopItem(taskId);
                break;
            case SHOP_TITLE:
                updateShopItemInfo(true, false, false, taskId);
                break;
            case SHOP_CATEGORY_ID:
                updateShopItemInfo(false, true, false, taskId);
                break;
            case TAOBAO_FORBIDDEN:
                updateShopItemInfo(false, false, true, taskId);
                break;
            default:
                throw new RuntimeException("不支持的业务类型：" + task.getBizType());
        }
        return "success";
    } catch (Exception e) {
        logger.error("临时导入记录转换成其他类型时发成错误:" + e.getMessage(), e);
        map.put("actionError", "临时导入记录转换成其他类型时发生错误:" + e.getMessage());
        return "error";
    }
}


public void transferToShopItem(Integer taskId){
    List<TempImportDetailView> list = this.doPurchaseService.findTempImportDetail(taskId, TempImportDetailStatus.MATCHED);
    List<ShopItemInfoDTO> itemList = new ArrayList<ShopItemInfoDTO>();
    for (TempImportDetailView view : list) {
        if (!ShopItemInfoStatus.ON_SALES.equals(view.getAdditionalInfo()) && !ShopItemInfoStatus.SOLD_OUT.equals(view.getAdditionalInfo())) {
            throw new RuntimeException("商品状态必须是" + ShopItemInfoStatus.ON_SALES + "或者" + ShopItemInfoStatus.SOLD_OUT);
        }
        ShopItemInfoDTO item = new ShopItemInfoDTO();
        item.setBookId(view.getBookId());
        item.setShopName(ShopItemInfoShopName.TB_ZAIJIAN);
        item.setShopStatus(view.getAdditionalInfo());
        itemList.add(item);
    }
    this.bookService.saveShopItemForBatch(itemList);
}


public void transferToBookStock(Integer taskId){
    List<TempImportDetailView> list = this.doPurchaseService.findTempImportDetail(taskId, TempImportDetailStatus.MATCHED);
    List<StockDTO> stockList = new ArrayList<StockDTO>();
    for (TempImportDetailView view : list) {
        StockDTO stock = new StockDTO();
        stock.setBookId(view.getBookId());
        stock.setStockBalance(Integer.valueOf(view.getData()));
        stockList.add(stock);
    }
    this.doPurchaseService.addBookStock(stockList, StorageUtil.getRepoId());
}


public void updateShopItemInfo(boolean updateTitle,boolean updateCategoryId,boolean updateForbidden,Integer taskId){
    List<TempImportDetailView> list = this.doPurchaseService.findTempImportDetail(taskId, TempImportDetailStatus.MATCHED);
    List<ShopItemInfoDTO> detailList = new ArrayList<ShopItemInfoDTO>();
    for (TempImportDetailView view : list) {
        ShopItemInfoDTO detail = new ShopItemInfoDTO();
        detail.setBookId(view.getBookId());
        if (updateTitle) {
            detail.setTaobaoTitle(view.getData());
        }
        if (updateCategoryId) {
            detail.setTaobaoCatagoryId(Integer.valueOf(view.getData()));
        }
        if (updateForbidden) {
            detail.setTaobaoForbidden("是".equals(view.getData()));
        }
        detailList.add(detail);
    }
    this.bookService.updateTitleAndCategoryForBatch(detailList);
}


}
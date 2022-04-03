package com.zis.purchase.controller;
 import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.bean.BookinfoDetail;
import com.zis.bookinfo.dto.BookInfoAndDetailDTO;
import com.zis.purchase.bean.PurchasePlan;
import com.zis.purchase.bean.TempImportDetailStatus;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.purchase.dto.TempImportDetailView;
import com.zis.storage.entity.StorageProduct;
import com.zis.storage.service.StorageService;
import com.zis.storage.util.StorageUtil;
import com.zis.Interface.DoPurchaseService;
import com.zis.Interface.StorageService;
import com.zis.DTO.StorageService;
import com.zis.DTO.StorageProduct;
@Controller
@RequestMapping(value = "/purchase")
public class TaobaoCsvDataExportActionTempRecordImpl extends TaobaoCsvDataExportController{

@Autowired
 private  DoPurchaseService doPurchaseService;

@Autowired
 private  StorageService storageService;

 private  Logger logger;


@Override
public List<BookInfoAndDetailDTO> queryBookInfoAndDetails(HttpServletRequest request){
    String taskIdStr = request.getParameter("taskId");
    if (StringUtils.isNumeric(taskIdStr)) {
        Integer taskId = Integer.parseInt(taskIdStr);
        // 查询相关数据
        List<TempImportDetailView> inList = this.doPurchaseService.findTempImportDetail(taskId, TempImportDetailStatus.MATCHED);
        System.out.println(taskId);
        // 转换成BookInfoAndDetailDTO
        List<BookInfoAndDetailDTO> resultList = new ArrayList<BookInfoAndDetailDTO>();
        for (TempImportDetailView tmp : inList) {
            Bookinfo book = tmp.getAssociateBook();
            BookinfoDetail detail = bookService.findBookInfoDetailByBookId(book.getId());
            // 如果没有图书详情，则从网上采集
            if (detail == null) {
                try {
                    detail = bookService.captureBookInfoDetailFromNet(book.getId());
                } catch (Exception e) {
                    // 单条错误不能影响全部记录
                    logger.error("[数据采集] 采集过程中发生错误，bookId=", book.getId(), e);
                }
            }
            // 如果没有采集到图书详情，则跳过此条记录
            if (detail == null) {
                continue;
            }
            // 过滤淘宝黑名单记录
            if (detail.getTaobaoForbidden() != null && detail.getTaobaoForbidden() == true) {
                continue;
            }
            BookInfoAndDetailDTO infoAndDetail = new BookInfoAndDetailDTO();
            BeanUtils.copyProperties(book, infoAndDetail);
            BeanUtils.copyProperties(detail, infoAndDetail);
            // 查询库存量
            PurchasePlan plan = this.doPurchaseService.findPurchasePlanByBookId(book.getId(), StorageUtil.getRepoId());
            StorageProduct storageProduct = this.storageService.findBySkuIdAndRepoId(book.getId(), StorageUtil.getRepoId());
            Integer stockAmount = null;
            if (storageProduct == null) {
                stockAmount = 0;
            } else {
                stockAmount = storageProduct.getStockAmt();
            }
            if (plan == null || stockAmount == null || stockAmount <= 0) {
                // 跳过没有库存的记录
                continue;
            }
            if (plan != null) {
                infoAndDetail.setStockBalance(stockAmount);
            }
            resultList.add(infoAndDetail);
        }
        return resultList;
    } else {
        throw new IllegalArgumentException("taskId 异常");
    }
}


@Override
public String getSuccessPage(){
    return "success";
}


@RequiresPermissions(value = { "data:dataInfo" })
@RequestMapping(value = "/exportTaobaoItemDataByTempImport")
public String export(HttpServletRequest request,ModelMap map){
    return super.export(request, map);
}


}
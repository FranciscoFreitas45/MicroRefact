package com.zis.purchase.action;
 import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.bean.BookinfoDetail;
import com.zis.bookinfo.bean.ShopItemInfo;
import com.zis.bookinfo.bean.ShopItemInfoShopName;
import com.zis.bookinfo.dto.BookInfoAndDetailDTO;
import com.zis.purchase.bean.InwarehouseDetail;
import com.zis.purchase.bean.PurchasePlan;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.Interface.DoPurchaseService;
import com.zis.DTO.DoPurchaseService;
public class TaobaoCsvDataExportActionInwarehouseImpl extends TaobaoCsvDataExportAction{

 private  long serialVersionUID;

 private  List<String> uniqueIsbnDealt;

 private  Integer[] batchSelectedItem;

 private  DoPurchaseService doPurchaseService;

 private  Logger logger;


public String getUniqueIsbn(Bookinfo book){
    // 一码多书的，采用"条形码+bookId"作为唯一标识，正常的图书直接使用条形码
    return book.getRepeatIsbn() ? book.getIsbn() + "-" + book.getId() : book.getIsbn();
}


public void setBatchSelectedItem(Integer[] batchSelectedItem){
    this.batchSelectedItem = batchSelectedItem;
}


@Override
public List<BookInfoAndDetailDTO> queryBookInfoAndDetails(){
    // 查询相关数据
    DetachedCriteria criteria = DetachedCriteria.forClass(InwarehouseDetail.class);
    criteria.add(Restrictions.in("inwarehouseId", batchSelectedItem));
    List<InwarehouseDetail> inList = doPurchaseService.findInwarehouseDetailByCriteria(criteria);
    // 转换成BookInfoAndDetailDTO
    List<BookInfoAndDetailDTO> resultList = new ArrayList<BookInfoAndDetailDTO>();
    for (InwarehouseDetail record : inList) {
        Bookinfo book = this.bookService.findNormalBookById(record.getBookId());
        if (book == null) {
            throw new RuntimeException("没有找到对应图书，bookId=" + record.getBookId());
        }
        // 如果已经处理过，则跳过
        String uniqueIsbn = getUniqueIsbn(book);
        if (uniqueIsbnDealt.contains(uniqueIsbn)) {
            continue;
        } else {
            uniqueIsbnDealt.add(uniqueIsbn);
        }
        // 如果淘宝网已上架，则跳过
        ShopItemInfo item = this.bookService.findShopItemByBookIdAndShopName(ShopItemInfoShopName.TB_ZAIJIAN, book.getId());
        if (item != null) {
            continue;
        }
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
        PurchasePlan plan = this.doPurchaseService.findPurchasePlanByBookId(book.getId());
        if (plan == null || plan.getStockAmount() == null || plan.getStockAmount() <= 0) {
            // 跳过没有库存的记录
            continue;
        }
        if (plan != null) {
            infoAndDetail.setStockBalance(plan.getStockAmount());
        }
        resultList.add(infoAndDetail);
    }
    return resultList;
}


public void setDoPurchaseService(DoPurchaseService doPurchaseService){
    this.doPurchaseService = doPurchaseService;
}


}
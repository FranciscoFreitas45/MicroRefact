package com.zis.purchase.action;
 import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.bean.ShopItemInfo;
import com.zis.bookinfo.bean.ShopItemInfoShopName;
import com.zis.bookinfo.bean.ShopItemInfoStatus;
import com.zis.bookinfo.service.BookService;
import com.zis.common.actiontemplate.CommonExcelExportAction;
import com.zis.common.util.ZisUtils;
import com.zis.purchase.bean.InwarehouseDetail;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.Interface.BookService;
import com.zis.Interface.DoPurchaseService;
import com.zis.DTO.InwarehouseDetail;
import com.zis.DTO.BookService;
import com.zis.DTO.ShopItemInfo;
public class SoldOutItemExportByInwarehouseAction extends CommonExcelExportAction<InwarehouseDetail>{

 private  Integer[] batchSelectedItem;

 private  BookService bookService;

 private  DoPurchaseService doPurchaseService;


@Override
public String[] getTableHeaders(){
    return new String[] { "商家编码", "书名", "数量" };
}


public void setBatchSelectedItem(Integer[] batchSelectedItem){
    this.batchSelectedItem = batchSelectedItem;
}


@Override
public String[] getRowDatas(InwarehouseDetail record){
    Bookinfo book = this.bookService.findBookById(record.getBookId());
    String artNo = book.getRepeatIsbn() ? book.getIsbn() + "-" + book.getId() : book.getIsbn();
    return new String[] { artNo, book.getBookName(), record.getAmount() + "" };
}


@Override
public List<InwarehouseDetail> queryExportData(){
    DetachedCriteria criteria = DetachedCriteria.forClass(InwarehouseDetail.class);
    criteria.add(Restrictions.in("inwarehouseId", batchSelectedItem));
    return this.doPurchaseService.findInwarehouseDetailByCriteria(criteria);
}


@Override
public Collection<InwarehouseDetail> TransformResultList(List<InwarehouseDetail> list){
    Map<String, InwarehouseDetail> resultMap = new HashMap<String, InwarehouseDetail>();
    for (InwarehouseDetail curDetail : list) {
        // 过滤在售商品和网店没有的商品
        ShopItemInfo item = this.bookService.findShopItemByBookIdAndShopName(ShopItemInfoShopName.TB_ZAIJIAN, curDetail.getBookId());
        if (item == null || ShopItemInfoStatus.ON_SALES.equals(item.getShopStatus())) {
            continue;
        }
        // 如果有重复记录，自动合并
        String key = curDetail.getBookId() + "_" + curDetail.getPositionLabel();
        if (resultMap.containsKey(key)) {
            InwarehouseDetail existDetail = resultMap.get(key);
            existDetail.setAmount(existDetail.getAmount() + curDetail.getAmount());
        } else {
            resultMap.put(key, curDetail);
        }
    }
    return resultMap.values();
}


public void setDoPurchaseService(DoPurchaseService doPurchaseService){
    this.doPurchaseService = doPurchaseService;
}


public void setBookService(BookService bookService){
    this.bookService = bookService;
}


public Integer[] getBatchSelectedItem(){
    return batchSelectedItem;
}


@Override
public String setExportFileName(){
    return "卖空商品-" + ZisUtils.getDateString("yyyy-MM-dd") + ".xls";
}


}
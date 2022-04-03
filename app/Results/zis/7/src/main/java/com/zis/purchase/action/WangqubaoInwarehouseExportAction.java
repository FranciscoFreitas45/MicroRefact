package com.zis.purchase.action;
 import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.service.BookService;
import com.zis.common.actiontemplate.CommonExcelExportAction;
import com.zis.common.util.ZisUtils;
import com.zis.purchase.bean.InwarehouseDetail;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.Interface.BookService;
import com.zis.Interface.DoPurchaseService;
public class WangqubaoInwarehouseExportAction extends CommonExcelExportAction<InwarehouseDetail>{

 private  Integer[] batchSelectedItem;

 private  BookService bookService;

 private  DoPurchaseService doPurchaseService;


@Override
public String[] getTableHeaders(){
    return new String[] { "序号", "商品条码", "库位编号", "数量", "单价", "商品货号", "颜色", "规格" };
}


public void setBatchSelectedItem(Integer[] batchSelectedItem){
    this.batchSelectedItem = batchSelectedItem;
}


@Override
public String[] getRowDatas(InwarehouseDetail record){
    Bookinfo book = this.bookService.findBookById(record.getBookId());
    String artNo = book.getRepeatIsbn() ? book.getIsbn() + "-" + book.getId() : book.getIsbn();
    return new String[] { "", artNo, record.getPositionLabel(), record.getAmount() + "", book.getBookPrice() + "", artNo, "", "" };
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
    return "网渠宝入库单-" + ZisUtils.getDateString("yyyy-MM-dd") + ".xls";
}


}
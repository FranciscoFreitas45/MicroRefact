package com.zis.purchase.controller;
 import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.bean.ShopItemInfo;
import com.zis.bookinfo.bean.ShopItemInfoShopName;
import com.zis.bookinfo.bean.ShopItemInfoStatus;
import com.zis.bookinfo.service.BookService;
import com.zis.common.controllertemplate.CommonExcelExportController;
import com.zis.common.util.ZisUtils;
import com.zis.purchase.bean.InwarehouseDetail;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.Interface.BookService;
import com.zis.Interface.DoPurchaseService;
import com.zis.DTO.ShopItemInfo;
@Controller
@RequestMapping(value = "/purchase")
public class SoldOutItemExportByInwarehouseController extends CommonExcelExportController<InwarehouseDetail>{

@Autowired
 private  BookService bookService;

@Autowired
 private  DoPurchaseService doPurchaseService;


@Override
public String[] getTableHeaders(){
    return new String[] { "商家编码", "书名", "数量" };
}


@Override
public String[] getRowDatas(InwarehouseDetail record){
    Bookinfo book = this.bookService.findBookById(record.getBookId());
    String artNo = book.getRepeatIsbn() ? book.getIsbn() + "-" + book.getId() : book.getIsbn();
    return new String[] { artNo, book.getBookName(), record.getAmount() + "" };
}


@Override
public String getSuccessPage(){
    return "success";
}


@Override
public List<InwarehouseDetail> queryExportData(HttpServletRequest request){
    String[] batchSelectedItemStr = request.getParameterValues("batchSelectedItem");
    if (batchSelectedItemStr != null) {
        Integer[] batchSelectedItem = new Integer[batchSelectedItemStr.length];
        for (int i = 0; i < batchSelectedItemStr.length; i++) {
            batchSelectedItem[i] = Integer.parseInt(batchSelectedItemStr[i]);
        }
        return this.doPurchaseService.findInwarehouseDetailByInwarehouseIds(batchSelectedItem);
    } else {
        throw new IllegalArgumentException("batchSelectedItem 数组为空");
    }
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


@RequiresPermissions(value = { "stock:output" })
@RequestMapping(value = "/exportSoldOutItem")
public String export(HttpServletRequest request,HttpServletResponse response){
    return super.export(request, response);
}


@Override
public String setExportFileName(){
    return "卖空商品-" + ZisUtils.getDateString("yyyy-MM-dd") + ".xls";
}


}
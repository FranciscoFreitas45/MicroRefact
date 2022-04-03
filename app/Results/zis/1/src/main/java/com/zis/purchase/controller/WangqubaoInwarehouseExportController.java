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
import com.zis.bookinfo.service.BookService;
import com.zis.common.controllertemplate.CommonExcelExportController;
import com.zis.common.util.ZisUtils;
import com.zis.purchase.bean.InwarehouseDetail;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.Interface.BookService;
import com.zis.Interface.DoPurchaseService;
@Controller
@RequestMapping(value = "/purchase")
public class WangqubaoInwarehouseExportController extends CommonExcelExportController<InwarehouseDetail>{

@Autowired
 private  BookService bookService;

@Autowired
 private  DoPurchaseService doPurchaseService;


@Override
public String[] getTableHeaders(){
    return new String[] { "序号", "商品条码", "库位编号", "数量", "单价", "商品货号", "颜色", "规格" };
}


@Override
public String[] getRowDatas(InwarehouseDetail record){
    Bookinfo book = this.bookService.findBookById(record.getBookId());
    String artNo = book.getRepeatIsbn() ? book.getIsbn() + "-" + book.getId() : book.getIsbn();
    return new String[] { "", artNo, record.getPositionLabel(), record.getAmount() + "", book.getBookPrice() + "", artNo, "", "" };
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
@RequestMapping(value = "/exportWangqubaoInwarehouse")
public String export(HttpServletRequest request,HttpServletResponse response){
    return super.export(request, response);
}


@Override
public String setExportFileName(){
    return "网渠宝入库单-" + ZisUtils.getDateString("yyyy-MM-dd") + ".xls";
}


}
package com.zis.purchase.controller;
 import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class WangqubaoItemExportByInwarehouseController extends CommonExcelExportController<InwarehouseDetail>{

@Autowired
 private  BookService bookService;

@Autowired
 private  DoPurchaseService doPurchaseService;


public String getUniqueIsbn(Bookinfo book){
    // 一码多书的，采用"条形码+bookId"作为唯一标识，正常的图书直接使用条形码
    return book.getRepeatIsbn() ? book.getIsbn() + "-" + book.getId() : book.getIsbn();
}


@Override
public boolean isSkip(InwarehouseDetail record){
    Bookinfo book = this.bookService.findBookById(record.getBookId());
    // 已处理的记录，导出的时候判断重复项用
    Set<String> uniqueIsbnDealt = new HashSet<String>();
    if (book == null) {
        throw new RuntimeException("没有找到对应的图书：" + record.getBookId());
    }
    String uniqueIsbn = getUniqueIsbn(book);
    // 如果已经处理过，则跳过
    if (uniqueIsbnDealt.contains(uniqueIsbn)) {
        return true;
    } else {
        uniqueIsbnDealt.add(uniqueIsbn);
        return false;
    }
}


@Override
public String[] getTableHeaders(){
    return new String[] { "序号", "商品名称", "商品货号", "商品条形码", "仓库名称", "供应商", "供应商货号", "商品备案货号", "品牌", "商品属性", "大类名称", "小类名称", "单位", "颜色名称", "尺码名称", "单价", "条码", "亚马逊编码", "SKU条形码", "SKU备案货号", "重量", "颜色代码", "尺码代码", "原产地", "税率" };
}


@Override
public String[] getRowDatas(InwarehouseDetail record){
    String[] rowDatas = new String[this.getTableHeaders().length];
    Bookinfo book = this.bookService.findBookById(record.getBookId());
    String fmt = "%s (%s) %s %s";
    String itemTitle = String.format(fmt, book.getBookName(), book.getBookEdition(), book.getBookAuthor(), book.getIsbn());
    // 一码多书的，采用"条形码+bookId"作为唯一标识，正常的图书直接使用条形码
    String uniqueIsbn = getUniqueIsbn(book);
    // 商品名称
    rowDatas[1] = itemTitle;
    // 商品货号，必须是唯一标识
    rowDatas[2] = uniqueIsbn;
    // 商品条形码，网渠宝规定必须是数字，可以重复
    rowDatas[3] = book.getIsbn();
    rowDatas[8] = "二手教材";
    rowDatas[9] = "二手书";
    rowDatas[10] = "图书";
    rowDatas[11] = "二手书";
    rowDatas[15] = book.getBookPrice() + "";
    // 条码，更新库存用，与商品货号保持一致
    rowDatas[16] = uniqueIsbn;
    return rowDatas;
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


@RequiresPermissions(value = { "stock:output" })
@RequestMapping(value = "/exportWangqubaoItemByInwarehouse")
public String export(HttpServletRequest request,HttpServletResponse response){
    return super.export(request, response);
}


@Override
public String setExportFileName(){
    return "网渠宝商品资料-" + ZisUtils.getDateString("yyyy-MM-dd") + ".xls";
}


}
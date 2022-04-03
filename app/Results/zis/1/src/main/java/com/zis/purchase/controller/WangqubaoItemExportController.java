package com.zis.purchase.controller;
 import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.common.controllertemplate.CommonExcelExportController;
import com.zis.common.util.ZisUtils;
import com.zis.purchase.bean.TempImportDetailStatus;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.purchase.dto.TempImportDetailView;
import com.zis.Interface.DoPurchaseService;
import com.zis.DTO.DoPurchaseService;
@Controller
@RequestMapping(value = "/purchase")
public class WangqubaoItemExportController extends CommonExcelExportController<TempImportDetailView>{

 private  ThreadLocal<Set<String>> uniqueIsbnDealtLocal;

@Autowired
 private  DoPurchaseService doPurchaseService;


public String getUniqueIsbn(Bookinfo book){
    // 一码多书的，采用"条形码+bookId"作为唯一标识，正常的图书直接使用条形码
    return book.getRepeatIsbn() ? book.getIsbn() + "-" + book.getId() : book.getIsbn();
}


@Override
public boolean isSkip(TempImportDetailView record){
    String uniqueIsbn = getUniqueIsbn(record.getAssociateBook());
    Set<String> set = uniqueIsbnDealtLocal.get();
    if (set == null) {
        set = new HashSet<String>();
        uniqueIsbnDealtLocal.set(set);
    }
    // 如果已经处理过，则跳过
    if (set.contains(uniqueIsbn)) {
        return true;
    } else {
        set.add(uniqueIsbn);
        return super.isSkip(record);
    }
}


@Override
public String[] getTableHeaders(){
    return new String[] { "序号", "商品名称", "商品货号", "商品条形码", "仓库名称", "供应商", "供应商货号", "商品备案货号", "品牌", "商品属性", "大类名称", "小类名称", "单位", "颜色名称", "尺码名称", "单价", "条码", "亚马逊编码", "SKU条形码", "SKU备案货号", "重量", "颜色代码", "尺码代码", "原产地", "税率" };
}


@Override
public String[] getRowDatas(TempImportDetailView record){
    String[] rowDatas = new String[this.getTableHeaders().length];
    Bookinfo book = record.getAssociateBook();
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
public List<TempImportDetailView> queryExportData(HttpServletRequest request){
    String taskIdStr = request.getParameter("taskId");
    if (StringUtils.isNumeric(taskIdStr)) {
        Integer taskId = Integer.parseInt(taskIdStr);
        return this.doPurchaseService.findTempImportDetail(taskId, TempImportDetailStatus.MATCHED);
    } else {
        throw new IllegalArgumentException("taskId 异常");
    }
}


@RequiresPermissions(value = { "data:dataInfo" })
@RequestMapping(value = "/exportWangqubaoItemDataByTempImport")
public String export(HttpServletRequest request,HttpServletResponse response){
    return super.export(request, response);
}


@Override
public String setExportFileName(){
    return "网渠宝商品资料-" + ZisUtils.getDateString("yyyy-MM-dd") + ".xls";
}


}
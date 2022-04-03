package com.zis.purchase.controller;
 import java.util.List;
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
@Controller
@RequestMapping(value = "/purchase")
public class BookInfoExportController extends CommonExcelExportController<TempImportDetailView>{

@Autowired
 private  DoPurchaseService doPurchaseService;


public String getUniqueIsbn(Bookinfo book){
    // 一码多书的，采用"条形码+bookId"作为唯一标识，正常的图书直接使用条形码
    return book.getRepeatIsbn() ? book.getIsbn() + "-" + book.getId() : book.getIsbn();
}


@Override
public String[] getTableHeaders(){
    return new String[] { "条形码", "条形码（带后缀）", "书名", "版次", "作者", "出版社", "出版日期", "标价", "辅助1", "辅助2", "辅助3" };
}


@Override
public String[] getRowDatas(TempImportDetailView record){
    String[] rowDatas = new String[this.getTableHeaders().length];
    Bookinfo book = record.getAssociateBook();
    // 一码多书的，采用"条形码+bookId"作为唯一标识，正常的图书直接使用条形码
    rowDatas[0] = book.getIsbn();
    rowDatas[1] = getUniqueIsbn(book);
    rowDatas[2] = book.getBookName();
    rowDatas[3] = book.getBookEdition();
    rowDatas[4] = book.getBookAuthor();
    rowDatas[5] = book.getBookPublisher();
    String pubDate = ZisUtils.getDateString("yyyy-MM", book.getPublishDate());
    rowDatas[6] = pubDate;
    rowDatas[7] = book.getBookPrice() + "";
    rowDatas[8] = "46602357:311354894;46398806:10448865;122216620:16405245;2043193:10285019;2043189:129253040;2043183:311354894;1636953:126199908";
    rowDatas[9] = "46602357,46398806,2043189,2043183,122216620,1636953,2043193";
    // \"%2$s,%3$s,%4$s,%2$s,%5$s,%6$s,%7$s\"
    // 书名，price，作者，书名，出版社，isbn，出版日
    rowDatas[10] = String.format("%1$s,%2$s,%3$s,%1$s,%4$s,%5$s,%6$s", book.getBookName(), book.getBookPrice(), book.getBookAuthor(), book.getBookPublisher(), book.getIsbn(), pubDate);
    return rowDatas;
}


@Override
public String getSuccessPage(){
    return "success";
}


@Override
public List<TempImportDetailView> queryExportData(HttpServletRequest request){
    String strTaskId = request.getParameter("taskId");
    if (!StringUtils.isNumeric(strTaskId)) {
        throw new IllegalArgumentException("taskId 数组为空");
    }
    Integer taskId = Integer.parseInt(strTaskId);
    return this.doPurchaseService.findTempImportDetail(taskId, TempImportDetailStatus.MATCHED);
}


@RequiresPermissions(value = { "data:dataInfo" })
@RequestMapping(value = "/exportBookInfoByTempImport")
public String export(HttpServletRequest request,HttpServletResponse response){
    return super.export(request, response);
}


@Override
public String setExportFileName(){
    return "图书基本信息-" + ZisUtils.getDateString("yyyy-MM-dd") + ".xls";
}


}
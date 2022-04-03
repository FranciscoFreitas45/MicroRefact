package com.zis.purchase.action;
 import java.util.List;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.common.actiontemplate.CommonExcelExportAction;
import com.zis.common.util.ZisUtils;
import com.zis.purchase.bean.TempImportDetailStatus;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.purchase.dto.TempImportDetailView;
import com.zis.Interface.DoPurchaseService;
public class BookInfoExportAction extends CommonExcelExportAction<TempImportDetailView>{

 private  long serialVersionUID;

 private  Integer taskId;

 private  DoPurchaseService doPurchaseService;


public String getUniqueIsbn(Bookinfo book){
    // 一码多书的，采用"条形码+bookId"作为唯一标识，正常的图书直接使用条形码
    return book.getRepeatIsbn() ? book.getIsbn() + "-" + book.getId() : book.getIsbn();
}


public Integer getTaskId(){
    return taskId;
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
public List<TempImportDetailView> queryExportData(){
    return this.doPurchaseService.findTempImportDetail(taskId, TempImportDetailStatus.MATCHED);
}


public void setTaskId(Integer taskId){
    this.taskId = taskId;
}


public void setDoPurchaseService(DoPurchaseService doPurchaseService){
    this.doPurchaseService = doPurchaseService;
}


@Override
public String setExportFileName(){
    return "图书基本信息-" + ZisUtils.getDateString("yyyy-MM-dd") + ".xls";
}


}
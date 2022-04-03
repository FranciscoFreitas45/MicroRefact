package com.zis.storage.controller;
 import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.service.BookService;
import com.zis.common.controllertemplate.CommonExcelExportController;
import com.zis.common.util.ZisUtils;
import com.zis.storage.entity.StorageIoDetail;
import com.zis.storage.entity.StorageIoDetail.DetailStatus;
import com.zis.storage.entity.StorageIoDetail.IoType;
import com.zis.storage.service.StorageService;
import com.zis.storage.util.StorageUtil;
import com.zis.Interface.BookService;
@Controller
@RequestMapping(value = "/storage")
public class StorageIoBatchOutBatchController extends CommonExcelExportController<StorageIoDetail>{

@Autowired
 private  BookService bookService;

@Autowired
 private  StorageService storageService;

 private  String[] SUCCESS;


@Override
public String[] getTableHeaders(){
    return new String[] { "序号", "商品条码", "库位编号", "数量", "单价", "商品货号", "颜色", "规格" };
}


@Override
public String[] getRowDatas(StorageIoDetail record){
    Bookinfo book = this.bookService.findBookById(record.getSkuId());
    String artNo = book.getRepeatIsbn() ? book.getIsbn() + "-" + book.getId() : book.getIsbn();
    return new String[] { "", artNo, record.getPosLabel(), record.getAmount() + "", book.getBookPrice() + "", artNo, "", "" };
}


@Override
public String getSuccessPage(){
    return "success";
}


@Override
public List<StorageIoDetail> queryExportData(HttpServletRequest request){
    String[] batchSelectedItemStr = request.getParameterValues("batchId");
    if (batchSelectedItemStr != null) {
        Integer[] batchSelectedItem = new Integer[batchSelectedItemStr.length];
        for (int i = 0; i < batchSelectedItemStr.length; i++) {
            batchSelectedItem[i] = Integer.parseInt(batchSelectedItemStr[i]);
        }
        return this.storageService.findStorageIoDetailByRepoIdAndBatchIdInAndIoDetailTypeAndDetailStatusIn(StorageUtil.getRepoId(), Arrays.asList(batchSelectedItem), IoType.OUT.getValue(), Arrays.asList(SUCCESS));
    } else {
        throw new IllegalArgumentException("batchSelectedItem 数组为空");
    }
}


@Override
public Collection<StorageIoDetail> TransformResultList(List<StorageIoDetail> list){
    Map<String, StorageIoDetail> resultMap = new HashMap<String, StorageIoDetail>();
    for (StorageIoDetail curDetail : list) {
        String key = curDetail.getSkuId() + "_" + curDetail.getPosLabel();
        if (resultMap.containsKey(key)) {
            StorageIoDetail existDetail = resultMap.get(key);
            existDetail.setAmount(existDetail.getAmount() + curDetail.getAmount());
        } else {
            resultMap.put(key, curDetail);
        }
    }
    return resultMap.values();
}


@RequestMapping(value = "/exportWangqubaoSendOut")
public String export(HttpServletRequest request,HttpServletResponse response){
    return super.export(request, response);
}


@Override
public String setExportFileName(){
    return "网渠宝出库单-" + ZisUtils.getDateString("yyyy-MM-dd") + ".xls";
}


}
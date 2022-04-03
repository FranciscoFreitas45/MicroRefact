package com.zis.purchase.controller;
 import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.zis.common.controllertemplate.CommonImportController;
import com.zis.purchase.bean.TempImportTaskBizTypeEnum;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.purchase.dto.TempImportDTO;
import com.zis.Interface.DoPurchaseService;
@Controller
@RequestMapping(value = "/purchase")
public class TempImportUploadController extends CommonImportController<TempImportDTO>{

@Autowired
 private  DoPurchaseService doPurchaseService;

 private ThreadLocal<TempImportTaskBizTypeEnum> bizTypeLocal;

 private  String HEADER_ISBN;

 private  String HEADER_STOCK;

 private  String HEADER_SHOP_STATUS;

 private  String HEADER_SHOP_TITLE;

 private  String HEADER_SHOP_CATEGORY_ID;

 private  String HEADER_SHOP_FORBIDDEN;

 private  String supportedBizTypes;


@Override
public String initTemplatePath(){
    return null;
}


@Override
public String getFailPage(){
    return "error";
}


@Override
public Logger initLogger(){
    return Logger.getLogger(TempImportUploadController.class);
}


@Override
public void saveImportRecord(List<TempImportDTO> list,String memo){
    doPurchaseService.addTempImportTask(list, bizTypeLocal.get(), memo);
}


@Override
public Map<String,Integer> initPropMapping(){
    Map<String, Integer> map = new HashMap<String, Integer>();
    map.put("isbn", 0);
    map.put("data", 1);
    // 附加信息，不做检查
    map.put("additionalInfo", 2);
    return map;
}


@RequiresPermissions(value = { "data:dataInfo" })
@RequestMapping(value = "/uploadTempRecord")
public String upload(MultipartFile excelFile,String memo,ModelMap map){
    try {
        InputStream fileInputStream = excelFile.getInputStream();
        return super.upload(fileInputStream, memo, map);
    } catch (IOException e) {
        e.printStackTrace();
        map.put("actionError", "传入文件为空");
        return getFailPage();
    }
}


@Override
public String getSuccessPage(){
    return "redirect:/purchase/viewTempImportTask";
}


@Override
public String subCheckFileFormat(List<String> factHeader){
    if (!factHeader.get(0).equals(HEADER_ISBN)) {
        return "格式错误，第一列必须是" + HEADER_ISBN;
    }
    String secondCol = factHeader.get(1);
    if (secondCol.equals(HEADER_STOCK)) {
        bizTypeLocal.set(TempImportTaskBizTypeEnum.STOCK);
        return null;
    }
    if (secondCol.equals(HEADER_SHOP_STATUS)) {
        bizTypeLocal.set(TempImportTaskBizTypeEnum.SHOP_STATUS);
        return null;
    }
    if (secondCol.equals(HEADER_SHOP_TITLE)) {
        bizTypeLocal.set(TempImportTaskBizTypeEnum.SHOP_TITLE);
        return null;
    }
    if (secondCol.equals(HEADER_SHOP_CATEGORY_ID)) {
        bizTypeLocal.set(TempImportTaskBizTypeEnum.SHOP_CATEGORY_ID);
        return null;
    }
    if (secondCol.equals(HEADER_SHOP_FORBIDDEN)) {
        bizTypeLocal.set(TempImportTaskBizTypeEnum.TAOBAO_FORBIDDEN);
        return null;
    }
    return "格式错误，无法识别的业务类型，第二列必须是{" + supportedBizTypes + "}之一";
}


@Override
public TempImportDTO getInstance(){
    return new TempImportDTO();
}


public void setDoPurchaseService(DoPurchaseService doPurchaseService){
    this.doPurchaseService = doPurchaseService;
}


@Override
public String getFaildRecordMessage(TempImportDTO failRecord){
    return failRecord.toString();
}


}
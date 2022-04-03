package com.zis.purchase.action;
 import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.zis.common.actiontemplate.CommonImportAction;
import com.zis.purchase.bean.TempImportTaskBizTypeEnum;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.purchase.dto.TempImportDTO;
import com.zis.Interface.DoPurchaseService;
public class TempImportUploadAction extends CommonImportAction<TempImportDTO>{

 private  long serialVersionUID;

 private  DoPurchaseService doPurchaseService;

 private  TempImportTaskBizTypeEnum bizType;

 private  String memo;

 private  String HEADER_ISBN;

 private  String HEADER_STOCK;

 private  String HEADER_SHOP_STATUS;

 private  String HEADER_SHOP_TITLE;

 private  String HEADER_SHOP_CATEGORY_ID;

 private  String HEADER_SHOP_FORBIDDEN;

 private  String supportedBizTypes;


@Validations(requiredFields = { @RequiredFieldValidator(fieldName = "excelFile", key = "文件必须输入") })
@Override
public String initTemplatePath(){
    return null;
}


public void setMemo(String memo){
    this.memo = memo;
}


@Override
public Logger initLogger(){
    return Logger.getLogger(TempImportUploadAction.class);
}


public String getMemo(){
    return memo;
}


@Override
public void saveImportRecord(List<TempImportDTO> list){
    doPurchaseService.addTempImportTask(list, bizType, memo);
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


@Override
public String subCheckFileFormat(List<String> factHeader){
    if (!factHeader.get(0).equals(HEADER_ISBN)) {
        return "格式错误，第一列必须是" + HEADER_ISBN;
    }
    String secondCol = factHeader.get(1);
    if (secondCol.equals(HEADER_STOCK)) {
        bizType = TempImportTaskBizTypeEnum.STOCK;
        return null;
    }
    if (secondCol.equals(HEADER_SHOP_STATUS)) {
        bizType = TempImportTaskBizTypeEnum.SHOP_STATUS;
        return null;
    }
    if (secondCol.equals(HEADER_SHOP_TITLE)) {
        bizType = TempImportTaskBizTypeEnum.SHOP_TITLE;
        return null;
    }
    if (secondCol.equals(HEADER_SHOP_CATEGORY_ID)) {
        bizType = TempImportTaskBizTypeEnum.SHOP_CATEGORY_ID;
        return null;
    }
    if (secondCol.equals(HEADER_SHOP_FORBIDDEN)) {
        bizType = TempImportTaskBizTypeEnum.TAOBAO_FORBIDDEN;
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
package com.zis.oldapi;
 import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import com.zis.api.response.BaseApiResponse;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.Interface.DoPurchaseService;
public class ApiPurchasedAmountAddAction extends BaseApiAction{

 private  long serialVersionUID;

 private  Logger logger;

 private  DoPurchaseService doPurchaseService;

 private  String bookId;

 private  String purchasedAmount;

 private  String memo;

 private  String operator;

 private  String position;

 private  Map<String,Object> map;


public DoPurchaseService getDoPurchaseService(){
    return doPurchaseService;
}


public void setPurchasedAmount(String purchasedAmount){
    this.purchasedAmount = purchasedAmount;
}


public String validateParam(){
    if (StringUtils.isBlank(bookId)) {
        return "bookId不能为空";
    }
    try {
        Integer bookId1 = Integer.parseInt(bookId);
        map.put("bookId", bookId1);
    } catch (NumberFormatException e) {
        logger.error("bookId转换失败", e);
        return "bookId必须为数字";
    }
    if (StringUtils.isBlank(purchasedAmount)) {
        return "purchasedAmount不能为空";
    }
    try {
        Integer purchasedAmount1 = Integer.parseInt(purchasedAmount);
        map.put("purchasedAmount", purchasedAmount1);
    } catch (NumberFormatException e) {
        logger.error("purchasedAmount转换失败", e);
        return "purchasedAmount必须为数字";
    }
    if (StringUtils.isBlank(operator)) {
        return "操作员不能为空";
    }
    // 操作员名称中带回车不允许提交
    operator = operator.trim();
    if (operator.contains("\n")) {
        return "操作员名称中不能含有回车等特殊符号";
    }
    // if (StringUtils.isBlank(position)) {
    // return "位置不能为空";
    // }
    return "";
}


public void setDoPurchaseService(DoPurchaseService doPurchaseService){
    this.doPurchaseService = doPurchaseService;
}


public String getPurchasedAmount(){
    return purchasedAmount;
}


public void setPosition(String position){
    this.position = position;
}


public String addRequirementAmount(){
    String logMsg = String.format("api.ApiPurchasedAmountAddAction invoke, bookId=%s, purchasedAmount=%s, operator=%s, position=%s, memo=%s", bookId, purchasedAmount, operator, position, memo);
    logger.info(logMsg);
    // 参数检验
    String errMsg = validateParam();
    if (StringUtils.isNotBlank(errMsg)) {
        logger.info("api.AddBookRequirement--参数校验失败:" + errMsg);
        renderResponse(BaseApiResponse.CODE_ILLEGAL_ARGUMENT, "参数校验失败：" + errMsg);
        return SUCCESS;
    }
    // token检查
    String tokenCheckResult = checkToken();
    if (StringUtils.isNotBlank(tokenCheckResult)) {
        renderErrResult(tokenCheckResult);
        logger.info("api.PurchaseAmountAdd--" + tokenCheckResult);
        return SUCCESS;
    }
    // 保存采购数量
    int bookId = (Integer) map.get("bookId");
    int purchasedAmount = (Integer) map.get("purchasedAmount");
    try {
        String result = doPurchaseService.addPurchaseDetail(bookId, purchasedAmount, operator, position, memo);
        if (StringUtils.isNotBlank(result)) {
            // 清理token
            clearSessionToken();
            // 渲染结果
            renderResponse(BaseApiResponse.CODE_INNER_ERROR, result);
            return SUCCESS;
        } else {
            renderResponse(BaseApiResponse.CODE_SUCCESS, StringUtils.EMPTY);
            logger.info("api.ApiPurchasedAmountAddAction invoke successfully");
        }
        return SUCCESS;
    } catch (Exception e) {
        logger.error("api.ApiPurchasedAmountAddAction--系统内部错误", e);
        renderResponse(BaseApiResponse.CODE_INNER_ERROR, "系统内部错误" + e.getMessage());
        return SUCCESS;
    }
}


public void setBookId(String bookId){
    this.bookId = bookId;
}


public void setMemo(String memo){
    this.memo = memo;
}


public String getMemo(){
    return memo;
}


public void renderResponse(int code,String msg){
    BaseApiResponse response = new BaseApiResponse();
    response.setCode(code);
    response.setMsg(msg);
    renderResult(response);
}


public String getPosition(){
    return position;
}


public String getBookId(){
    return bookId;
}


public void setOperator(String operator){
    this.operator = operator;
}


public String getOperator(){
    return operator;
}


}
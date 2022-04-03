package com.zis.api;
 import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.api.response.BaseApiResponse;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.storage.entity.StorageProduct;
import com.zis.storage.service.StorageService;
import com.zis.Interface.DoPurchaseService;
import com.zis.Interface.StorageService;
import com.zis.DTO.StorageService;
@Controller
@RequestMapping(value = "/api")
public class ApiPurchasedAmountAddController extends BaseApiController{

 private  Logger logger;

@Autowired
 private  DoPurchaseService doPurchaseService;

@Autowired
 private  StorageService storageService;

 private  Integer ZIS_STORAGE_REPO_ID;


public void renderResponse(int code,String msg,HttpServletResponse resp){
    BaseApiResponse response = new BaseApiResponse();
    response.setCode(code);
    response.setMsg(msg);
    renderResult(response, resp);
}


public String validateParam(String bookId,String purchasedAmount,String operator,Map<String,Object> map){
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
    if (operator.contains("\n")) {
        return "操作员名称中不能含有回车等特殊符号";
    }
    // if (StringUtils.isBlank(position)) {
    // return "位置不能为空";
    // }
    return "";
}


@RequestMapping(value = "/addPurchasedAmount", produces = "text/plain; charset=utf-8")
public String addRequirementAmount(String memo,String operator,String bookId,String purchasedAmount,String position,HttpServletResponse response,String token){
    Map<String, Object> map = new HashMap<String, Object>();
    operator = operator.trim();
    String logMsg = String.format("api.ApiPurchasedAmountAddAction invoke, bookId=%s, purchasedAmount=%s, operator=%s, position=%s, memo=%s", bookId, purchasedAmount, operator, position, memo);
    logger.info(logMsg);
    // 参数检验
    operator = operator.trim();
    String errMsg = validateParam(bookId, purchasedAmount, operator, map);
    if (StringUtils.isNotBlank(errMsg)) {
        logger.info("api.AddBookRequirement--参数校验失败:" + errMsg);
        renderResponse(BaseApiResponse.CODE_ILLEGAL_ARGUMENT, "参数校验失败：" + errMsg, response);
        return "";
    }
    // token检查
    String tokenCheckResult = checkToken(token);
    if (StringUtils.isNotBlank(tokenCheckResult)) {
        renderErrResult(tokenCheckResult, response);
        logger.info("api.PurchaseAmountAdd--" + tokenCheckResult);
        return "";
    }
    // 保存采购数量
    int intBookId = (Integer) map.get("bookId");
    int intPurchasedAmount = (Integer) map.get("purchasedAmount");
    try {
        StorageProduct storageProduct = this.storageService.findBySkuIdAndRepoId(intBookId, ZIS_STORAGE_REPO_ID);
        Integer stockAmount = null;
        if (storageProduct == null) {
            stockAmount = 0;
        } else {
            stockAmount = storageProduct.getStockAmt();
        }
        String result = doPurchaseService.addPurchaseDetail(intBookId, intPurchasedAmount, operator, position, memo, ZIS_STORAGE_REPO_ID, stockAmount);
        if (StringUtils.isNotBlank(result)) {
            // 渲染结果
            renderResponse(BaseApiResponse.CODE_INNER_ERROR, result, response);
            return "";
        } else {
            renderResponse(BaseApiResponse.CODE_SUCCESS, StringUtils.EMPTY, response);
            logger.info("api.ApiPurchasedAmountAddAction invoke successfully");
        }
        return "";
    } catch (Exception e) {
        logger.error("api.ApiPurchasedAmountAddAction--系统内部错误", e);
        renderResponse(BaseApiResponse.CODE_INNER_ERROR, "系统内部错误" + e.getMessage(), response);
        return "";
    } finally {
        // 清理token
        clearSessionToken();
    }
}


}
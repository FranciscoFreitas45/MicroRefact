package com.zis.api;
 import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSON;
import com.zis.api.response.BaseApiResponse;
import com.zis.api.response.RequiredAmountQueryData;
import com.zis.api.response.RequiredAmountQueryDataV2;
import com.zis.api.response.RequiredAmountQueryResponse;
import com.zis.api.response.RequiredAmountQueryResponseV2;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.service.BookService;
import com.zis.purchase.bean.PurchasePlan;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.storage.entity.StorageProduct;
import com.zis.storage.service.StorageService;
import com.zis.Interface.DoPurchaseService;
import com.zis.Interface.BookService;
import com.zis.Interface.StorageService;
import com.zis.DTO.BookService;
import com.zis.DTO.StorageService;
import com.zis.DTO.StorageProduct;
import com.zis.DTO.StorageService;
@Controller
@RequestMapping(value = "/api")
public class ApiPurchasedAmountQueryController {

 private  Logger logger;

@Autowired
 private  DoPurchaseService doPurchaseService;

@Autowired
 private  BookService bookService;

@Autowired
 private  StorageService storageService;

 private  Integer ZIS_STORAGE_REPO_ID;


@Deprecated
/**
 * 采购查询接口，已废弃，使用v2
 * @return
 */
@RequestMapping(value = "/queryPurchaseAmount", produces = "text/plain; charset=utf-8")
public String queryRequiredAmount(String isbn,HttpServletResponse resp){
    logger.info("api.ApiPuchasedAmountQueryAction invoke, isbn=" + isbn);
    RequiredAmountQueryResponse response = new RequiredAmountQueryResponse();
    if (StringUtils.isBlank(isbn)) {
        response.setCode(BaseApiResponse.CODE_ILLEGAL_ARGUMENT);
        response.setMsg("ISNB不能为空!");
        renderResult(response, resp);
        return "";
    }
    List<PurchasePlan> list = doPurchaseService.findPurchasePlanByIsbn(isbn, ZIS_STORAGE_REPO_ID);
    List<RequiredAmountQueryData> resultList = new ArrayList<RequiredAmountQueryData>();
    // 如果采购计划中无此记录，提示系统无此记录
    if (list == null || list.isEmpty()) {
        RequiredAmountQueryData dq = new RequiredAmountQueryData();
        dq.setBookName("无此图书，请联系管理员");
        dq.setBookAuthor("");
        dq.setBookEdition("");
        dq.setBookPublisher("");
        dq.setIsbn(isbn);
        dq.setMemo("无此图书，请联系管理员");
        dq.setRequireAmount(0);
        resultList.add(dq);
    } else {
        for (PurchasePlan plan : list) {
            RequiredAmountQueryData dq = new RequiredAmountQueryData();
            BeanUtils.copyProperties(plan, dq);
            Bookinfo book = this.bookService.findBookById(plan.getBookId());
            // FIXME 废弃接口还可以使用
            Integer stockAmount = null;
            if (book != null) {
                // dq.setNewEdition(book.getIsNewEdition());
                StorageProduct storageProduct = this.storageService.findBySkuIdAndRepoId(book.getId(), ZIS_STORAGE_REPO_ID);
                if (storageProduct == null) {
                    stockAmount = 0;
                } else {
                    stockAmount = storageProduct.getStockAmt();
                }
            } else {
                stockAmount = 0;
            }
            dq.setRequireAmount(doPurchaseService.calculateStillRequireAmount(plan, stockAmount));
            resultList.add(dq);
        }
    }
    response.setCode(BaseApiResponse.CODE_SUCCESS);
    response.setResultList(resultList);
    renderResult(response, resp);
    return "";
}


@RequestMapping(value = "/queryPurchaseAmountV2", produces = "text/plain; charset=utf-8")
public String queryRequiredAmountV2(String isbn,HttpServletResponse resp){
    logger.info("api.ApiPuchasedAmountQueryAction invoke, isbn=" + isbn);
    RequiredAmountQueryResponseV2 response = new RequiredAmountQueryResponseV2();
    if (StringUtils.isBlank(isbn)) {
        response.setCode(BaseApiResponse.CODE_ILLEGAL_ARGUMENT);
        response.setMsg("ISNB不能为空!");
        renderResult(response, resp);
        return "";
    }
    // FIXME 采购员看到的采购计划 只为本公司使用
    List<PurchasePlan> list = doPurchaseService.findPurchasePlanByIsbn(isbn, ZIS_STORAGE_REPO_ID);
    List<RequiredAmountQueryDataV2> resultList = new ArrayList<RequiredAmountQueryDataV2>();
    // 如果采购计划中无此记录，提示系统无此记录
    if (list == null || list.isEmpty()) {
        RequiredAmountQueryDataV2 dq = new RequiredAmountQueryDataV2();
        dq.setBookName("无此图书，请联系管理员");
        dq.setBookAuthor("");
        dq.setBookEdition("");
        dq.setBookPublisher("");
        dq.setIsbn(isbn);
        dq.setMemo("无此图书，请联系管理员");
        dq.setRequireAmount(0);
        resultList.add(dq);
    } else {
        for (PurchasePlan plan : list) {
            RequiredAmountQueryDataV2 dq = new RequiredAmountQueryDataV2();
            BeanUtils.copyProperties(plan, dq);
            Bookinfo book = this.bookService.findBookById(plan.getBookId());
            Integer stockAmount = null;
            if (book != null) {
                dq.setNewEdition(book.getIsNewEdition());
                StorageProduct storageProduct = this.storageService.findBySkuIdAndRepoId(book.getId(), ZIS_STORAGE_REPO_ID);
                if (storageProduct == null) {
                    stockAmount = 0;
                } else {
                    stockAmount = storageProduct.getStockAmt();
                }
            } else {
                stockAmount = 0;
            }
            dq.setRequireAmount(doPurchaseService.calculateStillRequireAmount(plan, stockAmount));
            resultList.add(dq);
        }
    }
    response.setCode(BaseApiResponse.CODE_SUCCESS);
    response.setResultList(resultList);
    renderResult(response, resp);
    return "";
}


public void renderResult(Object obj,HttpServletResponse resp){
    // json序列化
    String content = JSON.toJSONString(obj);
    resp.setContentType("text/html;charset=utf-8");
    try {
        PrintWriter out = resp.getWriter();
        out.print(content);
        out.flush();
        out.close();
    } catch (IOException e) {
        logger.error("序列化过程失败", e);
    }
}


}
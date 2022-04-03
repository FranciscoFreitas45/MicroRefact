package com.zis.oldapi;
 import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.zis.api.response.BaseApiResponse;
import com.zis.api.response.RequiredAmountQueryData;
import com.zis.api.response.RequiredAmountQueryDataV2;
import com.zis.api.response.RequiredAmountQueryResponse;
import com.zis.api.response.RequiredAmountQueryResponseV2;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.service.BookService;
import com.zis.purchase.bean.PurchasePlan;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.Interface.DoPurchaseService;
import com.zis.Interface.BookService;
public class ApiPurchasedAmountQueryAction extends ActionSupport{

 private  long serialVersionUID;

 private  Logger logger;

 private  DoPurchaseService doPurchaseService;

 private  BookService bookService;

 private  String isbn;

 private  Integer ZIS_STORAGE_REPO_ID;


public void setIsbn(String isbn){
    this.isbn = isbn;
}


@Deprecated
public String queryRequiredAmount(){
    logger.info("api.ApiPuchasedAmountQueryAction invoke, isbn=" + isbn);
    RequiredAmountQueryResponse response = new RequiredAmountQueryResponse();
    if (StringUtils.isBlank(isbn)) {
        response.setCode(BaseApiResponse.CODE_ILLEGAL_ARGUMENT);
        response.setMsg("ISNB不能为空!");
        renderResult(response);
        return SUCCESS;
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
            dq.setRequireAmount(doPurchaseService.calculateStillRequireAmount(plan));
            resultList.add(dq);
        }
    }
    response.setCode(BaseApiResponse.CODE_SUCCESS);
    response.setResultList(resultList);
    renderResult(response);
    return SUCCESS;
}


public String queryRequiredAmountV2(){
    logger.info("api.ApiPuchasedAmountQueryAction invoke, isbn=" + isbn);
    RequiredAmountQueryResponseV2 response = new RequiredAmountQueryResponseV2();
    if (StringUtils.isBlank(isbn)) {
        response.setCode(BaseApiResponse.CODE_ILLEGAL_ARGUMENT);
        response.setMsg("ISNB不能为空!");
        renderResult(response);
        return SUCCESS;
    }
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
            if (book != null) {
                dq.setNewEdition(book.getIsNewEdition());
            }
            dq.setRequireAmount(doPurchaseService.calculateStillRequireAmount(plan));
            resultList.add(dq);
        }
    }
    response.setCode(BaseApiResponse.CODE_SUCCESS);
    response.setResultList(resultList);
    renderResult(response);
    return SUCCESS;
}


public void setDoPurchaseService(DoPurchaseService doPurchaseService){
    this.doPurchaseService = doPurchaseService;
}


public void renderResult(Object obj){
    // json序列化
    String content = JSON.toJSONString(obj);
    ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
    try {
        PrintWriter out = ServletActionContext.getResponse().getWriter();
        out.print(content);
        out.flush();
        out.close();
    } catch (IOException e) {
        logger.error("序列化过程失败", e);
    }
}


public void setBookService(BookService bookService){
    this.bookService = bookService;
}


public String getIsbn(){
    return isbn;
}


}
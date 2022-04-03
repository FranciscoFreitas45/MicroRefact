package com.zis.purchase.controller;
 import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.service.BookService;
import com.zis.common.controllertemplate.PaginationQueryController;
import com.zis.common.mvc.ext.QueryUtil;
import com.zis.common.util.ZisUtils;
import com.zis.purchase.bean.PurchasePlan;
import com.zis.purchase.bean.PurchasePlanStatus;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.purchase.dto.PurchasePlanView;
import com.zis.storage.entity.StorageProduct;
import com.zis.storage.service.StorageService;
import com.zis.storage.util.StorageUtil;
import com.zis.Interface.BookService;
import com.zis.Interface.StorageService;
import com.zis.DTO.BookService;
import com.zis.DTO.StorageService;
import com.zis.DTO.StorageProduct;
@Controller
@RequestMapping(value = "/purchase")
public class PurchasePlanViewController extends PaginationQueryController<PurchasePlan>{

@Autowired
 private  BookService bookService;

@Autowired
 private  DoPurchaseService doPurchaseService;

@Autowired
 private  StorageService storageService;


@Override
public String getFailPage(){
    return "purchase/purchasePlan";
}


@Override
public String setActionUrl(HttpServletRequest request){
    return "purchase/queryPurchasePlan";
}


@Override
public Page<PurchasePlan> buildPageList(Specification<PurchasePlan> spec,Pageable page){
    return this.doPurchaseService.findPurchasePlanBySpecPage(spec, page);
}


@Override
public void preProcessGetRequestCHN(HttpServletRequest request){
    String bookName = request.getParameter("bookName");
    String bookAuthor = request.getParameter("bookAuthor");
    String bookPublisher = request.getParameter("bookPublisher");
    if (StringUtils.isNotBlank(bookName)) {
        bookName = ZisUtils.convertGetRequestCHN(bookName);
    }
    if (StringUtils.isNotBlank(bookAuthor)) {
        bookAuthor = ZisUtils.convertGetRequestCHN(bookAuthor);
    }
    if (StringUtils.isNotBlank(bookPublisher)) {
        bookPublisher = ZisUtils.convertGetRequestCHN(bookPublisher);
    }
}


@Override
public String setActionUrlQueryCondition(HttpServletRequest request){
    StringBuilder condition = new StringBuilder();
    String isbn = request.getParameter("isbn");
    String bookName = request.getParameter("bookName");
    String strictBookName = request.getParameter("strictBookName");
    String bookAuthor = request.getParameter("bookAuthor");
    String bookPublisher = request.getParameter("bookPublisher");
    String minPlanAmountStr = request.getParameter("minPlanAmount");
    String maxPlanAmountStr = request.getParameter("minPlanAmount");
    if (StringUtils.isNotBlank(isbn)) {
        condition.append("isbn=" + isbn + "&");
    }
    if (StringUtils.isNotBlank(bookName)) {
        condition.append("bookName=" + bookName + "&");
    }
    if (StringUtils.isNotBlank(strictBookName) && Boolean.valueOf(strictBookName) == true) {
        condition.append("strictBookName=true&");
    }
    if (StringUtils.isNotBlank(bookAuthor)) {
        condition.append("bookAuthor=" + bookAuthor + "&");
    }
    if (StringUtils.isNotBlank(bookPublisher)) {
        condition.append("bookPublisher=" + bookPublisher + "&");
    }
    if (StringUtils.isNotBlank(minPlanAmountStr)) {
        Integer minPlanAmount = Integer.parseInt(minPlanAmountStr);
        condition.append("minPlanAmount=" + minPlanAmount + "&");
    }
    if (StringUtils.isNotBlank(maxPlanAmountStr)) {
        Integer maxPlanAmount = Integer.parseInt(maxPlanAmountStr);
        condition.append("maxPlanAmount=" + maxPlanAmount + "&");
    }
    return condition.toString();
}


@Override
public String getSuccessPage(HttpServletRequest request){
    return "purchase/purchasePlan";
}


@SuppressWarnings({ "rawtypes" })
@Override
public List transformResult(List<PurchasePlan> list){
    List<PurchasePlanView> resultList = new ArrayList<PurchasePlanView>();
    for (PurchasePlan record : list) {
        PurchasePlanView view = new PurchasePlanView();
        BeanUtils.copyProperties(record, view);
        Bookinfo book = this.bookService.findBookById(record.getBookId());
        // 是否是最新版
        if (book != null && book.getIsNewEdition() != null) {
            view.setNewEdition(book.getIsNewEdition());
        }
        // 是否一码多书
        if (book != null && book.getRepeatIsbn() != null) {
            view.setRepeatIsbn(book.getRepeatIsbn());
        }
        // 是否是人工定量优先
        if (record.getManualDecision() > 0 && doPurchaseService.isAllowManualDecisionForPurchasePlan()) {
            view.setManualDecisionFlag(true);
        }
        StorageProduct storageProduct = this.storageService.findBySkuIdAndRepoId(record.getBookId(), StorageUtil.getRepoId());
        Integer stockAmount = null;
        if (storageProduct == null) {
            stockAmount = 0;
        } else {
            stockAmount = storageProduct.getStockAmt();
        }
        view.setStockAmount(stockAmount);
        // 计算仍需采购量
        view.setStillRequireAmount(doPurchaseService.calculateStillRequireAmount(record, stockAmount));
        view.setPublishDate(book.getPublishDate());
        if (book.getBookPrice() != null && book.getBookPrice() > 0) {
            view.setBookPrice((float) Math.ceil(book.getBookPrice() * 0.2f));
        }
        resultList.add(view);
    }
    return resultList;
}


@Override
public Specification<PurchasePlan> buildQueryCondition(HttpServletRequest request){
    // DetachedCriteria dc = DetachedCriteria.forClass(PurchasePlan.class);
    QueryUtil<PurchasePlan> query = new QueryUtil<PurchasePlan>();
    String isbn = request.getParameter("isbn");
    String bookName = request.getParameter("bookName");
    String strictBookName = request.getParameter("strictBookName");
    String bookAuthor = request.getParameter("bookAuthor");
    String bookPublisher = request.getParameter("bookPublisher");
    String minPlanAmountStr = request.getParameter("minPlanAmount");
    String maxPlanAmountStr = request.getParameter("minPlanAmount");
    if (StringUtils.isNotBlank(isbn)) {
        String[] isbnStr = isbn.split(",");
        if (isbnStr.length > 1) {
            throw new RuntimeException("采购计划查询isbn不能输入多个");
        }
        if (!StringUtils.isNumeric(isbn.trim())) {
            throw new RuntimeException("isbn存在非法字符");
        }
        // dc.add(Restrictions.eq("isbn", isbn));
        query.eq("isbn", isbn.trim());
    }
    if (StringUtils.isNotBlank(bookName)) {
        if (StringUtils.isNotBlank(strictBookName) && Boolean.valueOf(strictBookName) == true) {
            // dc.add(Restrictions.eq("bookName", bookName));
            query.eq("bookName", bookName);
        } else {
            // dc.add(Restrictions.like("bookName", "%" + bookName + "%"));
            query.like("bookName", "%" + bookName + "%");
        }
    }
    if (StringUtils.isNotBlank(bookAuthor)) {
        query.like("bookAuthor", "%" + bookAuthor + "%");
    // dc.add(Restrictions.like("bookAuthor", "%" + bookAuthor + "%"));
    }
    if (StringUtils.isNotBlank(bookPublisher)) {
        query.like("bookPublisher", "%" + bookPublisher + "%");
    // dc.add(Restrictions.like("bookPublisher", "%" + bookPublisher +
    // "%"));
    }
    if (StringUtils.isNotBlank(minPlanAmountStr) && StringUtils.isNotBlank(maxPlanAmountStr)) {
        Integer minPlanAmount = Integer.parseInt(minPlanAmountStr);
        Integer maxPlanAmount = Integer.parseInt(maxPlanAmountStr);
        query.between("requireAmount", maxPlanAmount, minPlanAmount);
    // dc.add(Restrictions.between("requireAmount", minPlanAmount,
    // maxPlanAmount));
    } else if (StringUtils.isNotBlank(minPlanAmountStr)) {
        Integer minPlanAmount = Integer.parseInt(minPlanAmountStr);
        query.ge("requireAmount", minPlanAmount);
    // dc.add(Restrictions.ge("requireAmount", minPlanAmount));
    } else if (StringUtils.isNotBlank(maxPlanAmountStr)) {
        Integer maxPlanAmount = Integer.parseInt(maxPlanAmountStr);
        query.le("requireAmount", maxPlanAmount);
    // dc.add(Restrictions.le("requireAmount", maxPlanAmount));
    }
    query.eq("status", PurchasePlanStatus.NORMAL);
    // dc.add(Restrictions.eq("status", PurchasePlanStatus.NORMAL));
    return query.getSpecification();
}


@RequiresPermissions(value = { "purchase:view", "purchase:management" }, logical = Logical.OR)
@RequestMapping(value = "/queryPurchasePlan")
public String executeQuery(ModelMap context,HttpServletRequest request){
    return super.executeQuery(context, request);
}


}
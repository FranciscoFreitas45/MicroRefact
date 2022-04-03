package com.zis.purchase.action;
 import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.service.BookService;
import com.zis.common.actiontemplate.PaginationQueryAction;
import com.zis.common.util.ZisUtils;
import com.zis.purchase.bean.PurchasePlan;
import com.zis.purchase.bean.PurchasePlanStatus;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.purchase.dto.PurchasePlanView;
import com.zis.Interface.BookService;
public class PurchasePlanViewAction extends PaginationQueryAction<PurchasePlan>{

 private  long serialVersionUID;

 private  String isbn;

 private  String bookName;

 private  String bookAuthor;

 private  String bookPublisher;

 private  Integer minPlanAmount;

 private  Integer maxPlanAmount;

 private  Boolean strictBookName;

 private  BookService bookService;

 private  DoPurchaseService doPurchaseService;


public Integer getMinPlanAmount(){
    return minPlanAmount;
}


public void setMinPlanAmount(Integer minPlanAmount){
    this.minPlanAmount = minPlanAmount;
}


@Override
public String setActionUrl(){
    return "purchase/queryPurchasePlan";
}


@Override
public String setActionUrlQueryCondition(){
    StringBuilder condition = new StringBuilder();
    if (StringUtils.isNotBlank(isbn)) {
        condition.append("isbn=" + isbn + "&");
    }
    if (StringUtils.isNotBlank(bookName)) {
        condition.append("bookName=" + bookName + "&");
    }
    if (strictBookName != null && strictBookName == true) {
        condition.append("strictBookName=true&");
    }
    if (StringUtils.isNotBlank(bookAuthor)) {
        condition.append("bookAuthor=" + bookAuthor + "&");
    }
    if (StringUtils.isNotBlank(bookPublisher)) {
        condition.append("bookPublisher=" + bookPublisher + "&");
    }
    if (minPlanAmount != null) {
        condition.append("minPlanAmount=" + minPlanAmount + "&");
    }
    if (maxPlanAmount != null) {
        condition.append("maxPlanAmount=" + maxPlanAmount + "&");
    }
    return condition.toString();
}


public void setBookPublisher(String bookPublisher){
    this.bookPublisher = bookPublisher;
}


public void setBookAuthor(String bookAuthor){
    this.bookAuthor = bookAuthor;
}


public void setStrictBookName(Boolean strictBookName){
    this.strictBookName = strictBookName;
}


public void setDoPurchaseService(DoPurchaseService doPurchaseService){
    this.doPurchaseService = doPurchaseService;
}


public String getIsbn(){
    return isbn;
}


public void setBookService(BookService bookService){
    this.bookService = bookService;
}


public Boolean getStrictBookName(){
    return strictBookName;
}


public Integer getMaxPlanAmount(){
    return maxPlanAmount;
}


public void setIsbn(String isbn){
    this.isbn = isbn;
}


public String getBookAuthor(){
    return bookAuthor;
}


@Override
public void preProcessGetRequestCHN(){
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


public void setMaxPlanAmount(Integer maxPlanAmount){
    this.maxPlanAmount = maxPlanAmount;
}


public String getBookName(){
    return bookName;
}


public void setBookName(String bookName){
    this.bookName = bookName;
}


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
        // 计算仍需采购量
        view.setStillRequireAmount(doPurchaseService.calculateStillRequireAmount(record));
        view.setPublishDate(book.getPublishDate());
        if (book.getBookPrice() != null && book.getBookPrice() > 0) {
            view.setBookPrice((float) Math.ceil(book.getBookPrice() * 0.2f));
        }
        resultList.add(view);
    }
    return resultList;
}


@Override
public DetachedCriteria buildQueryCondition(){
    DetachedCriteria dc = DetachedCriteria.forClass(PurchasePlan.class);
    if (StringUtils.isNotBlank(isbn)) {
        dc.add(Restrictions.eq("isbn", isbn));
    }
    if (StringUtils.isNotBlank(bookName)) {
        if (strictBookName != null && strictBookName == true) {
            dc.add(Restrictions.eq("bookName", bookName));
        } else {
            dc.add(Restrictions.like("bookName", "%" + bookName + "%"));
        }
    }
    if (StringUtils.isNotBlank(bookAuthor)) {
        dc.add(Restrictions.like("bookAuthor", "%" + bookAuthor + "%"));
    }
    if (StringUtils.isNotBlank(bookPublisher)) {
        dc.add(Restrictions.like("bookPublisher", "%" + bookPublisher + "%"));
    }
    if (minPlanAmount != null && maxPlanAmount != null) {
        dc.add(Restrictions.between("requireAmount", minPlanAmount, maxPlanAmount));
    } else if (minPlanAmount != null) {
        dc.add(Restrictions.ge("requireAmount", minPlanAmount));
    } else if (maxPlanAmount != null) {
        dc.add(Restrictions.le("requireAmount", maxPlanAmount));
    }
    dc.add(Restrictions.eq("status", PurchasePlanStatus.NORMAL));
    return dc;
}


public String getBookPublisher(){
    return bookPublisher;
}


}
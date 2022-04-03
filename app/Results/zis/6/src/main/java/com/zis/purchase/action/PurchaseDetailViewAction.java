package com.zis.purchase.action;
 import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.service.BookService;
import com.zis.common.actiontemplate.PaginationQueryAction;
import com.zis.common.util.ZisUtils;
import com.zis.purchase.bean.PurchaseDetailStatus;
import com.zis.purchase.bean.PurchaseDetail;
import com.zis.purchase.dto.PurchaseDetailView;
import com.zis.Interface.BookService;
public class PurchaseDetailViewAction extends PaginationQueryAction<PurchaseDetail>{

 private  long serialVersionUID;

 private  Integer bookId;

 private  String isbn;

 private  String bookName;

 private  String operator;

 private  String status;

 private  BookService bookService;


@Override
public String setActionUrl(){
    return "purchase/queryPurchaseDetail";
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
    if (StringUtils.isNotBlank(operator)) {
        condition.append("operator=" + operator + "&");
    }
    if (StringUtils.isNotBlank(status)) {
        condition.append("status=" + status + "&");
    }
    if (bookId != null) {
        condition.append("bookId=" + bookId + "&");
    }
    return condition.toString();
}


public String getIsbn(){
    return isbn;
}


public String getStatus(){
    return status;
}


public void setBookService(BookService bookService){
    this.bookService = bookService;
}


public void setStatus(String status){
    this.status = status;
}


public void setBookId(Integer bookId){
    this.bookId = bookId;
}


public void setIsbn(String isbn){
    this.isbn = isbn;
}


@Override
public void preProcessGetRequestCHN(){
    if (StringUtils.isNotBlank(bookName)) {
        bookName = ZisUtils.convertGetRequestCHN(bookName);
    }
    if (StringUtils.isNotBlank(operator)) {
        operator = ZisUtils.convertGetRequestCHN(operator);
    }
}


public Integer getBookId(){
    return bookId;
}


public String getBookName(){
    return bookName;
}


public void setBookName(String bookName){
    this.bookName = bookName;
}


public void setOperator(String operator){
    this.operator = operator;
}


@Override
public List transformResult(List<PurchaseDetail> list){
    List<PurchaseDetailView> resultList = new ArrayList<PurchaseDetailView>();
    for (PurchaseDetail record : list) {
        PurchaseDetailView view = new PurchaseDetailView();
        Bookinfo book = this.bookService.findBookById(record.getBookId());
        if (book != null) {
            BeanUtils.copyProperties(book, view);
            view.setNewEdition(book.getIsNewEdition());
        }
        BeanUtils.copyProperties(record, view);
        view.setStatusDisplay(PurchaseDetailStatus.getDisplay(record.getStatus()));
        resultList.add(view);
    }
    return resultList;
}


@Override
public DetachedCriteria buildQueryCondition(){
    DetachedCriteria dc = DetachedCriteria.forClass(PurchaseDetail.class);
    if (bookId != null) {
        dc.add(Restrictions.eq("bookId", bookId));
    } else // 如果输入了isbn或者图书，则先查询图书信息
    if (StringUtils.isNotBlank(isbn) || StringUtils.isNotBlank(bookName) || bookId != null) {
        DetachedCriteria bookCriteria = DetachedCriteria.forClass(Bookinfo.class);
        if (StringUtils.isNotBlank(isbn)) {
            bookCriteria.add(Restrictions.eq("isbn", isbn));
        }
        if (StringUtils.isNotBlank(bookName)) {
            bookCriteria.add(Restrictions.like("bookName", "%" + bookName + "%"));
        }
        List<Bookinfo> blist = bookService.findBookByCriteria(bookCriteria);
        List<Integer> bookIds = new ArrayList<Integer>();
        if (blist == null || blist.isEmpty()) {
            return null;
        }
        for (Bookinfo bi : blist) {
            bookIds.add(bi.getId());
        }
        dc.add(Restrictions.in("bookId", bookIds));
    }
    // 附加操作员条件
    if (StringUtils.isNotBlank(operator)) {
        dc.add(Restrictions.eq("operator", operator));
    }
    if (StringUtils.isNotBlank(status)) {
        dc.add(Restrictions.eq("status", status));
    }
    dc.addOrder(Order.desc("gmtCreate"));
    return dc;
}


public String getOperator(){
    return operator;
}


}
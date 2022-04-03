package com.zis.purchase.action;
 import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import com.opensymphony.xwork2.ActionSupport;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.service.BookService;
import com.zis.bookinfo.util.ConstantString;
import com.zis.common.util.Page;
import com.zis.common.util.PaginationQueryUtil;
import com.zis.common.util.ZisUtils;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.Interface.BookService;
public class DoPurchaseAction extends ActionSupport{

 private  long serialVersionUID;

 private  Logger logger;

 private  String isbn;

 private  String keyword;

 private  String purchaseOperator;

 private  DoPurchaseService doPurchaseService;

 private  BookService bookService;


public DoPurchaseService getDoPurchaseService(){
    return doPurchaseService;
}


public void setDoPurchaseService(DoPurchaseService doPurchaseService){
    this.doPurchaseService = doPurchaseService;
}


public String batchUpdatePurchasePlanForPurchaseAmount(){
    this.doPurchaseService.batchUpdatePurchasePlanForPurchaseAmount();
    return SUCCESS;
}


public void setBookService(BookService bookService){
    this.bookService = bookService;
}


public String getIsbn(){
    return isbn;
}


public String getPurchaseOperator(){
    return purchaseOperator;
}


public String getKeyword(){
    return keyword;
}


public String doPurchase(){
    // 如果指定了bookId则只处理这一条
    if (StringUtils.isNotBlank(isbn)) {
        DetachedCriteria dc = DetachedCriteria.forClass(Bookinfo.class);
        dc.add(Restrictions.eq("isbn", isbn));
        List<Bookinfo> list = this.bookService.findBookByCriteria(dc);
        doPurchaseService.addPurchasePlanForBatch(list);
        return SUCCESS;
    }
    // 考虑到服务器压力，分批处理采购计划
    final int batchSize = 1000;
    DetachedCriteria dc = DetachedCriteria.forClass(Bookinfo.class);
    Integer totalCount = PaginationQueryUtil.getTotalCount(dc);
    Page page = Page.createPage(1, batchSize, totalCount);
    for (int i = 0; i < page.getTotalPageCount(); i++) {
        page = Page.createPage(i + 1, batchSize, totalCount);
        logger.info("采购计划开始，from=" + page.getBeginIndex() + ", to=" + (page.getBeginIndex() + batchSize));
        try {
            @SuppressWarnings("unchecked")
            List<Bookinfo> list = PaginationQueryUtil.paginationQuery(dc, page);
            doPurchaseService.addPurchasePlanForBatch(list);
        } catch (Exception e) {
            logger.error("执行采购计划过程中出错" + e);
        }
    }
    return SUCCESS;
}


public String clearOnwayStock(){
    this.doPurchaseService.deleteOnwayStock(purchaseOperator);
    return SUCCESS;
}


public String batchAddIntoBlackList(){
    if (StringUtils.isBlank(keyword)) {
        return SUCCESS;
    }
    DetachedCriteria criteria = DetachedCriteria.forClass(Bookinfo.class);
    criteria.add(Restrictions.like("bookName", "%" + keyword + "%"));
    // FIXME 时间限定写死
    criteria.add(Restrictions.lt("publishDate", ZisUtils.stringToDate("2016-01")));
    criteria.add(Restrictions.eq("bookStatus", ConstantString.USEFUL));
    criteria.setProjection(Projections.property("id"));
    List<Bookinfo> list = this.bookService.findBookByCriteria(criteria);
    for (Object obj : list) {
        Integer bookId = (Integer) obj;
        doPurchaseService.addBlackList(bookId);
    }
    return SUCCESS;
}


public void setIsbn(String isbn){
    this.isbn = isbn;
}


public void setKeyword(String keyword){
    this.keyword = keyword;
}


public void setPurchaseOperator(String purchaseOperator){
    this.purchaseOperator = purchaseOperator;
}


public BookService getBookService(){
    return bookService;
}


}
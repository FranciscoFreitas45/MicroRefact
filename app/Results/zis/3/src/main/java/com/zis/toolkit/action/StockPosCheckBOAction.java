package com.zis.toolkit.action;
 import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.service.BookService;
import com.zis.purchase.bean.TempImportDetail;
import com.zis.purchase.bean.TempImportDetailStatus;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.purchase.repository.TempImportDetailDao;
import com.zis.Interface.TempImportDetailDao;
@Deprecated
public class StockPosCheckBOAction {

 private  String SESSION_KEY_RECORDS;

 private  String SESSION_KEY_POS;

 private  Integer taskId;

@Autowired
 private  TempImportDetailDao tempImportDetailDao;

 private  BookService bookService;


public List<Bookinfo> putBookIntoSession(Bookinfo bookinfo,HttpSession session){
    @SuppressWarnings("unchecked")
    List<Bookinfo> bookList = (List<Bookinfo>) session.getAttribute(SESSION_KEY_RECORDS);
    if (bookList == null) {
        bookList = new ArrayList<Bookinfo>();
        session.setAttribute(SESSION_KEY_RECORDS, bookList);
    }
    bookList.add(bookinfo);
    return bookList;
}


public Set<String> putPosIntoSession(Set<String> stockPosLabel,HttpSession session){
    @SuppressWarnings("unchecked")
    Set<String> pos = (Set<String>) session.getAttribute(SESSION_KEY_POS);
    if (pos == null) {
        // 如果是第一次处理，将stockPosLabel直接放入缓存并返回
        pos = stockPosLabel;
        session.setAttribute(SESSION_KEY_POS, pos);
    } else {
        // 如果不是第一次处理，将stockPosLabel和缓存中的set取交集并返回
        pos.retainAll(stockPosLabel);
    }
    return pos;
}


public void clearSession(HttpSession session){
    session.setAttribute(SESSION_KEY_POS, null);
    session.setAttribute(SESSION_KEY_RECORDS, null);
}


public void setBookService(BookService bookService){
    this.bookService = bookService;
}


}
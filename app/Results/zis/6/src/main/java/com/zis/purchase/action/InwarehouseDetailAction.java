package com.zis.purchase.action;
 import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import com.opensymphony.xwork2.ActionContext;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.service.BookService;
import com.zis.common.actiontemplate.PaginationQueryAction;
import com.zis.purchase.bean.InwarehouseDetail;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.purchase.dto.InwarehouseDetailView;
import com.zis.purchase.dto.InwarehouseView;
import com.zis.Interface.BookService;
public class InwarehouseDetailAction extends PaginationQueryAction<InwarehouseDetail>{

 private  Integer inwarehouseId;

 private  BookService bookService;

 private  DoPurchaseService doPurchaseService;


public void setInwarehouseId(Integer inwarehouseId){
    this.inwarehouseId = inwarehouseId;
}


@Override
public String setActionUrl(){
    return "purchase/viewInwarehouseDetail";
}


@Override
public String setActionUrlQueryCondition(){
    return "inwarehouseId=" + inwarehouseId + "&";
}


public void setDoPurchaseService(DoPurchaseService doPurchaseService){
    this.doPurchaseService = doPurchaseService;
}


@Override
public List transformResult(List<InwarehouseDetail> list){
    // 结果集转换，方便页面展示
    List<InwarehouseDetailView> viewList = new ArrayList<InwarehouseDetailView>();
    for (InwarehouseDetail detail : list) {
        Bookinfo book = this.bookService.findBookById(detail.getBookId());
        InwarehouseDetailView view = new InwarehouseDetailView();
        BeanUtils.copyProperties(book, view);
        BeanUtils.copyProperties(detail, view);
        viewList.add(view);
    }
    return viewList;
}


public void setBookService(BookService bookService){
    this.bookService = bookService;
}


@Override
public void doBeforeReturn(){
    InwarehouseView view = this.doPurchaseService.findInwarehouseViewById(inwarehouseId);
    ActionContext context = ActionContext.getContext();
    context.put("inwarehouse", view);
}


public Integer getInwarehouseId(){
    return inwarehouseId;
}


@Override
public DetachedCriteria buildQueryCondition(){
    DetachedCriteria criteria = DetachedCriteria.forClass(InwarehouseDetail.class);
    criteria.add(Restrictions.eq("inwarehouseId", inwarehouseId));
    return criteria;
}


}
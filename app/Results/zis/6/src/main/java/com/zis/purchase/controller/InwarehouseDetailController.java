package com.zis.purchase.controller;
 import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
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
import com.zis.purchase.bean.InwarehouseDetail;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.purchase.dto.InwarehouseDetailView;
import com.zis.purchase.dto.InwarehouseView;
import com.zis.Interface.BookService;
@Controller
@RequestMapping(value = "/purchase")
public class InwarehouseDetailController extends PaginationQueryController<InwarehouseDetail>{

@Autowired
 private  BookService bookService;

@Autowired
 private  DoPurchaseService doPurchaseService;


@Override
public String getFailPage(){
    return "error";
}


@Override
public String setActionUrl(HttpServletRequest request){
    return "purchase/viewInwarehouseDetail";
}


@Override
public Page<InwarehouseDetail> buildPageList(Specification<InwarehouseDetail> spec,Pageable page){
    return this.doPurchaseService.findInwarehouseDetailBySpecPage(spec, page);
}


@Override
public String setActionUrlQueryCondition(HttpServletRequest request){
    String inwarehouseId = request.getParameter("inwarehouseId");
    return "inwarehouseId=" + inwarehouseId + "&";
}


@Override
public String getSuccessPage(HttpServletRequest request){
    return "purchase/inwarehouseDetail";
}


@SuppressWarnings({ "rawtypes" })
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


@Override
public void doBeforeReturn(ModelMap context,HttpServletRequest request){
    String idStr = request.getParameter("inwarehouseId");
    if (StringUtils.isBlank(idStr)) {
        throw new IllegalArgumentException("inwarehouseId is null");
    }
    Integer inwarehouseId = Integer.parseInt(idStr);
    InwarehouseView view = this.doPurchaseService.findInwarehouseViewById(inwarehouseId);
    context.put("inwarehouse", view);
}


@Override
public Specification<InwarehouseDetail> buildQueryCondition(HttpServletRequest request){
    QueryUtil<InwarehouseDetail> query = new QueryUtil<InwarehouseDetail>();
    String idStr = request.getParameter("inwarehouseId");
    if (StringUtils.isBlank(idStr)) {
        throw new IllegalArgumentException("inwarehouseId is null");
    }
    Integer inwarehouseId = Integer.parseInt(idStr);
    query.eq("inwarehouseId", inwarehouseId);
    return query.getSpecification();
}


@RequiresPermissions(value = { "stock:view" })
@RequestMapping(value = "/viewInwarehouseDetail")
public String executeQuery(ModelMap context,HttpServletRequest request){
    return super.executeQuery(context, request);
}


}
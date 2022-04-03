package com.zis.purchase.controller;
 import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.common.controllertemplate.PaginationQueryController;
import com.zis.common.mvc.ext.QueryUtil;
import com.zis.purchase.bean.Inwarehouse;
import com.zis.purchase.bean.InwarehouseBizType;
import com.zis.purchase.bean.InwarehouseStatus;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.purchase.dto.InwarehouseView;
@Controller
@RequestMapping(value = "/purchase")
public class InwarehouseViewController extends PaginationQueryController<Inwarehouse>{

@Autowired
 private  DoPurchaseService doPurchaseService;


@Override
public String getFailPage(){
    return "error";
}


@Override
public String setActionUrl(HttpServletRequest request){
    return "purchase/viewInwarehouseList";
}


@Override
public Page<Inwarehouse> buildPageList(Specification<Inwarehouse> spec,Pageable page){
    return this.doPurchaseService.findInwarehouseBySpecPage(spec, page);
}


@Override
public String setActionUrlQueryCondition(HttpServletRequest request){
    return "";
}


@Override
public String getSuccessPage(HttpServletRequest request){
    return "purchase/inwarehouseList";
}


@SuppressWarnings({ "rawtypes" })
@Override
public List transformResult(List<Inwarehouse> list){
    List<InwarehouseView> resultList = new ArrayList<InwarehouseView>();
    for (Inwarehouse in : list) {
        InwarehouseView record = new InwarehouseView();
        BeanUtils.copyProperties(in, record);
        record.setBizTypeDisplay(InwarehouseBizType.getDisplay(in.getBizType()));
        record.setStatusDisplay(InwarehouseStatus.getDisplay(in.getStatus()));
        resultList.add(record);
    }
    return resultList;
}


@Override
public Specification<Inwarehouse> buildQueryCondition(HttpServletRequest request){
    QueryUtil<Inwarehouse> query = new QueryUtil<Inwarehouse>();
    query.ne("status", InwarehouseStatus.CANCEL);
    query.desc("gmtCreate");
    // DetachedCriteria criteria =
    // DetachedCriteria.forClass(Inwarehouse.class);
    // criteria.add(Restrictions.ne("status", InwarehouseStatus.CANCEL));
    // criteria.addOrder(Order.desc("gmtCreate"));
    return query.getSpecification();
}


@RequiresPermissions(value = { "stock:view" })
@RequestMapping(value = "/viewInwarehouseList")
public String executeQuery(ModelMap context,HttpServletRequest request){
    request.setAttribute("sort", new String[] { "gmtCreate" });
    return super.executeQuery(context, request);
}


}
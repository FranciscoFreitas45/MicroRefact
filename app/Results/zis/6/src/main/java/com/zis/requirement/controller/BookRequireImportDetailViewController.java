package com.zis.requirement.controller;
 import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zis.common.controllertemplate.PaginationQueryController;
import com.zis.common.mvc.ext.QueryUtil;
import com.zis.requirement.bean.BookRequireImportDetail;
import com.zis.requirement.bean.BookRequireImportDetailStatus;
import com.zis.requirement.biz.BookRequireImportBO;
@Controller
@RequestMapping(value = "/requirement")
public class BookRequireImportDetailViewController extends PaginationQueryController<BookRequireImportDetail>{

@Autowired
 private  BookRequireImportBO bookRequireImportBO;


@Override
public String getFailPage(){
    return "error";
}


@Override
public String setActionUrl(HttpServletRequest request){
    return "requirement/viewBookRequireImportDetailForMatched";
}


@Override
public Page<BookRequireImportDetail> buildPageList(Specification<BookRequireImportDetail> spec,Pageable page){
    return this.bookRequireImportBO.findAllBookRequireImportDetailBySpecPage(spec, page);
}


@Override
public String setActionUrlQueryCondition(HttpServletRequest request){
    String status = request.getParameter("status");
    StringBuilder condition = new StringBuilder();
    String taskIdStr = request.getParameter("taskId");
    Integer taskId;
    if (StringUtils.isNumeric(taskIdStr)) {
        taskId = Integer.parseInt(taskIdStr);
        condition.append("taskId=" + taskId + "&");
    } else {
        throw new IllegalArgumentException("taskId is error");
    }
    if (StringUtils.isNotBlank(status)) {
        condition.append("status=" + status + "&");
    }
    return condition.toString();
}


@Override
public String getSuccessPage(HttpServletRequest request){
    String status = request.getParameter("status");
    if (BookRequireImportDetailStatus.BOOK_NOT_MATCHED.equals(status)) {
        return "requirement/bookRequireImportDetailForBookNotMatched";
    } else if (BookRequireImportDetailStatus.DEPARTMENT_NOT_MATCHED.equals(status)) {
        return "requirement/bookRequireImportDetailForDepartmentNotMatched";
    } else {
        return "requirement/bookRequireImportDetailForMatched";
    }
}


@Override
public Specification<BookRequireImportDetail> buildQueryCondition(HttpServletRequest request){
    String status = request.getParameter("status");
    String taskIdStr = request.getParameter("taskId");
    Integer taskId;
    if (StringUtils.isNumeric(taskIdStr)) {
        taskId = Integer.parseInt(taskIdStr);
    } else {
        throw new IllegalArgumentException("taskId is error");
    }
    // DetachedCriteria criteria =
    // DetachedCriteria.forClass(BookRequireImportDetail.class);
    // criteria.add(Restrictions.eq("status", status));
    // criteria.add(Restrictions.eq("batchId", taskId));
    // criteria.addOrder(Order.asc("gmtCreate"));
    QueryUtil<BookRequireImportDetail> query = new QueryUtil<BookRequireImportDetail>();
    query.eq("status", status);
    query.eq("batchId", taskId);
    query.asc("gmtCreate");
    return query.getSpecification();
}


@RequiresPermissions(value = { "requirement:viewBookRequireImportDetailForMatched" })
@RequestMapping(value = "/viewBookRequireImportDetailForMatched")
public String executeQuery(ModelMap context,HttpServletRequest request){
    return super.executeQuery(context, request);
}


}
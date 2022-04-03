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
import com.zis.purchase.bean.TempImportDetail;
import com.zis.purchase.bean.TempImportDetailStatus;
import com.zis.purchase.bean.TempImportTask;
import com.zis.purchase.bean.TempImportTaskBizTypeEnum;
import com.zis.purchase.bean.TempImportTaskStatus;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.purchase.dto.TempImportDetailView;
import com.zis.purchase.dto.TempImportTaskView;
import com.zis.Interface.BookService;
import com.zis.Interface.DoPurchaseService;
import com.zis.DTO.QueryUtil;
@Controller
@RequestMapping(value = "/purchase")
public class TempImportDetailViewController extends PaginationQueryController<TempImportDetail>{

@Autowired
 private  BookService bookService;

@Autowired
 private  DoPurchaseService doPurchaseService;


@Override
public String getFailPage(){
    return null;
}


@Override
public String setActionUrl(HttpServletRequest request){
    return "purchase/viewTempImportDetailForMatched";
}


@Override
public Page<TempImportDetail> buildPageList(Specification<TempImportDetail> spec,Pageable page){
    return this.doPurchaseService.findTempImportDetailBySpecPage(spec, page);
}


@Override
public String getSuccessPage(HttpServletRequest request){
    String status = request.getParameter("status");
    if (TempImportDetailStatus.NOT_MATCHED.equals(status)) {
        return "purchase/tempImportDetailViewForNotMatched";
    } else {
        return "purchase/tempImportDetailViewForMatched";
    }
}


@Override
public String setActionUrlQueryCondition(HttpServletRequest request){
    String status = request.getParameter("status");
    String taskIdStr = request.getParameter("taskId");
    StringBuilder condition = new StringBuilder();
    if (StringUtils.isNumeric(taskIdStr)) {
        Integer taskId = Integer.parseInt(taskIdStr);
        condition.append("taskId=" + taskId + "&");
    }
    if (StringUtils.isNotBlank(status)) {
        condition.append("status=" + status + "&");
    }
    return condition.toString();
}


@Override
public void doBeforeQuery(HttpServletRequest request){
    String taskIdStr = request.getParameter("taskId");
    if (StringUtils.isNumeric(taskIdStr)) {
        Integer taskId = Integer.parseInt(taskIdStr);
        this.doPurchaseService.updateTempImportDetail(taskId);
    } else {
        throw new IllegalArgumentException("taskId 异常");
    }
}


@SuppressWarnings({ "rawtypes" })
@Override
public List transformResult(List<TempImportDetail> list){
    // 转换结果
    List<TempImportDetailView> resultList = new ArrayList<TempImportDetailView>();
    for (TempImportDetail detail : list) {
        TempImportDetailView view = new TempImportDetailView();
        BeanUtils.copyProperties(detail, view);
        // 未匹配成功的，查找出可能相关的记录
        if (detail.getStatus().equals(TempImportDetailStatus.NOT_MATCHED)) {
            List<Bookinfo> relatedBooks = this.bookService.findBookByISBN(detail.getOrigIsbn());
            view.setRelatedBooks(relatedBooks);
            view.setIsbn(detail.getOrigIsbn());
        } else // 匹配成功的，查找出匹配的记录
        if (detail.getStatus().equals(TempImportDetailStatus.MATCHED)) {
            Bookinfo book = this.bookService.findBookById(detail.getBookId());
            if (book == null) {
                throw new RuntimeException("图书记录不存在,bookId=" + detail.getBookId());
            }
            view.setAssociateBook(book);
        } else {
            throw new RuntimeException("临时导入记录状态不正确, id=" + detail.getId());
        }
        resultList.add(view);
    }
    return resultList;
}


@Override
public void doBeforeReturn(ModelMap context,HttpServletRequest request){
    String taskIdStr = request.getParameter("taskId");
    if (StringUtils.isNumeric(taskIdStr)) {
        Integer taskId = Integer.parseInt(taskIdStr);
        TempImportTask task = this.doPurchaseService.findTempImportTaskByTaskId(taskId);
        // 给下一个页面准备参数
        TempImportTaskView view = new TempImportTaskView();
        BeanUtils.copyProperties(task, view);
        view.setBizTypeDisplay(TempImportTaskBizTypeEnum.parseEnum(task.getBizType()).getDisplayValue());
        view.setStatusDisplay(TempImportTaskStatus.getDisplay(task.getStatus()));
        context.put("task", view);
    } else {
        throw new IllegalArgumentException("taskId 异常");
    }
}


@Override
public Specification<TempImportDetail> buildQueryCondition(HttpServletRequest request){
    String taskIdStr = request.getParameter("taskId");
    String status = request.getParameter("status");
    if (StringUtils.isNumeric(taskIdStr)) {
        Integer taskId = Integer.parseInt(taskIdStr);
        QueryUtil<TempImportDetail> query = new QueryUtil<TempImportDetail>();
        query.eq("taskId", taskId);
        query.eq("status", status);
        // DetachedCriteria criteria =
        // DetachedCriteria.forClass(TempImportDetail.class);
        // criteria.add(Restrictions.eq("taskId", taskId));
        // criteria.add(Restrictions.eq("status", status));
        return query.getSpecification();
    } else {
        throw new IllegalArgumentException("taskId 异常");
    }
}


@RequiresPermissions(value = { "data:dataInfo" })
@RequestMapping(value = "/viewTempImportDetailForMatched")
public String executeQuery(ModelMap context,HttpServletRequest request){
    return super.executeQuery(context, request);
}


}
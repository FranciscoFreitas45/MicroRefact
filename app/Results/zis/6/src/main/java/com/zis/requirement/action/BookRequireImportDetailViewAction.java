package com.zis.requirement.action;
 import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import com.zis.common.actiontemplate.PaginationQueryAction;
import com.zis.requirement.bean.BookRequireImportDetail;
import com.zis.requirement.bean.BookRequireImportDetailStatus;
public class BookRequireImportDetailViewAction extends PaginationQueryAction<BookRequireImportDetail>{

 private  long serialVersionUID;

 private  String status;

 private  Integer taskId;


public Integer getTaskId(){
    return taskId;
}


@Override
public String setActionUrl(){
    if (BookRequireImportDetailStatus.BOOK_NOT_MATCHED.equals(status)) {
        return "requirement/viewBookRequireImportDetailForBookNotMatched";
    } else if (BookRequireImportDetailStatus.DEPARTMENT_NOT_MATCHED.equals(status)) {
        return "requirement/viewBookRequireImportDetailForDepartmentNotMatched";
    } else {
        return "requirement/viewBookRequireImportDetailForMatched";
    }
}


@Override
public String setActionUrlQueryCondition(){
    StringBuilder condition = new StringBuilder();
    if (taskId != null) {
        condition.append("taskId=" + taskId + "&");
    }
    if (StringUtils.isNotBlank(status)) {
        condition.append("status=" + status + "&");
    }
    return condition.toString();
}


public void setTaskId(Integer taskId){
    this.taskId = taskId;
}


public String getStatus(){
    return status;
}


@Override
public DetachedCriteria buildQueryCondition(){
    DetachedCriteria criteria = DetachedCriteria.forClass(BookRequireImportDetail.class);
    criteria.add(Restrictions.eq("status", status));
    criteria.add(Restrictions.eq("batchId", taskId));
    criteria.addOrder(Order.asc("gmtCreate"));
    return criteria;
}


public void setStatus(String status){
    this.status = status;
}


}
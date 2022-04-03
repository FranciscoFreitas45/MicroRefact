package com.zis.requirement.action;
 import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.BeanUtils;
import com.zis.common.actiontemplate.PaginationQueryAction;
import com.zis.requirement.bean.BookRequireImportTask;
import com.zis.requirement.bean.BookRequireImportTaskStatus;
import com.zis.requirement.dto.BookRequireImportTaskView;
public class BookRequireImportTaskViewAction extends PaginationQueryAction<BookRequireImportTask>{

 private  long serialVersionUID;


@Override
public String setActionUrl(){
    return "requirement/viewBookRequireImportTask";
}


@Override
public String setActionUrlQueryCondition(){
    return "";
}


@Override
public List<BookRequireImportTaskView> transformResult(List<BookRequireImportTask> list){
    List<BookRequireImportTaskView> viewList = new ArrayList<BookRequireImportTaskView>();
    for (BookRequireImportTask task : list) {
        BookRequireImportTaskView view = new BookRequireImportTaskView();
        BeanUtils.copyProperties(task, view);
        view.setStatusDisplay(BookRequireImportTaskStatus.getDisplay(task.getStatus()));
        viewList.add(view);
    }
    return viewList;
}


@Override
public DetachedCriteria buildQueryCondition(){
    DetachedCriteria criteria = DetachedCriteria.forClass(BookRequireImportTask.class);
    criteria.addOrder(Order.desc("gmtCreate"));
    return criteria;
}


}
package com.zis.purchase.action;
 import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import com.zis.common.actiontemplate.PaginationQueryAction;
import com.zis.purchase.bean.Inwarehouse;
import com.zis.purchase.bean.InwarehouseBizType;
import com.zis.purchase.bean.InwarehouseStatus;
import com.zis.purchase.dto.InwarehouseView;
public class InwarehouseViewAction extends PaginationQueryAction<Inwarehouse>{

 private  long serialVersionUID;


@Override
public String setActionUrl(){
    return "purchase/viewInwarehouseList";
}


@Override
public String setActionUrlQueryCondition(){
    return "";
}


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
public DetachedCriteria buildQueryCondition(){
    DetachedCriteria criteria = DetachedCriteria.forClass(Inwarehouse.class);
    criteria.add(Restrictions.ne("status", InwarehouseStatus.CANCEL));
    criteria.addOrder(Order.desc("gmtCreate"));
    return criteria;
}


}
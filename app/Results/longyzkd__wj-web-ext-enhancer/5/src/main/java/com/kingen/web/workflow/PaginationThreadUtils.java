package com.kingen.web.workflow;
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class PaginationThreadUtils {

 private  Logger logger;

 private  ThreadLocal<Pagination> pagination;


public void set(Pagination value){
    pagination.set(value);
}


public Pagination get(){
    return pagination.get();
}


public void clear(){
    pagination.remove();
}


public int[] setPage(Integer totalSum){
    Pagination pagination = PaginationThreadUtils.get();
    if (pagination == null) {
        pagination = new Pagination();
        PaginationThreadUtils.set(pagination);
        pagination.setCurrentPage(1);
    }
    if (pagination.getTotalSum() == 0) {
        pagination.setTotalSum(totalSum);
    }
    int firstResult = (pagination.getCurrentPage() - 1) * pagination.getPageNum();
    int maxResult = pagination.getPageNum();
    // 校验分页情况
    if (firstResult >= pagination.getTotalSum() || firstResult < 0) {
        firstResult = 0;
        pagination.setCurrentPage(1);
    }
    pagination.setFirstResult(firstResult);
    pagination.setMaxResult(maxResult);
    // 分页处理
    pagination.processTotalPage();
    return new int[] { firstResult, maxResult };
}


}
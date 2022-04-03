package com.zis.common.actiontemplate;
 import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zis.common.util.Page;
import com.zis.common.util.PaginationQueryUtil;
public class PaginationQueryAction extends ActionSupport{

 private  Integer pageNow;

 private  String pageSource;

 public  String PAGE_SOURCE_DEFAULT_VALUE;

 protected  Logger logger;


public String setActionUrl()


public String setActionUrlQueryCondition()


public void doBeforeQuery(){
}


public void doBeforeReturn(){
}


public String executeQuery(){
    try {
        if (pageNow == null || pageNow < 1) {
            pageNow = 1;
        }
        if (PAGE_SOURCE_DEFAULT_VALUE.equals(pageSource)) {
            preProcessGetRequestCHN();
        }
        doBeforeQuery();
        // 分页查询
        DetachedCriteria queryCondition = buildQueryCondition();
        int totalCount = PaginationQueryUtil.getTotalCount(queryCondition);
        Page page = Page.createPage(pageNow, Page.DEFAULT_PAGE_SIZE, totalCount);
        List<T> list = PaginationQueryUtil.paginationQuery(queryCondition, page);
        // 转换结果
        List resultList = transformResult(list);
        // 设置页面参数
        ActionContext context = ActionContext.getContext();
        context.put(setResultListLabel(), resultList);
        context.put("actionUrl", setActionUrl());
        context.put("queryCondition", setActionUrlQueryCondition());
        context.put("pageNow", pageNow);
        if (page.isHasPre()) {
            context.put("prePage", pageNow - 1);
        }
        if (page.isHasNext()) {
            context.put("nextPage", pageNow + 1);
        }
        // 设置其他页面参数
        doBeforeReturn();
        return SUCCESS;
    } catch (Exception e) {
        this.addActionError(e.getMessage());
        logger.error("查询临时导入记录时出错，" + e.getMessage(), e);
        return INPUT;
    }
}


public String setResultListLabel(){
    return "resultList";
}


public String getPageSource(){
    return pageSource;
}


public void setPageSource(String pageSource){
    this.pageSource = pageSource;
}


public void preProcessGetRequestCHN(){
}


public Integer getPageNow(){
    return pageNow;
}


public List transformResult(List<T> list){
    return list;
}


public void setPageNow(Integer pageNow){
    this.pageNow = pageNow;
}


public DetachedCriteria buildQueryCondition()


}
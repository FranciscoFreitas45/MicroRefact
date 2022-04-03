package com.zis.common.controllertemplate;
 import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.ui.ModelMap;
import com.zis.common.mvc.ext.WebHelper;
public class PaginationQueryController {

 public  String PAGE_SOURCE_DEFAULT_VALUE;

 protected  Logger logger;


public String getFailPage()


public String setResultListLabel(){
    return "resultList";
}


public String setActionUrl(HttpServletRequest request)


public Page<T> buildPageList(Specification<T> spec,Pageable page)


public void preProcessGetRequestCHN(HttpServletRequest request){
}


public String getSuccessPage(HttpServletRequest request)


public String setActionUrlQueryCondition(HttpServletRequest request)


public void doBeforeQuery(HttpServletRequest request){
}


@SuppressWarnings("rawtypes")
public List transformResult(List<T> list){
    return list;
}


public void doBeforeReturn(ModelMap context,HttpServletRequest request){
}


public Specification<T> buildQueryCondition(HttpServletRequest request)


@SuppressWarnings("rawtypes")
public String executeQuery(ModelMap context,HttpServletRequest request){
    try {
        doBeforeQuery(request);
        Specification<T> spec = buildQueryCondition(request);
        Pageable page = WebHelper.buildPageRequest(request);
        Page<T> plist = buildPageList(spec, page);
        // 分页查询
        List<T> list = plist.getContent();
        // 转换结果
        List resultList = transformResult(list);
        // 设置页面参数
        context.put(setResultListLabel(), resultList);
        context.put("actionUrl", setActionUrl(request));
        context.put("status", request.getParameter("status"));
        context.put("queryCondition", setActionUrlQueryCondition(request));
        context.put("page", page.getPageNumber());
        if (plist.hasPrevious()) {
            context.put("prePage", page.previousOrFirst().getPageNumber());
        }
        if (plist.hasNext()) {
            context.put("nextPage", page.next().getPageNumber());
        }
        // 设置其他页面参数
        doBeforeReturn(context, request);
        return getSuccessPage(request);
    } catch (Exception e) {
        context.put("actionError", e.getMessage());
        logger.error("查询临时导入记录时出错，" + e.getMessage(), e);
        return getFailPage();
    }
}


}
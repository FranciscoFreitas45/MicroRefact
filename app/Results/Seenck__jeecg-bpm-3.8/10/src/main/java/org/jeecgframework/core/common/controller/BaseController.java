package org.jeecgframework.core.common.controller;
 import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.interceptors.DateConvertEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import DTO.CommonService;
import DTO.CommonService;
@Controller
@RequestMapping("/baseController")
public class BaseController {


public List<?> pageBaseMethod(HttpServletRequest request,DetachedCriteria dc,CommonService commonService,int pageRow){
    // 当前页
    // 总条数
    // 总页数
    int currentPage = 1;
    int totalRow = 0;
    int totalPage = 0;
    // 获取当前页
    String str_currentPage = request.getParameter("str_currentPage");
    currentPage = str_currentPage == null || "".equals(str_currentPage) ? 1 : Integer.parseInt(str_currentPage);
    // 获取每页的条数
    String str_pageRow = request.getParameter("str_pageRow");
    pageRow = str_pageRow == null || "".equals(str_pageRow) ? pageRow : Integer.parseInt(str_pageRow);
    // 统计的总行数
    dc.setProjection(Projections.rowCount());
    totalRow = Integer.parseInt(commonService.findByDetached(dc).get(0).toString());
    totalPage = (totalRow + pageRow - 1) / pageRow;
    currentPage = currentPage < 1 ? 1 : currentPage;
    currentPage = currentPage > totalPage ? totalPage : currentPage;
    // 清空统计函数
    dc.setProjection(null);
    // dc.setResultTransformer(dc.DISTINCT_ROOT_ENTITY);
    List<?> list = commonService.pageList(dc, (currentPage - 1) * pageRow, pageRow);
    request.setAttribute("currentPage", currentPage);
    request.setAttribute("pageRow", pageRow);
    request.setAttribute("totalRow", totalRow);
    request.setAttribute("totalPage", totalPage);
    return list;
}


@InitBinder
public void initBinder(ServletRequestDataBinder binder){
    // SimpleDateFormat dateFormat = new SimpleDateFormat(
    // "yyyy-MM-dd hh:mm:ss");
    // binder.registerCustomEditor(Date.class, new CustomDateEditor(
    // dateFormat, true));
    binder.registerCustomEditor(Date.class, new DateConvertEditor());
}


public List<String> extractIdListByComma(String ids){
    List<String> result = new ArrayList<String>();
    if (StringUtils.hasText(ids)) {
        for (String id : ids.split(",")) {
            if (StringUtils.hasLength(id)) {
                result.add(id.trim());
            }
        }
    }
    return result;
}


}
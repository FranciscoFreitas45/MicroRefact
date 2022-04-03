package cn.maxcj.core.common.constant.factory;
 import cn.maxcj.core.common.constant.state.Order;
import cn.stylefeng.roses.core.util.HttpContext;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.plugins.Page;
import javax.servlet.http.HttpServletRequest;
public class PageFactory {


public Page<T> defaultPage(){
    HttpServletRequest request = HttpContext.getRequest();
    // 每页多少条数据
    int limit = Integer.valueOf(request.getParameter("limit"));
    // 每页的偏移量(本页当前有多少条)
    int offset = Integer.valueOf(request.getParameter("offset"));
    // 排序字段名称
    String sort = request.getParameter("sort");
    // asc或desc(升序或降序)
    String order = request.getParameter("order");
    if (ToolUtil.isEmpty(sort)) {
        Page<T> page = new Page<>((offset / limit + 1), limit);
        page.setOpenSort(false);
        return page;
    } else {
        Page<T> page = new Page<>((offset / limit + 1), limit, sort);
        if (Order.ASC.getDes().equals(order)) {
            page.setAsc(true);
        } else {
            page.setAsc(false);
        }
        return page;
    }
}


}
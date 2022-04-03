package org.live.common.page;
 import org.live.common.constants.PageConstants;
import org.live.common.constants.RequestPageConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import javax.servlet.http.HttpServletRequest;
public class PageUtils {


public PageRequest getPage4JqGrid(HttpServletRequest request){
    return getPage4JqGrid(request, false, null);
}


public JqGridModel<T> pageConvertJqGrid(Page<T> page){
    JqGridModel<T> model = new JqGridModel<T>();
    // 总记录数
    model.setRecords(page.getTotalElements());
    // 总页数
    model.setTotal(page.getTotalPages());
    // 数据
    model.setRows(page.getContent());
    // 当前页
    model.setPage(page.getNumber() + 1);
    return model;
}


}
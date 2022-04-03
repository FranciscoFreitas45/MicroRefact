package com.xwtec.xwserver.service.tool.impl;
 import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.xwtec.xwserver.constant.ConstantBusiKeys;
import com.xwtec.xwserver.constant.ConstantKeys;
import com.xwtec.xwserver.dao.tool.IDatabaseDao;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.pojo.Page;
import com.xwtec.xwserver.service.tool.IDatabaseService;
import com.xwtec.xwserver.util.DataSourceContextHolder;
@Service
public class DatabaseServiceImpl implements IDatabaseService{

@Resource
 private IDatabaseDao databaseDao;


public List<Map<String,Object>> getDataBySql(Page page){
    List<Map<String, Object>> datalist;
    try {
        // 数据源
        String dataResourseType = page.getSearchParam().get("dataResourseType");
        // 切换成前台传过来的数据源
        DataSourceContextHolder.switchTo(dataResourseType);
        datalist = databaseDao.getDataBySql(page);
        DataSourceContextHolder.clear();
        return datalist;
    } catch (Exception e) {
        // DataSourceContextHolder.clear();
        throw new SPTException(e);
    }
}


public boolean checkSql(ModelAndView modelAndView,Page page){
    // sql语句
    String sql = page.getSearchParam().get("querySql");
    // 数据源
    String dataResourseType = page.getSearchParam().get("dataResourseType");
    // 判断sql是否为空
    if (null == sql || "".equals(sql)) {
        // 是否报错 1为报错
        modelAndView.addObject("isError", ConstantKeys.WebKey.FAIL_NUMBER);
        modelAndView.addObject("ErrorMessage", ConstantBusiKeys.Tool.SQL_NOT_EMPTY);
        return true;
    }
    // 判断数据源是否存在
    if (null == dataResourseType) {
        // 是否报错 1为报错
        modelAndView.addObject("isError", ConstantKeys.WebKey.FAIL_NUMBER);
        modelAndView.addObject("ErrorMessage", "");
        return true;
    }
    // 过滤所有非查询语句
    if (!sql.toUpperCase().trim().startsWith("SELECT")) {
        // 是否报错 1为报错
        modelAndView.addObject("isError", ConstantKeys.WebKey.FAIL_NUMBER);
        modelAndView.addObject("ErrorMessage", ConstantBusiKeys.Tool.SQL_NOT_LEGITIMATE);
        return true;
    }
    return false;
}


}
package com.xwtec.xwserver.controller.tool;
 import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.xwtec.xwserver.constant.ConstantKeys;
import com.xwtec.xwserver.pojo.Page;
import com.xwtec.xwserver.service.tool.IDatabaseService;
@Controller
@RequestMapping("database")
public class DatabaseControllor {

@Resource
 private IDatabaseService databaseService;

@Resource
 private Map<String,Object> schemaMap;

 private  Logger logger;


@RequestMapping("querysql.action")
public ModelAndView querySQL(Page page,ModelAndView modelAndView){
    // 把获取的参数打印出来
    logger.info("QueryToolControllor.QuerySQLModel param:" + page.getSearchParam());
    // 数据源
    modelAndView.addObject("queryDataSource", schemaMap);
    // 查询返回数据
    List<Map<String, Object>> data = null;
    // 校验sql是否合法
    if (!databaseService.checkSql(modelAndView, page)) {
        try {
            // 执行sql查询功能
            data = databaseService.getDataBySql(page);
        } catch (Exception e) {
            // 是否报错 1为报错
            modelAndView.addObject("isError", ConstantKeys.WebKey.FAIL_NUMBER);
            modelAndView.addObject("ErrorMessage", e.getMessage());
            logger.debug("QueryToolControllor.QuerySQLModel error:" + e.getMessage());
        }
        // 判断返回的数据是否为空
        if (null != data && !data.isEmpty()) {
            modelAndView.addObject("data", data);
        } else {
            // 如果数据不为null就肯定是没数据
            if (null != data) {
                // 是否有数据 1为 没有数据
                modelAndView.addObject("isHaveData", ConstantKeys.WebKey.FAIL);
            }
        }
    }
    modelAndView.addObject("page", page);
    modelAndView.setViewName("/tool/database_list.jsp");
    return modelAndView;
}


@RequestMapping("init.action")
public ModelAndView init(Page page,ModelAndView modelAndView){
    // 把数据源传给前台
    modelAndView.addObject("queryDataSource", schemaMap);
    modelAndView.setViewName("/tool/database_list.jsp");
    return modelAndView;
}


}
package com.byr.warehouse.controller;
 import com.byr.warehouse.dao.CommonRepository;
import com.byr.warehouse.pojo.LogOperation;
import com.byr.warehouse.pojo.LogSystem;
import com.byr.warehouse.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;
import java.util.List;
@Controller
public class LogController {

 private Logger logger;

@Autowired
 private  CommonRepository<LogOperation> commonRepository;

@Autowired
 private  CommonRepository<LogSystem> commonRepository2;

@Resource
 private  JdbcTemplate jdbcTemplate;

 private  Integer pagesize;


@RequestMapping(value = "/log-system", method = { RequestMethod.GET, RequestMethod.POST })
public String getLogOperationList(LogSystem log,int pagenum,ModelMap modelMap){
    StringBuffer sql = null;
    try {
        sql = commonRepository2.getFiledValues(log, pagenum);
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    }
    sql.append(" 1 = 1 ORDER BY logDate DESC");
    int totalpage = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(LogSystem.class)).size();
    sql.append(" LIMIT " + (pagenum - 1) * pagesize + "," + pagesize);
    List<LogSystem> operations = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(LogSystem.class));
    logger.debug("获取操作日志" + operations);
    modelMap.addAttribute("operations", operations);
    modelMap.addAttribute("page", pagenum);
    modelMap.addAttribute("totalpage", PageUtil.getTotalPage(totalpage, pagesize));
    return "log_system";
}


}
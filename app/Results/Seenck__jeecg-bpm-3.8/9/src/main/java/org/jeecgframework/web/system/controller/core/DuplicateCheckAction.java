package org.jeecgframework.web.system.controller.core;
 import javax.servlet.http.HttpServletRequest;
import org.jeecgframework.web.system.pojo.base.DuplicateCheckPage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/duplicateCheckAction")
public class DuplicateCheckAction extends BaseController{

 private  Logger logger;

@Autowired
 private  JdbcDao jdbcDao;


@RequestMapping(params = "doDuplicateCheck")
@ResponseBody
public AjaxJson doDuplicateCheck(DuplicateCheckPage duplicateCheckPage,HttpServletRequest request){
    AjaxJson j = new AjaxJson();
    Long num = null;
    if (StringUtils.isNotBlank(duplicateCheckPage.getRowObid())) {
        // [2].编辑页面校验
        String sql = "SELECT count(*) FROM " + duplicateCheckPage.getTableName() + " WHERE " + duplicateCheckPage.getFieldName() + " =? and id != ?";
        num = jdbcDao.getCountForJdbcParam(sql, new Object[] { duplicateCheckPage.getFieldVlaue(), duplicateCheckPage.getRowObid() });
    } else {
        // [1].添加页面校验
        String sql = "SELECT count(*) FROM " + duplicateCheckPage.getTableName() + " WHERE " + duplicateCheckPage.getFieldName() + " =?";
        num = jdbcDao.getCountForJdbcParam(sql, new Object[] { duplicateCheckPage.getFieldVlaue() });
    }
    if (num == null || num == 0) {
        // 该值可用
        j.setSuccess(true);
        j.setMsg("该值可用！");
    } else {
        // 该值不可用
        j.setSuccess(false);
        j.setMsg("该值不可用，系统中已存在！");
    }
    return j;
}


}
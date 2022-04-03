package com.kingen.web;
 import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.alibaba.fastjson.JSONObject;
import com.kingen.aop.ControllerLogAnnotation;
import com.kingen.bean.Log;
import com.kingen.service.log.LogService;
import com.kingen.util.Json;
import com.kingen.util.JsonResultBuilder;
import com.kingen.util.Page;
import com.kingen.util.StringUtils;
import com.kingen.util.excel.ExportExcel;
import DTO.Json;
@Controller
@RequestMapping(value = "/log")
public class LogController extends CommonController{

 private  Logger logger;

@Autowired
 private  LogService service;


@ControllerLogAnnotation(moduleName = "日志管理", option = "导出")
@RequestMapping(value = "exportFile", method = RequestMethod.GET)
public void exportFile(Page<Log> page,Log vo,HttpServletResponse response){
    JSONObject jsonObject = new JSONObject();
    try {
        String fileName = "";
        if (StringUtils.isEmpty(vo.getFromDate())) {
            fileName = "日志（" + vo.getToDate() + "之前）.xlsx";
        } else {
            fileName = "日志（" + vo.getFromDate() + "至" + vo.getToDate() + "）.xlsx";
        }
        // page = service.getLogs(page,vo);
        // new ExportExcel("日志", Log.class).setDataList(page.getDataList()).write(response, fileName).dispose();
        List<Log> result = service.getLogs(vo);
        new ExportExcel("日志", Log.class).setDataList(result).write(response, fileName).dispose();
    // jsonObject = JsonResultBuilder.success(true).msg( "导出成功").json();
    } catch (Exception e) {
        jsonObject = JsonResultBuilder.success(false).msg("导出用户失败！失败信息：" + e.getMessage()).json();
        // 防止写两次responese
        writeJson(response, jsonObject);
    }
}


@RequestMapping(value = "listData")
public void listData(Page<Log> page,Log vo,HttpServletResponse response){
    page = service.getLogs(page, vo);
    writeJson(response, page);
}


@ControllerLogAnnotation(moduleName = "日志管理", option = "删除")
@RequestMapping(value = "deleteThem")
public void deleteThem(String[] ids,HttpServletResponse response){
    Json json = new Json();
    try {
        service.del(ids);
        json.setSuccess(true);
        json.setMsg("删除成功");
    } catch (Exception e) {
        json.setMsg("系统出错");
        logger.error(e.getMessage());
        e.printStackTrace();
    }
    writeJson(response, json);
}


@RequestMapping(value = "/")
@ControllerLogAnnotation(moduleName = "日志管理", option = "查看")
@RequiresPermissions("log:view")
public String execute(HttpServletResponse response){
    return "log/list";
}


}
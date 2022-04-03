package org.live.sys.controller;
 import org.live.common.page.JqGridModel;
import org.live.common.page.PageUtils;
import org.live.common.response.ResponseModel;
import org.live.common.response.SimpleResponseModel;
import org.live.common.systemlog.LogLevel;
import org.live.common.systemlog.OperateType;
import org.live.common.systemlog.SystemLog;
import org.live.sys.entity.Log;
import org.live.sys.service.LogService;
import org.live.sys.vo.LogVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("/sys")
public class LogController {

 private  Logger LOGGER;

@Resource
 private  LogService logService;


@SystemLog(description = "删除日志信息", logLevel = LogLevel.ERROR, operateType = OperateType.DELETE)
@RequestMapping(value = "/log", method = RequestMethod.PATCH)
@ResponseBody
public ResponseModel<Object> deleteLogInfo(String[] ids){
    ResponseModel<Object> model = new SimpleResponseModel<Object>();
    try {
        this.logService.deleteLogInfo(ids);
        model.success();
    } catch (DataIntegrityViolationException e) {
        model.error();
        LOGGER.error(e.getMessage(), e);
    } catch (Exception e) {
        model.error();
        LOGGER.error(e.getMessage(), e);
    }
    return model;
}


@SystemLog(description = "进入日志查看界面", operateType = OperateType.QUERY, logLevel = LogLevel.WARN)
@RequestMapping(value = "/log/page", method = RequestMethod.GET)
public String load(Model model){
    return "sys/log";
}


@RequestMapping(value = "/log", method = RequestMethod.GET)
@ResponseBody
public JqGridModel<Log> findLogInfoAll(HttpServletRequest request,LogVo logVo){
    PageRequest pageRequest = PageUtils.getPage4JqGrid(request);
    Page<Log> page = this.logService.findLogs(pageRequest, logVo);
    JqGridModel<Log> model = PageUtils.pageConvertJqGrid(page);
    return model;
}


}
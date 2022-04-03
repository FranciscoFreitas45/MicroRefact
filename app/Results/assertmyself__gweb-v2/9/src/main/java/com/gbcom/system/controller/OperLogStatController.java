package com.gbcom.system.controller;
 import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gbcom.system.aop.UserLog;
import com.gbcom.system.common.hchart.HChartResult;
import com.gbcom.system.common.hchart.HChartTransfer;
import com.gbcom.system.domain.SysLog;
import com.gbcom.system.manager.OperLogStatManager;
import com.gbcom.system.manager.UserLogStatManager;
import com.gbcom.system.utils.JsonUtil;
import com.hc.core.controller.BaseCRUDActionController;
@Controller
public class OperLogStatController extends BaseCRUDActionController<SysLog>{

 private  Logger logger;

@Autowired
 private  UserLogStatManager userLogStatManager;

@Autowired
 private  OperLogStatManager operLogStatManager;


@RequestMapping
public void statOperFoldLinePlot(HttpServletResponse response,String granularity){
    try {
        String resultData = operLogStatManager.statOperFoldLinePlot(granularity);
        sendSuccessJSON(response, "", resultData);
    } catch (Exception e) {
        logger.error("OperLogStat Exception：", e);
    }
}


@RequestMapping
@UserLog(eventType = UserLog.USERLOG_EVENTTYPE_QUERY)
public String init(Model model,String m,String type,HttpServletRequest request){
    // 判断是否有编辑权限
    model.addAttribute("type", type);
    return "view/system/operLogStat/chart";
}


@RequestMapping
public void statisticOperClick(HttpServletResponse response){
    try {
        String data = operLogStatManager.statOperClick();
        sendSuccessJSON(response, "", data);
    } catch (Exception e) {
        log.error("oper OperClick Exception:", e);
    }
}


@RequestMapping
public void statisticMenuClick(HttpServletResponse response){
    try {
        String data = operLogStatManager.statMenuClick();
        sendSuccessJSON(response, "", data);
    } catch (Exception e) {
        log.error("oper MenuClick Exception:", e);
    }
}


@RequestMapping
public void statisticResult(HttpServletResponse response){
    try {
        String data = operLogStatManager.statResult();
        sendSuccessJSON(response, "", data);
    } catch (Exception e) {
        logger.error("oper Result Exception：", e);
    }
}


@RequestMapping
@UserLog(eventType = UserLog.USERLOG_EVENTTYPE_QUERY)
public void hcStatLogType(HttpServletResponse response,String gate,String startDate,String endDate,String granularity,String method){
    try {
        List<Map<String, Object>> list = userLogStatManager.statLogType();
        HChartResult rst = new HChartTransfer().transferHchartData(list);
        sendJSON(response, JsonUtil.beanToJSON(rst));
    } catch (Exception e) {
        logger.error("hchartData" + e.getMessage(), e);
        HChartResult rst = new HChartResult();
        rst.setErrno(HChartResult.RST_FAILED);
        rst.setErrmsg(e.getMessage());
        sendJSON(response, JsonUtil.beanToJSON(rst));
    }
}


}
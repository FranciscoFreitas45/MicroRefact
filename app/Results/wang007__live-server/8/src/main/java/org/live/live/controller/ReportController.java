package org.live.live.controller;
 import org.live.common.response.DataTableModel;
import org.live.common.response.ResponseModel;
import org.live.common.response.SimpleResponseModel;
import org.live.common.utils.DataTableUtils;
import org.live.live.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import org.live.DTO.ResponseModel;
import org.live.DTO.ResponseModel;
import org.live.DTO.ResponseModel;
@Controller
@RequestMapping("/live")
public class ReportController {

@Autowired
 private  ReportService reportService;

 private  Logger LOGGER;


@RequestMapping(value = "/report", method = RequestMethod.PUT)
@ResponseBody
public ResponseModel handlerReport(String id){
    ResponseModel model = new SimpleResponseModel();
    try {
        reportService.updateReport(id);
        model.success();
    } catch (Exception e) {
        model.error();
        model.setMessage(e.getMessage());
    }
    return model;
}


@RequestMapping("/report/page")
public String toReportPage(){
    return "live/report";
}


@RequestMapping("/report/page2")
public String toReportHandledPage(){
    return "/live/report_handled";
}


@RequestMapping(value = "/report/data", method = RequestMethod.POST)
@ResponseBody
public DataTableModel getData(HttpServletRequest request){
    Map<String, Object> params = DataTableUtils.parseMap(request);
    // 查询记录
    return reportService.findReportByPage(params, false);
}


@RequestMapping(value = "/report/handled/data", method = RequestMethod.POST)
@ResponseBody
public DataTableModel getData2(HttpServletRequest request){
    Map<String, Object> params = DataTableUtils.parseMap(request);
    // 查询记录
    return reportService.findReportByPage(params, true);
}


}
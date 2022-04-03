package org.live.live.controller;
 import org.live.common.response.DataTableModel;
import org.live.common.utils.DataTableUtils;
import org.live.live.service.LiveRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("/live")
public class LiveRecordController {

 private  String MODULE;

 private  Logger LOGGER;

@Autowired
 private  LiveRecordService liveRecordService;


@RequestMapping(value = "/liveRecord/data", method = RequestMethod.POST)
@ResponseBody
public DataTableModel data(HttpServletRequest request){
    return liveRecordService.findLiveRecordByPage(DataTableUtils.parseMap(request));
}


@RequestMapping(value = "/liveRecord/page")
public String page(){
    return MODULE + "/live_record";
}


}
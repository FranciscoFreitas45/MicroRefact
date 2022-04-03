package cn.maxcj.modular.system.controller;
 import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import cn.maxcj.modular.system.service.IActivityStatisticsService;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/activityStatistics")
public class ActivityStatisticsController extends BaseController{

 private  String PREFIX;

@Autowired
 private  IActivityStatisticsService activityStatisticsService;


@RequestMapping(value = "/getweekynum")
@ResponseBody
public List<Map<String,Object>> getWeekActivityNum(){
    return activityStatisticsService.weekActivityNum();
}


@RequestMapping(value = "/list")
@ResponseBody
public Object list(String condition){
    return activityStatisticsService.activityNum();
}


@RequestMapping(value = "/getview")
@ResponseBody
public Map<String,Integer> getview(){
    return activityStatisticsService.getview();
}


@RequestMapping(value = "/kettle")
@ResponseBody
public Object kettle(String condition){
    return activityStatisticsService.selectList(null);
}


}
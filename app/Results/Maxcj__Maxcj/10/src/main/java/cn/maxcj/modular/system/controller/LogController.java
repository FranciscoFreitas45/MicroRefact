package cn.maxcj.modular.system.controller;
 import cn.hutool.core.bean.BeanUtil;
import cn.maxcj.core.common.annotion.BussinessLog;
import cn.maxcj.core.common.annotion.Permission;
import cn.maxcj.core.common.constant.Const;
import cn.maxcj.core.common.constant.factory.PageFactory;
import cn.maxcj.core.common.constant.state.BizLogType;
import cn.maxcj.core.common.page.PageInfoBT;
import cn.maxcj.modular.system.model.OperationLog;
import cn.maxcj.modular.system.service.IOperationLogService;
import cn.maxcj.modular.system.warpper.LogWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.mapper.SqlRunner;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/log")
public class LogController extends BaseController{

 private  String PREFIX;

@Autowired
 private  IOperationLogService operationLogService;


@RequestMapping("")
public String index(){
    return PREFIX + "log.html";
}


@RequestMapping("/detail/{id}")
@Permission(Const.ADMIN_NAME)
@ResponseBody
public Object detail(Integer id){
    OperationLog operationLog = operationLogService.selectById(id);
    Map<String, Object> stringObjectMap = BeanUtil.beanToMap(operationLog);
    return super.warpObject(new LogWarpper(stringObjectMap));
}


@RequestMapping("/list")
@Permission(Const.ADMIN_NAME)
@ResponseBody
public Object list(String beginTime,String endTime,String logName,Integer logType){
    Page<OperationLog> page = new PageFactory<OperationLog>().defaultPage();
    List<Map<String, Object>> result = operationLogService.getOperationLogs(page, beginTime, endTime, logName, BizLogType.valueOf(logType), page.getOrderByField(), page.isAsc());
    page.setRecords(new LogWarpper(result).wrap());
    return new PageInfoBT<>(page);
}


@BussinessLog(value = "清空业务日志")
@RequestMapping("/delLog")
@Permission(Const.ADMIN_NAME)
@ResponseBody
public Object delLog(){
    SqlRunner.db().delete("delete from sys_operation_log");
    return SUCCESS_TIP;
}


}
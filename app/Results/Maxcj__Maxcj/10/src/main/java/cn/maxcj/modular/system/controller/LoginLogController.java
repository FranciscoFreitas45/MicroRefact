package cn.maxcj.modular.system.controller;
 import cn.maxcj.core.common.annotion.BussinessLog;
import cn.maxcj.core.common.annotion.Permission;
import cn.maxcj.core.common.constant.Const;
import cn.maxcj.core.common.constant.factory.PageFactory;
import cn.maxcj.core.common.page.PageInfoBT;
import cn.maxcj.modular.system.model.LoginLog;
import cn.maxcj.modular.system.service.ILoginLogService;
import cn.maxcj.modular.system.warpper.LogWarpper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.mapper.SqlRunner;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/loginLog")
public class LoginLogController extends BaseController{

 private  String PREFIX;

@Autowired
 private  ILoginLogService loginLogService;


@RequestMapping("")
public String index(){
    return PREFIX + "login_log.html";
}


@RequestMapping("/list")
@Permission(Const.ADMIN_NAME)
@ResponseBody
public Object list(String beginTime,String endTime,String logName){
    Page<LoginLog> page = new PageFactory<LoginLog>().defaultPage();
    List<Map<String, Object>> result = loginLogService.getLoginLogs(page, beginTime, endTime, logName, page.getOrderByField(), page.isAsc());
    page.setRecords(new LogWarpper(result).wrap());
    return new PageInfoBT<>(page);
}


@BussinessLog("清空登录日志")
@RequestMapping("/delLoginLog")
@Permission(Const.ADMIN_NAME)
@ResponseBody
public Object delLog(){
    SqlRunner.db().delete("delete from sys_login_log");
    return SUCCESS_TIP;
}


}
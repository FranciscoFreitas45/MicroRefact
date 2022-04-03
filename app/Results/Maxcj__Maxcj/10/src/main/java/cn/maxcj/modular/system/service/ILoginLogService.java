package cn.maxcj.modular.system.service;
 import cn.maxcj.modular.system.model.LoginLog;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import java.util.List;
import java.util.Map;
public interface ILoginLogService extends IService<LoginLog>{


public List<Map<String,Object>> getLoginLogs(Page<LoginLog> page,String beginTime,String endTime,String logName,String orderByField,boolean asc)
;

}
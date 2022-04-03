package cn.maxcj.modular.system.service;
 import cn.maxcj.modular.system.model.OperationLog;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import java.util.List;
import java.util.Map;
public interface IOperationLogService extends IService<OperationLog>{


public List<Map<String,Object>> getOperationLogs(Page<OperationLog> page,String beginTime,String endTime,String logName,String s,String orderByField,boolean asc)
;

}
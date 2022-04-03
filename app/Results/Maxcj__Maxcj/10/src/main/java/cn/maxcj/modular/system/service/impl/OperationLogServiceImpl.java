package cn.maxcj.modular.system.service.impl;
 import cn.maxcj.modular.system.dao.OperationLogMapper;
import cn.maxcj.modular.system.model.OperationLog;
import cn.maxcj.modular.system.service.IOperationLogService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog>implements IOperationLogService{


@Override
public List<Map<String,Object>> getOperationLogs(Page<OperationLog> page,String beginTime,String endTime,String logName,String s,String orderByField,boolean asc){
    return this.baseMapper.getOperationLogs(page, beginTime, endTime, logName, s, orderByField, asc);
}


}
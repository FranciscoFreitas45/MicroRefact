package cn.maxcj.modular.system.service.impl;
 import cn.maxcj.modular.system.dao.LoginLogMapper;
import cn.maxcj.modular.system.model.LoginLog;
import cn.maxcj.modular.system.service.ILoginLogService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog>implements ILoginLogService{


@Override
public List<Map<String,Object>> getLoginLogs(Page<LoginLog> page,String beginTime,String endTime,String logName,String orderByField,boolean asc){
    return this.baseMapper.getLoginLogs(page, beginTime, endTime, logName, orderByField, asc);
}


}
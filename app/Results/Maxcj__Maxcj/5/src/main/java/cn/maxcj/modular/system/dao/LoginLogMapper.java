package cn.maxcj.modular.system.dao;
 import cn.maxcj.modular.system.model.LoginLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
public interface LoginLogMapper extends BaseMapper<LoginLog>{


public List<Map<String,Object>> getLoginLogs(Page<LoginLog> page,String beginTime,String endTime,String logName,String orderByField,boolean isAsc)
;

}
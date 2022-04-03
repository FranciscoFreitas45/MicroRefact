package cn.maxcj.modular.system.dao;
 import cn.maxcj.modular.system.model.OperationLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
public interface OperationLogMapper extends BaseMapper<OperationLog>{


public List<Map<String,Object>> getOperationLogs(Page<OperationLog> page,String beginTime,String endTime,String logName,String logType,String orderByField,boolean isAsc)
;

}
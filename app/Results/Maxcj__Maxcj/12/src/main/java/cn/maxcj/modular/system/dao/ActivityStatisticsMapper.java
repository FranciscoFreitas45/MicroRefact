package cn.maxcj.modular.system.dao;
 import cn.maxcj.modular.system.model.ActivityStatistics;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
public interface ActivityStatisticsMapper extends BaseMapper<ActivityStatistics>{


public List<Map<String,Object>> activityNum()
;

public List<ActivityStatistics> list(Integer deptid)
;

public List<Map<String,Object>> weekActivityNum()
;

public Map<String,Integer> getview()
;

}
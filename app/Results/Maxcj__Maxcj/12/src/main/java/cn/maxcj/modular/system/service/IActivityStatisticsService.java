package cn.maxcj.modular.system.service;
 import cn.maxcj.modular.system.model.ActivityStatistics;
import com.baomidou.mybatisplus.service.IService;
import java.util.List;
import java.util.Map;
public interface IActivityStatisticsService extends IService<ActivityStatistics>{


public List<Map<String,Object>> activityNum()
;

public List<ActivityStatistics> list(Integer deptid)
;

public List<Map<String,Object>> weekActivityNum()
;

public Map<String,Integer> getview()
;

}
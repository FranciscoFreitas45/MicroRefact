package cn.maxcj.modular.system.service.impl;
 import cn.maxcj.modular.system.model.ActivityStatistics;
import cn.maxcj.modular.system.dao.ActivityStatisticsMapper;
import cn.maxcj.modular.system.service.IActivityStatisticsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service
public class ActivityStatisticsServiceImpl extends ServiceImpl<ActivityStatisticsMapper, ActivityStatistics>implements IActivityStatisticsService{

@Resource
 private  ActivityStatisticsMapper activityStatisticsMapper;


@Override
public List<Map<String,Object>> activityNum(){
    return activityStatisticsMapper.activityNum();
}


@Override
public List<ActivityStatistics> list(Integer deptid){
    return null;
}


@Override
public List<Map<String,Object>> weekActivityNum(){
    return activityStatisticsMapper.weekActivityNum();
}


@Override
public Map<String,Integer> getview(){
    return activityStatisticsMapper.getview();
}


}
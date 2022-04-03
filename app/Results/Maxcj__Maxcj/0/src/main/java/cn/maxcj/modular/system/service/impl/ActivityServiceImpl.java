package cn.maxcj.modular.system.service.impl;
 import cn.maxcj.core.common.node.ZTreeNode;
import cn.maxcj.modular.system.model.Activity;
import cn.maxcj.modular.system.dao.ActivityMapper;
import cn.maxcj.modular.system.service.IActivityService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity>implements IActivityService{

@Resource
 private  ActivityMapper activityMapper;


@Override
public Map<Integer,Integer> club_activityNum(String condition){
    return this.baseMapper.club_activityNum(condition);
}


@Override
public List<Activity> clublist(Integer deptid,String condition){
    return activityMapper.clublist(deptid, condition);
}


@Override
public List<Map<String,Object>> activity_apply(String condition,String activity_category,String beginTime){
    return this.baseMapper.activity_apply(condition, activity_category, beginTime);
}


@Override
public List<Map<String,Object>> activity_club(Integer deptid,String condition){
    return activityMapper.activity_club(deptid, condition);
}


@Override
public List<Map<String,Object>> activity_list(String condition,String activity_category,String beginTime){
    return this.baseMapper.activity_list(condition, activity_category, beginTime);
}


@Override
public List<Map<String,Object>> tree(Integer deptId){
    return activityMapper.tree(deptId);
}


@Override
public List<Map<String,Object>> activity_clublist(Integer deptid,String condition){
    return activityMapper.activity_clublist(deptid, condition);
}


@Override
public List<Map<String,Object>> activity_history(String condition,String activity_category,String beginTime,Integer state){
    return this.baseMapper.activity_history(condition, activity_category, beginTime, state);
}


}
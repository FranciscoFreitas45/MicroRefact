package cn.maxcj.modular.system.service;
 import cn.maxcj.core.common.node.ZTreeNode;
import cn.maxcj.modular.system.model.Activity;
import com.baomidou.mybatisplus.service.IService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public interface IActivityService extends IService<Activity>{


public Map<Integer,Integer> club_activityNum(String condition)
;

public List<Activity> clublist(Integer deptid,String condition)
;

public List<Map<String,Object>> activity_apply(String condition,String activity_category,String beginTime)
;

public List<Map<String,Object>> activity_club(Integer deptid,String condition)
;

public List<Map<String,Object>> activity_list(String condition,String activity_category,String beginTime)
;

public List<Map<String,Object>> tree(Integer deptId)
;

public List<Map<String,Object>> activity_clublist(Integer deptid,String condition)
;

public List<Map<String,Object>> activity_history(String condition,String activity_category,String beginTime,Integer state)
;

}
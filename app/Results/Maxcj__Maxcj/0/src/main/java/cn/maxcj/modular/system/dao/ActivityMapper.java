package cn.maxcj.modular.system.dao;
 import cn.maxcj.core.common.node.ZTreeNode;
import cn.maxcj.modular.system.model.Activity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public interface ActivityMapper extends BaseMapper<Activity>{


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

public String getActivityName(Integer activity_id)
;

public List<Map<String,Object>> tree(Integer deptId)
;

public List<Map<String,Object>> activity_clublist(Integer deptid,String condition)
;

public List<Map<String,Object>> activity_history(String condition,String activity_category,String beginTime,Integer state)
;

}
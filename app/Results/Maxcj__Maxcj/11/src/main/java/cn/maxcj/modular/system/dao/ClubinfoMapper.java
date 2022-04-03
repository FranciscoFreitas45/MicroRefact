package cn.maxcj.modular.system.dao;
 import cn.maxcj.modular.system.model.Clubinfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public interface ClubinfoMapper extends BaseMapper<Clubinfo>{


public List<Map<String,Object>> queryClubInfo(Integer deptid)
;

public Clubinfo getClubInfoByDeptid(Integer deptid)
;

public void init(Integer deptid)
;

}
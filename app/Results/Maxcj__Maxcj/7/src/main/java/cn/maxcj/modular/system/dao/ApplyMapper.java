package cn.maxcj.modular.system.dao;
 import cn.maxcj.modular.system.model.Apply;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
public interface ApplyMapper extends BaseMapper<Apply>{


public List<Apply> applys(Integer deptid)
;

public Apply apply_exist(Integer userid)
;

public List<Map<String,Object>> listByDeptid(Integer deptid,String con)
;

public List<Map<String,Object>> list(String con)
;

}
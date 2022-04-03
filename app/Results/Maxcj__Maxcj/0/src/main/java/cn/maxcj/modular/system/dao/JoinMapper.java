package cn.maxcj.modular.system.dao;
 import cn.maxcj.modular.system.model.Join;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
public interface JoinMapper extends BaseMapper<Join>{


public List<Map<String,Object>> myJoin(Integer userid,String condition)
;

}
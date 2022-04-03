package cn.maxcj.modular.system.dao;
 import cn.maxcj.modular.system.model.Dict;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
public interface DictMapper extends BaseMapper<Dict>{


public List<Dict> selectByCode(String code)
;

public List<Map<String,Object>> list(String conditiion)
;

public List<Dict> selectByParentCode(String code)
;

}
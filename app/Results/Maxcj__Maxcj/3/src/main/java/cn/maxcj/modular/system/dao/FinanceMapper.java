package cn.maxcj.modular.system.dao;
 import cn.maxcj.modular.system.model.Finance;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
public interface FinanceMapper extends BaseMapper<Finance>{


public List<Map<String,Object>> getHistory(String condition,Integer category,Integer state)
;

public List<Map<String,Object>> getClubFinance(String condition,Integer category)
;

public List<Map<String,Object>> getMyClubFinance(Integer category,Integer deptid)
;

}
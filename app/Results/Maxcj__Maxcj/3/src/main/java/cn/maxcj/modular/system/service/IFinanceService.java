package cn.maxcj.modular.system.service;
 import cn.maxcj.modular.system.model.Finance;
import com.baomidou.mybatisplus.service.IService;
import io.swagger.models.auth.In;
import java.util.List;
import java.util.Map;
public interface IFinanceService extends IService<Finance>{


public List<Map<String,Object>> getHistory(String condition,Integer category,Integer state)
;

public List<Map<String,Object>> getClubFinance(String condition,Integer category)
;

public List<Map<String,Object>> getMyClubFinance(Integer condition,Integer deptid)
;

}
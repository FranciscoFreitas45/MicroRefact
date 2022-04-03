package cn.maxcj.modular.system.service;
 import cn.maxcj.modular.system.model.Apply;
import com.baomidou.mybatisplus.service.IService;
import java.util.List;
import java.util.Map;
public interface IApplyService extends IService<Apply>{


public List<Apply> applys(Integer deptid)
;

public boolean apply_exist(Integer userid)
;

public List<Map<String,Object>> list(Integer deptid,String con)
;

}
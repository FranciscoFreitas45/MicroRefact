package cn.maxcj.modular.system.service;
 import cn.maxcj.modular.system.model.Join;
import com.baomidou.mybatisplus.service.IService;
import java.util.List;
import java.util.Map;
public interface IJoinService extends IService<Join>{


public List<Map<String,Object>> myJoin(Integer userid,String condition)
;

}
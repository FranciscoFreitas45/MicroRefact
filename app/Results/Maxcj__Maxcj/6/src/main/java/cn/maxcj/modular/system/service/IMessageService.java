package cn.maxcj.modular.system.service;
 import cn.maxcj.modular.system.model.Message;
import com.baomidou.mybatisplus.service.IService;
import java.util.List;
import java.util.Map;
public interface IMessageService extends IService<Message>{


public List<Map<String,Object>> list(String condition)
;

}
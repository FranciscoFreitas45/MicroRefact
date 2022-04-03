package cn.maxcj.modular.system.dao;
 import cn.maxcj.modular.system.model.Message;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
public interface MessageMapper extends BaseMapper<Message>{


public List<Map<String,Object>> message_list(String condition)
;

}
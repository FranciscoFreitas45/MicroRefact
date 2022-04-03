package cn.maxcj.modular.system.dao;
 import cn.maxcj.modular.system.model.Notice;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;
public interface NoticeMapper extends BaseMapper<Notice>{


public List<Map<String,Object>> clublist(Integer deptId)
;

public Integer noticeNum()
;

public List<Map<String,Object>> list(String condition)
;

}
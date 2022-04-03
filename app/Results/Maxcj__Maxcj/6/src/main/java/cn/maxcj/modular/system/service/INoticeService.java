package cn.maxcj.modular.system.service;
 import cn.maxcj.modular.system.model.Notice;
import com.baomidou.mybatisplus.service.IService;
import java.util.List;
import java.util.Map;
public interface INoticeService extends IService<Notice>{


public List<Map<String,Object>> club_list(Integer deptId)
;

public Integer noticNum()
;

public List<Map<String,Object>> list(String condition)
;

}
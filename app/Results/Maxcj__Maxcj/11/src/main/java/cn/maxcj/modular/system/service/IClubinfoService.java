package cn.maxcj.modular.system.service;
 import cn.maxcj.modular.system.dao.ClubinfoMapper;
import cn.maxcj.modular.system.model.Clubinfo;
import com.baomidou.mybatisplus.service.IService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public interface IClubinfoService extends IService<Clubinfo>{


public List<Map<String,Object>> queryClubInfo(Integer deptid)
;

public void init(Integer deptid)
;

public Clubinfo getClubInfoByDeptid(Integer deptid)
;

}
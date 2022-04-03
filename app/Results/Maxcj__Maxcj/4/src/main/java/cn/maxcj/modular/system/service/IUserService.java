package cn.maxcj.modular.system.service;
 import cn.maxcj.modular.system.model.User;
import cn.stylefeng.roses.core.datascope.DataScope;
import com.baomidou.mybatisplus.service.IService;
import java.util.List;
import java.util.Map;
public interface IUserService extends IService<User>{


public int changePwd(Integer userId,String pwd)
;

public List<Map<String,Object>> selectUsersbydeptid(Integer deptid)
;

public List<Map<String,Object>> selectUsers(DataScope dataScope,String name,String beginTime,String endTime,Integer deptid)
;

public List<Map<String,Object>> selectSheLian()
;

public Integer isSheLian(Integer userId)
;

public User getByAccount(String account)
;

public int setRoles(Integer userId,String roleIds)
;

public int setStatus(Integer userId,int status)
;

}
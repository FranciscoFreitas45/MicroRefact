package cn.maxcj.modular.system.service.impl;
 import cn.maxcj.modular.system.dao.UserMapper;
import cn.maxcj.modular.system.model.User;
import cn.maxcj.modular.system.service.IUserService;
import cn.stylefeng.roses.core.datascope.DataScope;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>implements IUserService{


@Override
public int changePwd(Integer userId,String pwd){
    return this.baseMapper.changePwd(userId, pwd);
}


@Override
public List<Map<String,Object>> selectUsersbydeptid(Integer deptid){
    return this.baseMapper.selectUsersbydeptid(deptid);
}


@Override
public List<Map<String,Object>> selectUsers(DataScope dataScope,String name,String beginTime,String endTime,Integer deptid){
    return this.baseMapper.selectUsers(dataScope, name, beginTime, endTime, deptid);
}


@Override
public List<Map<String,Object>> selectSheLian(){
    return this.baseMapper.selectSheLian();
}


@Override
public Integer isSheLian(Integer userId){
    return this.baseMapper.isSheLian(userId);
}


@Override
public User getByAccount(String account){
    return this.baseMapper.getByAccount(account);
}


@Override
public int setRoles(Integer userId,String roleIds){
    return this.baseMapper.setRoles(userId, roleIds);
}


@Override
public int setStatus(Integer userId,int status){
    return this.baseMapper.setStatus(userId, status);
}


}
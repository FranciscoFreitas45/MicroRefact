package net.shangtech.weixin.sys.service;
 import net.shangtech.ssh.core.base.BaseDao;
import net.shangtech.ssh.core.base.BaseService;
import net.shangtech.weixin.sys.dao.SysUserDao;
import net.shangtech.weixin.sys.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.shangtech.Interface.SysUserDao;
@Service
@Transactional
public class SysUserService extends BaseService<SysUser>{

@Autowired
 private  SysUserDao dao;


public SysUser findByOpenid(String openid){
    return dao.findUnique("where openid=?", openid);
}


@Override
public BaseDao<SysUser> dao(){
    return dao;
}


public SysUser findByUsername(String username){
    return dao.findUnique("where username=?", username);
}


public SysUser findByAppid(String appid){
    return dao.findUnique("where appid=?", appid);
}


public void saveBasic(SysUser user){
    SysUser o = dao.find(user.getId());
    o.setAppid(user.getAppid());
    o.setAppkey(user.getAppkey());
    o.setSiteLogo(user.getSiteLogo());
    o.setSiteTel(user.getSiteTel());
    o.setWxName(user.getWxName());
    o.setWxNumber(user.getWxNumber());
    dao.update(o);
}


public SysUser findByToken(String token){
    return dao.findUnique("where token=?", token);
}


}
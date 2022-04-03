package com.gbcom.system.daoservice;
 import java.util.List;
import com.gbcom.system.domain.SysUser;
import com.hc.core.orm.hibernate.EntityService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SysUserService extends EntityService<SysUser, Long>{


public SysUser findByName(String name){
    String hql = "from SysUser as s where s.loginName = ?";
    /*Query query = getSession().createQuery(hql);
		query.setString(0, name);
		List<SysUser> sysUsers = query.list();*/
    List<SysUser> sysUsers = find(hql, new Object[] { name });
    if (sysUsers == null || sysUsers.size() == 0) {
        return null;
    }
    return sysUsers.get(0);
}


@Autowired
public void setSessionFactory(SessionFactory sessionFactory){
    initDao(sessionFactory, SysUser.class);
}


}
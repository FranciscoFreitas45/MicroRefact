package com.gbcom.system.daoservice;
 import com.gbcom.system.domain.SysUserRole;
import com.hc.core.orm.hibernate.EntityService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SysUserRoleService extends EntityService<SysUserRole, Long>{


@Autowired
public void setSessionFactory(SessionFactory sessionFactory){
    initDao(sessionFactory, SysUserRole.class);
}


}
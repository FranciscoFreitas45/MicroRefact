package com.gbcom.system.daoservice;
 import com.gbcom.system.domain.SysPersonDept;
import com.hc.core.orm.hibernate.EntityService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SysPersonDeptService extends EntityService<SysPersonDept, Long>{


@Autowired
public void setSessionFactory(SessionFactory sessionFactory){
    initDao(sessionFactory, SysPersonDept.class);
}


}
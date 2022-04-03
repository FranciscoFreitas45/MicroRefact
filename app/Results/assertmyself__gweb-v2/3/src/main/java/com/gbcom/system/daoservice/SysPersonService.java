package com.gbcom.system.daoservice;
 import com.gbcom.system.domain.SysPerson;
import com.hc.core.orm.hibernate.EntityService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SysPersonService extends EntityService<SysPerson, Long>{


@Autowired
public void setSessionFactory(SessionFactory sessionFactory){
    initDao(sessionFactory, SysPerson.class);
}


}
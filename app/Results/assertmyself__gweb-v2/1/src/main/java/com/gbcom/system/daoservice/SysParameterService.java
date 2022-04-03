package com.gbcom.system.daoservice;
 import com.gbcom.system.domain.SysParameter;
import com.hc.core.orm.hibernate.EntityService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SysParameterService extends EntityService<SysParameter, Long>{


@Autowired
public void setSessionFactory(SessionFactory sessionFactory){
    initDao(sessionFactory, SysParameter.class);
}


}
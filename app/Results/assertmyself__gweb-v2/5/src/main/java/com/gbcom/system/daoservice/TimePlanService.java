package com.gbcom.system.daoservice;
 import com.gbcom.system.domain.TimePlan;
import com.hc.core.orm.hibernate.EntityService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TimePlanService extends EntityService<TimePlan, Long>{


@Autowired
public void setSessionFactory(SessionFactory sessionFactory){
    initDao(sessionFactory, TimePlan.class);
}


}
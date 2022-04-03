package com.gbcom.system.daoservice;
 import com.gbcom.system.domain.SysCodeDetail;
import com.hc.core.orm.hibernate.EntityService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SysCodeDetailService extends EntityService<SysCodeDetail, Long>{


@Autowired
public void setSessionFactory(SessionFactory sessionFactory){
    initDao(sessionFactory, SysCodeDetail.class);
}


}
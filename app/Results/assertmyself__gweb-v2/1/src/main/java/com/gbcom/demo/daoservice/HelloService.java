package com.gbcom.demo.daoservice;
 import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gbcom.demo.domain.Hello;
import com.hc.core.orm.hibernate.EntityService;
@Service
public class HelloService extends EntityService<Hello, Long>{


@Autowired
public void setSessionFactory(SessionFactory sessionFactory){
    initDao(sessionFactory, Hello.class);
}


}
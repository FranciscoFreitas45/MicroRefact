package com.gbcom.system.daoservice;
 import java.util.List;
import com.gbcom.system.domain.SysArea;
import com.hc.core.orm.hibernate.EntityService;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SysAreaService extends EntityService<SysArea, Long>{


@Autowired
public void setSessionFactory(SessionFactory sessionFactory){
    initDao(sessionFactory, SysArea.class);
}


public List<SysArea> findByLayer(Long layer){
    /*
		 * String hql = "from SysArea a where a.layer=?"; List<SysArea> list =
		 * find(hql, new Object []{layer}); return list;
		 */
    Criteria cri = getSession().createCriteria(SysArea.class);
    return cri.add(Restrictions.eq("layer", layer)).addOrder(Order.asc("id")).list();
}


}
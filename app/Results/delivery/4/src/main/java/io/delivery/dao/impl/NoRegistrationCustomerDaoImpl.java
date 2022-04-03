package io.delivery.dao.impl;
 import io.delivery.dao.NoRegistrationCustomerDao;
import io.delivery.entity.NoRegistrationCustomer;
import java.util.List;
public class NoRegistrationCustomerDaoImpl extends BasicDaoImpl<NoRegistrationCustomer>implements NoRegistrationCustomerDao{


@Override
public List<NoRegistrationCustomer> findByName(String name){
    return (List<NoRegistrationCustomer>) sessionFactory.getCurrentSession().createQuery("from NoRegistrationCustomer as nreg where nreg.name = ?").setParameter(0, name).list();
}


}
package io.delivery.dao;
 import io.delivery.entity.NoRegistrationCustomer;
import java.util.List;
public interface NoRegistrationCustomerDao extends BasicDao<NoRegistrationCustomer>{


public List<NoRegistrationCustomer> findByName(String name)
;

}
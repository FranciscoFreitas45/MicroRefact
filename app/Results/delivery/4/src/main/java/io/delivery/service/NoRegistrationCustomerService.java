package io.delivery.service;
 import io.delivery.entity.NoRegistrationCustomer;
import java.util.List;
public interface NoRegistrationCustomerService {


public List<NoRegistrationCustomer> getNoRegistrationCustomerList()
;

public NoRegistrationCustomer findById(long id)
;

public List<NoRegistrationCustomer> findByName(String name)
;

public NoRegistrationCustomer create(NoRegistrationCustomer noRegistrationCustomer)
;

public NoRegistrationCustomer update(NoRegistrationCustomer noRegistrationCustomer)
;

public NoRegistrationCustomer deleteNoRegistrationCustomer(long id)
;

}
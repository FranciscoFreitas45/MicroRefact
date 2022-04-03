package io.swagger.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.repository.AddressRepository;
import io.swagger.model.Address;
@Service
public class AddressUserService {

@Autowired
 private AddressRepository addressrepository;


public List<Address> getAddress(Long id){
return addressrepository.getAddress(id);
}


public void setAddress(Long id,List<Address> address){
addressrepository.setAddress(id,address);
}


}
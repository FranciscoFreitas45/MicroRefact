package io.swagger.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.repository.AddressRepository;
import io.swagger.model.Address;
@Service
public class AddressMedicService {

@Autowired
 private AddressRepository addressrepository;


public void setAddress(Long id,List<Address> address){
addressrepository.setAddress(id,address);
}


public List<Address> getAddress(Long id){
return addressrepository.getAddress(id);
}


}
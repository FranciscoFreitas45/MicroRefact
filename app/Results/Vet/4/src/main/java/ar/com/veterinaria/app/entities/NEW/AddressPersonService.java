package ar.com.veterinaria.app.entities.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import ar.com.veterinaria.app.entities.repository.AddressRepository;
import ar.com.veterinaria.app.entities.Address;
@Service
public class AddressPersonService {

@Autowired
 private AddressRepository addressrepository;


public void setAddress(Integer id,Address address){
addressrepository.setAddress(id,address);
}


public Address getAddress(Integer id){
return addressrepository.getAddress(id);
}


}
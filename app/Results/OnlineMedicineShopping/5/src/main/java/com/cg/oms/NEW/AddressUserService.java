package com.cg.oms.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.oms.repository.AddressRepository;
import com.cg.oms.model.Address;
@Service
public class AddressUserService {

@Autowired
 private AddressRepository addressrepository;


public Address getUserAddress(Integer addressId){
return addressrepository.getUserAddress(addressId);
}


public void setUserAddress(Integer addressId,Address userAddress){
addressrepository.setUserAddress(addressId,userAddress);
}


}
package ar.com.veterinaria.app.entities.JPAImpl;
 import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ar.com.veterinaria.app.entities.Address;
import ar.com.veterinaria.app.entities.dao.AddressDao;
import ar.com.veterinaria.app.entities.helper.daoImpl.AddressManagerDaoImplHelper;
import ar.com.veterinaria.app.entities.repository.AddressRepository;
@Repository
public class AddressDaoImpl implements AddressDao{

 private  Logger logger;

@Autowired
 private  AddressRepository addressRepository;

public AddressDaoImpl() {
    super();
}
@Override
public Address add(Address address){
    try {
        addressRepository.save(address);
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
    }
    return address;
}


@Override
public boolean exist(Address address){
    if (!AddressManagerDaoImplHelper.validate(address)) {
        return false;
    }
    return true;
}


@Override
public Address findById(int id){
    try {
        if (!AddressManagerDaoImplHelper.isDeleted(id)) {
            return addressRepository.findAddressById(id);
        }
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
    }
    return null;
}


@Override
public Address update(int id,Address address){
    if (AddressManagerDaoImplHelper.existId(id)) {
        addressRepository.save(AddressManagerDaoImplHelper.updateAddress(id, address));
        return address;
    }
    return null;
}


@Override
public List<Map<String,Object>> findAll(){
    return AddressManagerDaoImplHelper.findAll();
}


@Override
public void remove(int id){
    if (!AddressManagerDaoImplHelper.isDeleted(id)) {
        Address address = addressRepository.findAddressById(id);
        address.setId(id);
        address.setDeleted(true);
        addressRepository.save(address);
    }
// falta validar id
}


}
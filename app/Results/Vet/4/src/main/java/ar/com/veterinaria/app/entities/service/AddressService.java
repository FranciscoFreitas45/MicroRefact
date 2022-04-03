package ar.com.veterinaria.app.entities.service;
 import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.veterinaria.app.entities.Address;
import ar.com.veterinaria.app.entities.dao.AddressDao;
import ar.com.veterinaria.app.entities.helper.service.AddressManagerServiceHelper;
import ar.com.veterinaria.app.entities.service.contract.AddressContractService;
@Service
@Transactional
public class AddressService implements AddressContractService{

@Autowired
 private  AddressDao addressDao;

public AddressService() {
}
@Override
public Address add(Address t){
    if (t == null) {
        throw new IllegalArgumentException("The passed object cannot be null.");
    }
    return addressDao.add(t);
}


@Override
public boolean exist(Address address){
    if (address == null) {
        throw new IllegalArgumentException("The passed object cannot be null.");
    }
    return addressDao.exist(address);
}


@Override
public boolean isValidInputData(Address address){
    if (AddressManagerServiceHelper.validate(address)) {
        return true;
    }
    return false;
}


@Override
public Address findById(int id){
    if (id <= 0) {
        throw new IllegalArgumentException("ID cannot be 0 or < 0");
    }
    return addressDao.findById(id);
}


@Override
public Address update(int id,Address address){
    if (address == null) {
        throw new IllegalArgumentException("The passed object cannot be null.");
    }
    if (id <= 0) {
        throw new IllegalArgumentException("ID cannot be 0 or < 0 or this id do not exist");
    }
    return addressDao.update(id, address);
}


@Override
public List<Map<String,Object>> findAll(){
    List<Map<String, Object>> result = addressDao.findAll();
    if (result.size() > 0) {
        return result;
    }
    return null;
}


@Override
public void remove(int id){
    if (id <= 0) {
        throw new IllegalArgumentException("ID cannot be 0 or < 0 or this id do not exist");
    }
    addressDao.remove(id);
}


}
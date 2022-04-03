package ar.com.veterinaria.app.entities.helper.daoImpl;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ar.com.veterinaria.app.entities.Address;
import ar.com.veterinaria.app.entities.helper.daoImpl.contract.AddressContractDaoImplHelper;
@Service
public class AddressDaoImplHelper implements AddressContractDaoImplHelper{

 private  Logger logger;


@Override
public boolean deleted(JpaRepository<Address,Integer> repository,Integer id){
    List<Address> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        Address aAddress = result.get(ini);
        if (aAddress.getId().equals(id) && aAddress.isDeleted()) {
            return true;
        }
        ini++;
    }
    return false;
}


@Override
public boolean isDuplicated(JpaRepository<Address,Integer> repository,Address address){
    List<Address> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        Address aBreed = result.get(ini);
        if (aBreed.getName().equals(address.getName())) {
            return true;
        }
        ini++;
    }
    return false;
}


@Override
public Address findByName(JpaRepository<Address,Integer> repository,String t){
    List<Address> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        Address aAddress = result.get(ini);
        if (aAddress.getName().equals(t)) {
            return aAddress;
        }
        ini++;
    }
    return null;
}


@Override
public Address update(JpaRepository<Address,Integer> repository,Integer id,Address address){
    List<Address> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        Address aAddress = result.get(ini);
        if (aAddress.getId().equals(id)) {
            return aAddress;
        }
        ini++;
    }
    return null;
}


@Override
public boolean existId(JpaRepository<Address,Integer> repository,Integer id){
    List<Address> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        Address aAddress = result.get(ini);
        if (aAddress.getId().equals(id)) {
            return true;
        }
        ini++;
    }
    return false;
}


@Override
public List<Map<String,Object>> findAll(JpaRepository<Address,Integer> repository){
    try {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        List<Address> result = repository.findAll();
        for (Address address : result) {
            map = new HashMap<>();
            if (!address.isDeleted()) {
                map.put(address.getId().toString(), address);
                list.add(map);
            }
        }
        return list;
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
    }
    return null;
}


}
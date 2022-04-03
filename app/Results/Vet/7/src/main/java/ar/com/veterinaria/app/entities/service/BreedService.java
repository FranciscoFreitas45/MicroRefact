package ar.com.veterinaria.app.entities.service;
 import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.veterinaria.app.entities.Breed;
import ar.com.veterinaria.app.entities.dao.BreedDao;
import ar.com.veterinaria.app.entities.helper.service.BreedManagerServiceHelper;
import ar.com.veterinaria.app.entities.service.contract.BreedContractService;
@Service
@Transactional
public class BreedService implements BreedContractService{

@Autowired
 private  BreedDao breedDao;

public BreedService() {
}
@Override
public Breed add(Breed t){
    if (t == null) {
        throw new IllegalArgumentException("The passed object cannot be null.");
    }
    return breedDao.add(t);
}


@Override
public boolean exist(Breed breed){
    if (breed == null) {
        throw new IllegalArgumentException("The passed object cannot be null.");
    }
    return breedDao.exist(breed);
}


@Override
public boolean isValidInputData(Breed breed){
    if (BreedManagerServiceHelper.validate(breed)) {
        return true;
    }
    return false;
}


@Override
public Breed findById(int id){
    if (id <= 0) {
        throw new IllegalArgumentException("ID cannot be 0 or < 0");
    }
    return breedDao.findById(id);
}


@Override
public Breed update(int id,Breed breed){
    if (breed == null) {
        throw new IllegalArgumentException("The passed object cannot be null.");
    }
    if (id <= 0) {
        throw new IllegalArgumentException("ID cannot be 0 or < 0 or this id do not exist");
    }
    return breedDao.update(id, breed);
}


@Override
public List<Map<String,Object>> findAll(){
    List<Map<String, Object>> result = breedDao.findAll();
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
    breedDao.remove(id);
}


}
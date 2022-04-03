package ar.com.veterinaria.app.entities.service;
 import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.veterinaria.app.entities.AnimalBreed;
import ar.com.veterinaria.app.entities.dao.AnimalBreedDao;
import ar.com.veterinaria.app.entities.helper.service.AnimalBreedManagerServiceHelper;
import ar.com.veterinaria.app.entities.service.contract.AnimalBreedContractService;
@Service
@Transactional
public class AnimalBreedService implements AnimalBreedContractService{

@Autowired
 private  AnimalBreedDao animalBreedDao;

public AnimalBreedService() {
}
@Override
public AnimalBreed add(AnimalBreed animalBreed){
    if (animalBreed == null) {
        throw new IllegalArgumentException("The passed object cannot be null.");
    }
    return animalBreedDao.add(animalBreed);
}


@Override
public boolean exist(AnimalBreed animalBreed){
    if (animalBreed == null) {
        throw new IllegalArgumentException("The passed object cannot be null.");
    }
    return animalBreedDao.exist(animalBreed);
}


@Override
public boolean isValidInputData(AnimalBreed animalBreed){
    boolean valid = false;
    if (AnimalBreedManagerServiceHelper.validate(animalBreed)) {
        valid = true;
    }
    return valid;
}


@Override
public AnimalBreed findById(int id){
    if (id <= 0) {
        throw new IllegalArgumentException("ID cannot be 0 or < 0");
    }
    return animalBreedDao.findById(id);
}


@Override
public AnimalBreed update(int id,AnimalBreed animalBreed){
    if (animalBreed == null) {
        throw new IllegalArgumentException("The passed object cannot be null.");
    }
    if (id <= 0) {
        throw new IllegalArgumentException("ID cannot be 0 or < 0 or this id do not exist");
    }
    return animalBreedDao.update(id, animalBreed);
}


@Override
public List<Map<String,Object>> findAll(){
    List<Map<String, Object>> result = animalBreedDao.findAll();
    if (result.size() > 0) {
        return result;
    } else {
        return null;
    }
}


@Override
public void remove(int id){
    if (id <= 0) {
        throw new IllegalArgumentException("ID cannot be 0 or < 0 or this id do not exist");
    }
    animalBreedDao.remove(id);
}


}
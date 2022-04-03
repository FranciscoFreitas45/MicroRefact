package ar.com.veterinaria.app.entities.service;
 import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.veterinaria.app.entities.Animal;
import ar.com.veterinaria.app.entities.dao.AnimalDao;
import ar.com.veterinaria.app.entities.helper.service.AnimalManagerServiceHelper;
import ar.com.veterinaria.app.entities.service.contract.AnimalContractService;
@Service
@Transactional
public class AnimalService implements AnimalContractService{

@Autowired
 private  AnimalDao animalDao;

public AnimalService() {
}
@Override
public Animal add(Animal animal){
    if (animal == null) {
        throw new IllegalArgumentException("The passed object cannot be null.");
    }
    return animalDao.add(animal);
}


@Override
public boolean exist(Animal animal){
    if (animal == null) {
        throw new IllegalArgumentException("The passed object cannot be null.");
    }
    return animalDao.exist(animal);
}


@Override
public boolean isValidInputData(Animal animal){
    if (AnimalManagerServiceHelper.validate(animal)) {
        return true;
    }
    return false;
}


@Override
public Animal findById(int id){
    if (id <= 0) {
        throw new IllegalArgumentException("ID cannot be 0 or < 0");
    }
    return animalDao.findById(id);
}


@Override
public Animal update(int id,Animal animal){
    if (animal == null) {
        throw new IllegalArgumentException("The passed object cannot be null.");
    }
    if (id <= 0) {
        throw new IllegalArgumentException("ID cannot be 0 or < 0 or this id do not exist");
    }
    return animalDao.update(id, animal);
}


@Override
public Animal findAnimalByName(String animal){
    if (animal == null) {
        throw new IllegalArgumentException("The passed object cannot be null.");
    }
    return animalDao.findAnimalByName(animal);
}


@Override
public List<Map<String,Object>> findAll(){
    List<Map<String, Object>> result = animalDao.findAll();
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
    animalDao.remove(id);
}


}
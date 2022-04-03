package ar.com.veterinaria.app.entities.helper.daoImpl;
 import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.veterinaria.app.entities.Animal;
import ar.com.veterinaria.app.entities.exception.duplicate.AnimalDuplicatedException;
import ar.com.veterinaria.app.entities.exception.notFound.AnimalNotFoundException;
import ar.com.veterinaria.app.entities.repository.AnimalRepository;
@Service
@Transactional
public class AnimalManagerDaoImplHelper {

@Autowired
 private  AnimalRepository animalRepository;

@Autowired
 private  AnimalDaoImplHelper animalDaoImplHelper;

@SuppressWarnings("static-access")
@Autowired
public AnimalManagerDaoImplHelper(AnimalDaoImplHelper animalDaoImplHelper, AnimalRepository animalRepository) {
    this.animalDaoImplHelper = animalDaoImplHelper;
    this.animalRepository = animalRepository;
}
public Animal updateAnimal(Integer id,Animal animal){
    Animal aAnimal = animalDaoImplHelper.update(animalRepository, id, animal);
    aAnimal.setId(id);
    aAnimal.setName(animal.getName());
    return aAnimal;
}


public boolean isDeleted(Integer id){
    if (animalDaoImplHelper.deleted(animalRepository, id)) {
        throw new AnimalNotFoundException(id);
    }
    return false;
}


public Animal findByName(String name){
    Animal animal = animalDaoImplHelper.findByName(animalRepository, name);
    if (animal != null) {
        return animal;
    }
    throw new AnimalNotFoundException(name);
}


public boolean existId(Integer id){
    if (animalDaoImplHelper.existId(animalRepository, id)) {
        return true;
    }
    throw new AnimalNotFoundException(id);
}


public List<Map<String,Object>> findAll(){
    return animalDaoImplHelper.findAll(animalRepository);
}


public boolean validate(Animal animal){
    if (animalDaoImplHelper.isDuplicated(animalRepository, animal)) {
        throw new AnimalDuplicatedException(animal.getName());
    }
    return false;
}


}
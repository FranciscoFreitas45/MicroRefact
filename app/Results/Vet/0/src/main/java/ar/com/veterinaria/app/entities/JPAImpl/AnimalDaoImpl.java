package ar.com.veterinaria.app.entities.JPAImpl;
 import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ar.com.veterinaria.app.entities.Animal;
import ar.com.veterinaria.app.entities.dao.AnimalDao;
import ar.com.veterinaria.app.entities.helper.daoImpl.AnimalManagerDaoImplHelper;
import ar.com.veterinaria.app.entities.repository.AnimalRepository;
@Repository
public class AnimalDaoImpl implements AnimalDao{

 private  Logger logger;

@Autowired
 private  AnimalRepository animalRepository;

public AnimalDaoImpl() {
    super();
}
@Override
public Animal add(Animal animal){
    try {
        animalRepository.save(animal);
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
    }
    return animal;
}


@Override
public boolean exist(Animal animal){
    if (!AnimalManagerDaoImplHelper.validate(animal)) {
        return false;
    }
    return true;
}


@Override
public Animal findById(int id){
    try {
        return animalRepository.findAnimalById(id);
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
    }
    return null;
}


@Override
public Animal update(int id,Animal animal){
    if (AnimalManagerDaoImplHelper.existId(id)) {
        animalRepository.save(AnimalManagerDaoImplHelper.updateAnimal(id, animal));
        return animal;
    }
    return null;
}


@Override
public Animal findAnimalByName(String name){
    return AnimalManagerDaoImplHelper.findByName(name);
}


@Override
public List<Map<String,Object>> findAll(){
    return AnimalManagerDaoImplHelper.findAll();
}


@Override
public void remove(int id){
    if (!AnimalManagerDaoImplHelper.isDeleted(id)) {
        Animal animal = animalRepository.findAnimalById(id);
        animal.setId(id);
        animal.setDeleted(true);
        animalRepository.save(animal);
    }
}


}
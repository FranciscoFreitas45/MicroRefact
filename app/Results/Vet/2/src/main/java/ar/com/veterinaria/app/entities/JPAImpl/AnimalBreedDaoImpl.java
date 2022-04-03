package ar.com.veterinaria.app.entities.JPAImpl;
 import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ar.com.veterinaria.app.entities.AnimalBreed;
import ar.com.veterinaria.app.entities.dao.AnimalBreedDao;
import ar.com.veterinaria.app.entities.helper.daoImpl.AnimalBreedManagerDaoImplHelper;
import ar.com.veterinaria.app.entities.repository.AnimalBreedRepository;
@Repository
public class AnimalBreedDaoImpl implements AnimalBreedDao{

 private  Logger logger;

@Autowired
 private  AnimalBreedRepository animalBreedRepository;

public AnimalBreedDaoImpl() {
    super();
}
@Override
public AnimalBreed add(AnimalBreed animalBreed){
    if (!AnimalBreedManagerDaoImplHelper.isAssociated(animalBreed)) {
        animalBreedRepository.save(AnimalBreedManagerDaoImplHelper.associate(animalBreed));
    }
    return animalBreed;
}


@Override
public boolean exist(AnimalBreed animalBreed){
    if (!AnimalBreedManagerDaoImplHelper.validate(animalBreed)) {
        return false;
    }
    return true;
}


@Override
public AnimalBreed findById(int id){
    try {
        return animalBreedRepository.findAnimalBreedById(id);
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
    }
    return null;
}


@Override
public AnimalBreed update(int id,AnimalBreed animalBreed){
    if (AnimalBreedManagerDaoImplHelper.existId(id)) {
        animalBreedRepository.save(AnimalBreedManagerDaoImplHelper.update(id, animalBreed));
        return animalBreed;
    }
    return null;
}


@Override
public List<Map<String,Object>> findAll(){
    return AnimalBreedManagerDaoImplHelper.findAll();
}


@Override
public void remove(int id){
    if (!AnimalBreedManagerDaoImplHelper.isDeleted(id)) {
        AnimalBreed animalBreed = animalBreedRepository.findAnimalBreedById(id);
        animalBreed.setId(id);
        animalBreed.setDeleted(true);
        animalBreedRepository.save(animalBreed);
    }
}


}
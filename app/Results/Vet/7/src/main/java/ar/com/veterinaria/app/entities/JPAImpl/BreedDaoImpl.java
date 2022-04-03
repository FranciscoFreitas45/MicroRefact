package ar.com.veterinaria.app.entities.JPAImpl;
 import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ar.com.veterinaria.app.entities.Breed;
import ar.com.veterinaria.app.entities.dao.BreedDao;
import ar.com.veterinaria.app.entities.helper.daoImpl.BreedManagerDaoImplHelper;
import ar.com.veterinaria.app.entities.repository.BreedRepository;
@Repository
public class BreedDaoImpl implements BreedDao{

 private  Logger logger;

@Autowired
 private  BreedRepository breedRepository;

public BreedDaoImpl() {
    super();
}
@Override
public Breed add(Breed breed){
    try {
        breedRepository.save(breed);
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
    }
    return breed;
}


@Override
public boolean exist(Breed breed){
    if (!BreedManagerDaoImplHelper.validate(breed)) {
        return false;
    }
    return true;
}


@Override
public Breed findById(int id){
    try {
        return breedRepository.findBreedById(id);
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
    }
    return null;
}


@Override
public Breed update(int id,Breed breed){
    if (BreedManagerDaoImplHelper.existId(id)) {
        breedRepository.save(BreedManagerDaoImplHelper.updateBreed(id, breed));
        return breed;
    }
    return null;
}


@Override
public List<Map<String,Object>> findAll(){
    return BreedManagerDaoImplHelper.findAll();
}


@Override
public void remove(int id){
    if (!BreedManagerDaoImplHelper.isDeleted(id)) {
        Breed breed = breedRepository.findBreedById(id);
        breed.setId(id);
        breed.setDeleted(true);
        breedRepository.save(breed);
    }
// falta validar id
}


@Override
public Breed findBreedByName(String breed){
    return BreedManagerDaoImplHelper.findBreedByName(breed);
}


}
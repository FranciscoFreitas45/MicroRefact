package ar.com.veterinaria.app.entities.helper.daoImpl;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ar.com.veterinaria.app.entities.Breed;
import ar.com.veterinaria.app.entities.helper.daoImpl.contract.BreedContractDaoImplHelper;
@Service
public class BreedDaoImplHelper implements BreedContractDaoImplHelper{

 private  Logger logger;


@Override
public boolean deleted(JpaRepository<Breed,Integer> repository,Integer id){
    List<Breed> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        Breed aBreed = result.get(ini);
        if (aBreed.getId().equals(id) && aBreed.isDeleted()) {
            return true;
        }
        ini++;
    }
    return false;
}


@Override
public boolean isDuplicated(JpaRepository<Breed,Integer> repository,Breed breed){
    List<Breed> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        Breed aBreed = result.get(ini);
        if (aBreed.getBreed().equals(breed.getBreed())) {
            return true;
        }
        ini++;
    }
    return false;
}


@Override
public Breed findByName(JpaRepository<Breed,Integer> repository,String t){
    List<Breed> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        Breed aBreed = result.get(ini);
        if (aBreed.getBreed().equals(t)) {
            return aBreed;
        }
        ini++;
    }
    return null;
}


@Override
public Breed update(JpaRepository<Breed,Integer> repository,Integer id,Breed name){
    List<Breed> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        Breed aBreed = result.get(ini);
        if (aBreed.getId().equals(id)) {
            return aBreed;
        }
        ini++;
    }
    return null;
}


@Override
public boolean existId(JpaRepository<Breed,Integer> repository,Integer id){
    List<Breed> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        Breed aBreed = result.get(ini);
        if (aBreed.getId().equals(id)) {
            return true;
        }
        ini++;
    }
    return false;
}


@Override
public List<Map<String,Object>> findAll(JpaRepository<Breed,Integer> repository){
    try {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        List<Breed> result = repository.findAll();
        for (Breed breed : result) {
            map = new HashMap<>();
            if (!breed.isDeleted()) {
                map.put(breed.getId().toString(), breed);
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
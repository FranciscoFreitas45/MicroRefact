package ar.com.veterinaria.app.entities.helper.daoImpl;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ar.com.veterinaria.app.entities.Animal;
import ar.com.veterinaria.app.entities.helper.daoImpl.contract.AnimalContractDaoImplHelper;
@Service
public class AnimalDaoImplHelper implements AnimalContractDaoImplHelper{

 private  Logger logger;


@Override
public boolean deleted(JpaRepository<Animal,Integer> repository,Integer id){
    List<Animal> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        Animal aAnimal = result.get(ini);
        if (aAnimal.getId().equals(id) && aAnimal.isDeleted()) {
            return true;
        }
        ini++;
    }
    return false;
}


@Override
public boolean isDuplicated(JpaRepository<Animal,Integer> repository,Animal animal){
    List<Animal> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        Animal aAnimal = result.get(ini);
        if (aAnimal.getName().equals(animal.getName())) {
            return true;
        }
        ini++;
    }
    return false;
}


@Override
public Animal findByName(JpaRepository<Animal,Integer> repository,String t){
    List<Animal> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        Animal aAnimal = result.get(ini);
        if (aAnimal.getName().equals(t)) {
            return aAnimal;
        }
        ini++;
    }
    return null;
}


@Override
public Animal update(JpaRepository<Animal,Integer> repository,Integer id,Animal animal){
    List<Animal> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        Animal aAnimal = result.get(ini);
        if (aAnimal.getId().equals(id)) {
            return aAnimal;
        }
        ini++;
    }
    return null;
}


@Override
public boolean existId(JpaRepository<Animal,Integer> repository,Integer id){
    List<Animal> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        Animal aAnimal = result.get(ini);
        if (aAnimal.getId().equals(id)) {
            return true;
        }
        ini++;
    }
    return false;
}


@Override
public List<Map<String,Object>> findAll(JpaRepository<Animal,Integer> repository){
    try {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        List<Animal> result = repository.findAll();
        for (Animal animal : result) {
            map = new HashMap<>();
            if (!animal.isDeleted()) {
                map.put(animal.getId().toString(), animal);
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
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
import ar.com.veterinaria.app.entities.AnimalBreed;
import ar.com.veterinaria.app.entities.Breed;
import ar.com.veterinaria.app.entities.helper.daoImpl.contract.AnimalBreedContractDaoImplHelper;
@Service
public class AnimalBreedDaoImplHelper implements AnimalBreedContractDaoImplHelper{

 private  Logger logger;


@Override
public boolean deleted(JpaRepository<AnimalBreed,Integer> repository,Integer id){
    List<AnimalBreed> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        AnimalBreed animalBreed = result.get(ini);
        if (animalBreed.getId().equals(id)) {
            if (!animalBreed.isDeleted()) {
                return false;
            }
        }
        ini++;
    }
    return true;
}


@Override
public boolean isDuplicated(JpaRepository<AnimalBreed,Integer> repository,AnimalBreed animal){
    List<AnimalBreed> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        AnimalBreed animalBreed = result.get(ini);
        if (animalBreed.getAnimal().getName().equals(animal.getAnimal().getName()) && animalBreed.getBreed().getBreed().equals(animal.getBreed().getBreed())) {
            return true;
        }
        ini++;
    }
    return false;
}


@Override
public AnimalBreed findAnimalBreedByName(JpaRepository<AnimalBreed,Integer> repository,AnimalBreed animalBreed){
    List<AnimalBreed> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        AnimalBreed aAnimalBreed = result.get(ini);
        if (aAnimalBreed.getAnimal().getName().equals(animalBreed.getAnimal().getName()) && aAnimalBreed.getBreed().getBreed().equals(animalBreed.getBreed().getBreed())) {
            return animalBreed;
        }
        ini++;
    }
    return null;
}


@Deprecated
@Override
public AnimalBreed findByName(JpaRepository<AnimalBreed,Integer> repository,String animalBreed){
    return null;
}


@Override
public AnimalBreed update(JpaRepository<AnimalBreed,Integer> repository,Integer id,AnimalBreed animalBreed){
    List<AnimalBreed> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        AnimalBreed aAnimalBreed = result.get(ini);
        if (aAnimalBreed.getId().equals(id)) {
            return aAnimalBreed;
        }
        ini++;
    }
    return null;
}


@Override
public boolean existId(JpaRepository<AnimalBreed,Integer> repository,Integer id){
    List<AnimalBreed> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        AnimalBreed aAnimal = result.get(ini);
        if (aAnimal.getAnimal().getId().equals(id)) {
            return true;
        }
        ini++;
    }
    return false;
}


@Override
public Animal findAnimalByName(JpaRepository<AnimalBreed,Integer> repository,AnimalBreed animal){
    List<AnimalBreed> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        AnimalBreed aAnimalBreed = result.get(ini);
        if (aAnimalBreed.getAnimal().getName().equals(animal.getAnimal().getName())) {
            return aAnimalBreed.getAnimal();
        }
        ini++;
    }
    return null;
}


@Override
public List<Map<String,Object>> findAll(JpaRepository<AnimalBreed,Integer> repository){
    try {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        List<AnimalBreed> result = repository.findAll();
        for (AnimalBreed animalBreed : result) {
            map = new HashMap<>();
            if (!animalBreed.isDeleted()) {
                map.put(animalBreed.getId().toString(), animalBreed);
                list.add(map);
            }
        }
        return list;
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
    }
    return null;
}


@Override
public Breed findBreedByName(JpaRepository<AnimalBreed,Integer> repository,AnimalBreed breed){
    List<AnimalBreed> result = repository.findAll();
    int ini = 0;
    while (ini < result.size()) {
        AnimalBreed aAnimalBreed = result.get(ini);
        if (aAnimalBreed.getBreed().getBreed().equals(breed.getBreed().getBreed())) {
            return aAnimalBreed.getBreed();
        }
        ini++;
    }
    return null;
}


}
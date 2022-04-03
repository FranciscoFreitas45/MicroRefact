package ar.com.veterinaria.app.entities.helper.daoImpl;
 import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.veterinaria.app.entities.Animal;
import ar.com.veterinaria.app.entities.AnimalBreed;
import ar.com.veterinaria.app.entities.Breed;
import ar.com.veterinaria.app.entities.exception.duplicate.AnimalBreedDuplicatedException;
import ar.com.veterinaria.app.entities.exception.notFound.AnimalBreedNotFoundException;
import ar.com.veterinaria.app.entities.repository.AnimalBreedRepository;
import ar.com.veterinaria.app.entities.repository.AnimalRepository;
import ar.com.veterinaria.app.entities.repository.BreedRepository;
@Service
@Transactional
public class AnimalBreedManagerDaoImplHelper {

@Autowired
 private  AnimalBreedRepository animalBreedRepository;

@Autowired
 private  BreedRepository breedRepository;

@Autowired
 private  AnimalRepository animalRepository;

@Autowired
 private  AnimalBreedDaoImplHelper animalBreedDaoImplHelper;

@Autowired
 private  BreedDaoImplHelper breedDaoImplHelper;

@Autowired
 private  AnimalDaoImplHelper animalDaoImplHelper;

@SuppressWarnings("static-access")
@Autowired
public AnimalBreedManagerDaoImplHelper(AnimalBreedDaoImplHelper animalBreedDaoImplHelper, AnimalBreedRepository animalBreedRepository, BreedDaoImplHelper breedDaoImplHelper, BreedRepository breedRepository, AnimalDaoImplHelper animalDaoImplHelper, AnimalRepository animalRepository) {
    this.animalBreedRepository = animalBreedRepository;
    this.animalBreedDaoImplHelper = animalBreedDaoImplHelper;
    this.breedRepository = breedRepository;
    this.breedDaoImplHelper = breedDaoImplHelper;
    this.animalRepository = animalRepository;
    this.animalDaoImplHelper = animalDaoImplHelper;
}
public boolean isDeleted(int id){
    if (animalBreedDaoImplHelper.deleted(animalBreedRepository, id)) {
        throw new AnimalBreedNotFoundException(id);
    }
    return false;
}


public AnimalBreed update(Integer id,AnimalBreed animalBreed){
    Animal aAnimal = animalDaoImplHelper.update(animalRepository, id, animalBreed.getAnimal());
    aAnimal.setId(id);
    aAnimal.setName(animalBreed.getAnimal().getName());
    Breed bBreed = breedDaoImplHelper.update(breedRepository, id, animalBreed.getBreed());
    bBreed.setId(id);
    bBreed.setBreed(animalBreed.getBreed().getBreed());
    animalBreed.setId(id);
    animalBreed.setAnimal(aAnimal);
    animalBreed.setBreed(bBreed);
    return animalBreed;
}


public boolean existId(int id){
    if (!animalBreedDaoImplHelper.existId(animalBreedRepository, id)) {
        throw new AnimalBreedNotFoundException(id);
    }
    return true;
}


public boolean isAssociated(AnimalBreed animalBreed){
    if (!animalBreedDaoImplHelper.isDuplicated(animalBreedRepository, animalBreed)) {
        return false;
    }
    return true;
}


public List<Map<String,Object>> findAll(){
    return animalBreedDaoImplHelper.findAll(animalBreedRepository);
}


public AnimalBreed associate(AnimalBreed animalBreed){
    Animal animal = animalBreedDaoImplHelper.findAnimalByName(animalBreedRepository, animalBreed);
    Breed breed = animalBreedDaoImplHelper.findBreedByName(animalBreedRepository, animalBreed);
    if (animal == null && breed == null) {
        if ((!animalDaoImplHelper.isDuplicated(animalRepository, animalBreed.getAnimal()) && (!breedDaoImplHelper.isDuplicated(breedRepository, animalBreed.getBreed())))) {
            animal = new Animal();
            breed = new Breed();
            breed.setBreed(animalBreed.getBreed().getBreed());
            animal.setName(animalBreed.getAnimal().getName());
            animalBreed.setBreed(breed);
            animalBreed.setAnimal(animal);
        } else if ((animalDaoImplHelper.isDuplicated(animalRepository, animalBreed.getAnimal()) && (breedDaoImplHelper.isDuplicated(breedRepository, animalBreed.getBreed())))) {
            Animal aAnimalFound = animalDaoImplHelper.findByName(animalRepository, animalBreed.getAnimal().getName());
            Breed bBreedFound = breedDaoImplHelper.findByName(breedRepository, animalBreed.getBreed().getBreed());
            animalBreed.setAnimal(aAnimalFound);
            animalBreed.setBreed(bBreedFound);
        }
    } else if (animal != null && breed == null) {
        breed = new Breed();
        breed.setBreed(animalBreed.getBreed().getBreed());
        if ((animalDaoImplHelper.isDuplicated(animalRepository, animalBreed.getAnimal()))) {
            Animal aAnimal = animalDaoImplHelper.update(animalRepository, animal.getId(), animalBreed.getAnimal());
            animalBreed.setBreed(breed);
            animalBreed.setAnimal(aAnimal);
        }
    } else if (animal == null && breed != null) {
        animal = new Animal();
        animal.setName(animalBreed.getAnimal().getName());
        if (breedDaoImplHelper.isDuplicated(breedRepository, animalBreed.getBreed())) {
            Breed bBreed = breedDaoImplHelper.update(breedRepository, breed.getId(), animalBreed.getBreed());
            breedDaoImplHelper.update(breedRepository, breed.getId(), animalBreed.getBreed());
            animalBreed.setAnimal(animal);
            animalBreed.setBreed(bBreed);
        }
    }
    return animalBreed;
}


public boolean validate(AnimalBreed animalBreed){
    if ((animalBreedDaoImplHelper.findAnimalByName(animalBreedRepository, animalBreed) != null) && (animalBreedDaoImplHelper.findBreedByName(animalBreedRepository, animalBreed) != null)) {
        throw new AnimalBreedDuplicatedException(animalBreed);
    }
    return false;
}


}
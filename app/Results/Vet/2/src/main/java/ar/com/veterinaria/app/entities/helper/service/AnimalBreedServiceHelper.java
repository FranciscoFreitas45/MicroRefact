package ar.com.veterinaria.app.entities.helper.service;
 import org.springframework.stereotype.Service;
import ar.com.veterinaria.app.bo.validatorPattern.ValidatorPatternServiceHelper;
import ar.com.veterinaria.app.entities.AnimalBreed;
import ar.com.veterinaria.app.entities.helper.service.contract.AnimalBreedContractServiceHelper;
@Service
public class AnimalBreedServiceHelper implements AnimalBreedContractServiceHelper{

public AnimalBreedServiceHelper() {
    super();
}
@Override
public boolean isValidName(AnimalBreed animalBreed){
    boolean valid = false;
    if (ValidatorPatternServiceHelper.getNamePattern().matcher(animalBreed.getAnimal().getName()).matches() && ValidatorPatternServiceHelper.getNamePattern().matcher(animalBreed.getBreed().getBreed()).matches()) {
        valid = true;
    }
    return valid;
}


@Override
public boolean isValidLengthName(AnimalBreed animalBreed){
    boolean valid = false;
    if (animalBreed.getAnimal().getName().length() <= 70 && animalBreed.getBreed().getBreed().length() <= 50) {
        valid = true;
    }
    return valid;
}


}
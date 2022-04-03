package ar.com.veterinaria.app.entities.helper.service;
 import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.veterinaria.app.entities.AnimalBreed;
import ar.com.veterinaria.app.entities.exception.invalidData.AnimalBreedInvalidDataException;
import ar.com.veterinaria.app.entities.exception.validationLength.AnimalBreedValidationLengthDataException;
@Service
@Transactional
public class AnimalBreedManagerServiceHelper {

@Autowired
 private  AnimalBreedServiceHelper animalBreedServiceHelper;

@SuppressWarnings("static-access")
@Autowired
public AnimalBreedManagerServiceHelper(AnimalBreedServiceHelper animalBreedServiceHelper) {
    this.animalBreedServiceHelper = animalBreedServiceHelper;
}
public boolean validate(AnimalBreed animalBreed){
    if (!animalBreedServiceHelper.isValidName(animalBreed)) {
        throw new AnimalBreedInvalidDataException(animalBreed);
    } else if (!animalBreedServiceHelper.isValidLengthName(animalBreed)) {
        throw new AnimalBreedValidationLengthDataException(animalBreed);
    }
    return true;
}


}
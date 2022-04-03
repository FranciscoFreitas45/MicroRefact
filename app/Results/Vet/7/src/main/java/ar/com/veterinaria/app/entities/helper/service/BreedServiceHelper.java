package ar.com.veterinaria.app.entities.helper.service;
 import org.springframework.stereotype.Service;
import ar.com.veterinaria.app.bo.validatorPattern.ValidatorPatternServiceHelper;
import ar.com.veterinaria.app.entities.Breed;
import ar.com.veterinaria.app.entities.helper.service.contract.BreedContractServiceHelper;
@Service
public class BreedServiceHelper implements BreedContractServiceHelper{


@Override
public boolean isValidName(Breed breed){
    if (ValidatorPatternServiceHelper.getNamePattern().matcher(breed.getBreed()).matches()) {
        return true;
    }
    return false;
}


@Override
public boolean isValidLengthName(Breed breed){
    if (breed.getBreed().length() < 50) {
        return true;
    }
    return false;
}


}
package ar.com.veterinaria.app.entities.helper.service;
 import org.springframework.stereotype.Service;
import ar.com.veterinaria.app.bo.validatorPattern.ValidatorPatternServiceHelper;
import ar.com.veterinaria.app.entities.Animal;
import ar.com.veterinaria.app.entities.helper.service.contract.AnimalContractServiceHelper;
@Service
public class AnimalServiceHelper implements AnimalContractServiceHelper{

public AnimalServiceHelper() {
    super();
}
@Override
public boolean isValidName(Animal animal){
    if (ValidatorPatternServiceHelper.getNamePattern().matcher(animal.getName()).matches()) {
        return true;
    }
    return false;
}


@Override
public boolean isValidLengthName(Animal animal){
    if (animal.getName().length() < 70) {
        return true;
    }
    return false;
}


}
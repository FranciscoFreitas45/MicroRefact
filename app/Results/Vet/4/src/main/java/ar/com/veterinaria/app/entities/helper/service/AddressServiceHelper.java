package ar.com.veterinaria.app.entities.helper.service;
 import org.springframework.stereotype.Service;
import ar.com.veterinaria.app.bo.validatorPattern.ValidatorPatternServiceHelper;
import ar.com.veterinaria.app.entities.Address;
import ar.com.veterinaria.app.entities.helper.service.contract.AddressContractServiceHelper;
@Service
public class AddressServiceHelper implements AddressContractServiceHelper{


@Override
public boolean isValidNumber(Address address){
    boolean valid = false;
    if (ValidatorPatternServiceHelper.getNumberPattern().matcher(address.getNumber().toString()).matches()) {
        valid = true;
    }
    return valid;
}


@Override
public boolean isValidName(Address address){
    boolean valid = false;
    if (ValidatorPatternServiceHelper.getNamePattern().matcher(address.getName()).matches()) {
        valid = true;
    }
    return valid;
}


@Override
public boolean isValidLengthName(Address address){
    boolean valid = false;
    if (address.getName().length() < 50) {
        valid = true;
    }
    return valid;
}


}
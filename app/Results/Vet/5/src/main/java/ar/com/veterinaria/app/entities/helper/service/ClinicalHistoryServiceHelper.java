package ar.com.veterinaria.app.entities.helper.service;
 import org.springframework.stereotype.Service;
import ar.com.veterinaria.app.bo.validatorPattern.ValidatorPatternServiceHelper;
import ar.com.veterinaria.app.entities.ClinicalHistory;
import ar.com.veterinaria.app.entities.helper.service.contract.ClinicalHistoryContractServiceHelper;
@Service
public class ClinicalHistoryServiceHelper implements ClinicalHistoryContractServiceHelper{


@Override
public boolean isValidName(ClinicalHistory clinicalHistory){
    if (ValidatorPatternServiceHelper.getNamePattern().matcher(clinicalHistory.getClinicalHistory()).matches()) {
        return true;
    }
    return false;
}


@Override
public boolean isValidClinicalHistory(ClinicalHistory clinicalHistory){
    if (ValidatorPatternServiceHelper.getNumberLetterPattern().matcher(clinicalHistory.getClinicalHistory()).matches()) {
        return true;
    }
    return false;
}


@Override
public boolean isValidLengthName(ClinicalHistory clinicalHistory){
    if (clinicalHistory.getClinicalHistory().length() < 50) {
        return true;
    }
    return false;
}


}
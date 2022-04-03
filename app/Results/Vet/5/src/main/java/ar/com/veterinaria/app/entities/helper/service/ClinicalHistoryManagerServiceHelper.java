package ar.com.veterinaria.app.entities.helper.service;
 import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.veterinaria.app.entities.ClinicalHistory;
import ar.com.veterinaria.app.entities.exception.invalidData.ClinicalHistoryInvalidDataException;
import ar.com.veterinaria.app.entities.exception.validationLength.ClinicalHistoryValidationLengthDataException;
@Service
@Transactional
public class ClinicalHistoryManagerServiceHelper {

@Autowired
 private  ClinicalHistoryServiceHelper clinicalHistoryServiceHelper;

@SuppressWarnings("static-access")
@Autowired
public ClinicalHistoryManagerServiceHelper(ClinicalHistoryServiceHelper clinicalServiceHelper) {
    this.clinicalHistoryServiceHelper = clinicalServiceHelper;
}
public boolean validate(ClinicalHistory clinicalHistory){
    if (!clinicalHistoryServiceHelper.isValidName(clinicalHistory)) {
        throw new ClinicalHistoryInvalidDataException(clinicalHistory);
    } else if (!clinicalHistoryServiceHelper.isValidLengthName(clinicalHistory)) {
        throw new ClinicalHistoryValidationLengthDataException(clinicalHistory);
    }
    return true;
}


}
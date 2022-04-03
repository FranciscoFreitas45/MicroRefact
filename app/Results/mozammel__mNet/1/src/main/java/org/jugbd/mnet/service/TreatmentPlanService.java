package org.jugbd.mnet.service;
 import org.jugbd.mnet.domain.TreatmentPlan;
import org.jugbd.mnet.domain.enums.RegistrationType;
import org.springframework.stereotype.Component;
@Component
public interface TreatmentPlanService {


public TreatmentPlan save(TreatmentPlan treatmentPlan,RegistrationType registrationType)
;

public TreatmentPlan findOne(Long id)
;

}
package org.jugbd.mnet.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.dao.TreatmentPlanDao;
import org.jugbd.mnet.domain.TreatmentPlan;
@Service
public class TreatmentPlanOutdoorRegisterService {

@Autowired
 private TreatmentPlanDao treatmentplandao;


public OutdoorRegister setTreatmentPlan(Long id,TreatmentPlan treatmentPlan){
return treatmentplandao.setTreatmentPlan(id,treatmentPlan);
}


public TreatmentPlan getTreatmentPlan(Long id){
return treatmentplandao.getTreatmentPlan(id);
}


}
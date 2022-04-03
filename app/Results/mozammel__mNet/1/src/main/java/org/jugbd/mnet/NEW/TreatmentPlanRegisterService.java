package org.jugbd.mnet.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.dao.TreatmentPlanDao;
import org.jugbd.mnet.domain.TreatmentPlan;
@Service
public class TreatmentPlanRegisterService {

@Autowired
 private TreatmentPlanDao treatmentplandao;


public void setTreatmentPlan(Long id,TreatmentPlan treatmentPlan){
treatmentplandao.setTreatmentPlan(id,treatmentPlan);
}


public TreatmentPlan getTreatmentPlan(Long id){
return treatmentplandao.getTreatmentPlan(id);
}


}
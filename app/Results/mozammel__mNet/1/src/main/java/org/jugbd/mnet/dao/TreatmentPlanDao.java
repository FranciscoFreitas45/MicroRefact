package org.jugbd.mnet.dao;
 import org.jugbd.mnet.domain.TreatmentPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TreatmentPlanDao extends JpaRepository<TreatmentPlan, Long>{


public void setTreatmentPlan(Long id,TreatmentPlan treatmentPlan);

public TreatmentPlan getTreatmentPlan(Long id);

public OutdoorRegister setTreatmentPlan(Long id,TreatmentPlan treatmentPlan);

public TreatmentPlan getTreatmentPlan(Long id);

}
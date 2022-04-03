package org.jugbd.mnet.dao;
 import org.jugbd.mnet.domain.OutdoorRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface OutdoorRegisterRepository extends JpaRepository<OutdoorRegister, Long>{


public Examination setOutdoorRegister(Long id,OutdoorRegister outdoorRegister);

public OutdoorRegister getOutdoorRegister(Long id);

public TreatmentPlan setOutdoorRegister(Long id,OutdoorRegister outdoorRegister);

public OutdoorRegister getOutdoorRegister(Long id);

public Visit setOutdoorRegister(Long id,OutdoorRegister outdoorRegister);

public OutdoorRegister getOutdoorRegister(Long id);

public Vital setOutdoorRegister(Long id,OutdoorRegister outdoorRegister);

public OutdoorRegister getOutdoorRegister(Long id);

public Set<OutdoorRegister> getOutdoorRegisters(Long id);

public Patient setOutdoorRegisters(Long id,Set<OutdoorRegister> outdoorRegisters);

public ChiefComplaint setOutdoorRegister(Long id,OutdoorRegister outdoorRegister);

public OutdoorRegister getOutdoorRegister(Long id);

public OutdoorRegister setExamination(Long id,Examination examination);

public OutdoorRegister setTreatmentPlan(Long id,TreatmentPlan treatmentPlan);

public OutdoorRegister setChiefComplaint(Long id,ChiefComplaint chiefComplaint);

}
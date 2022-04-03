package org.jugbd.mnet.dao;
 import org.jugbd.mnet.domain.OutdoorRegister;
import org.jugbd.mnet.domain.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface RegisterDao extends JpaRepository<Register, Long>{


@Query("select o from Register o where o.patient.id=:patientId and o.status='ACTIVE'")
public List<Register> findActiveRegisterByPatientId(Long patientId)
;

@Query("select o from Register o where o.patient.id=:patientId")
public List<Register> findAllRegisterByPatientId(Long patientId)
;

@Query("select o from OutdoorRegister o where o.patient.id=:patientId")
public List<OutdoorRegister> findAllOutdoorRegisterByPatient_Id(Long patientId)
;

public Examination setRegister(Long id,Register register);

public Register getRegister(Long id);

public TreatmentPlan setRegister(Long id,Register register);

public Register getRegister(Long id);

public Visit setRegister(Long id,Register register);

public Register getRegister(Long id);

public void setRegister(Long id,Register register);

public Register getRegister(Long id);

public void setRegister(Long id,Register register);

public Register getRegister(Long id);

public Vital setRegister(Long id,Register register);

public Register getRegister(Long id);

public void setRegisters(Long id,Set<Register> registers);

public Set<Register> getRegisters(Long id);

public ChiefComplaint setRegister(Long id,Register register);

public Register getRegister(Long id);

public Register getRegister(Long id);

public void setRegister(Long id,Register register);

public void setRegister(Long id,Register register);

public Register getRegister(Long id);

public void setRegister(Long id,Register register);

public Register getRegister(Long id);

public void setRegister(Long id,Register register);

public Register getRegister(Long id);

public void setExamination(Long id,Examination examination);

public void setTreatmentPlan(Long id,TreatmentPlan treatmentPlan);

public void setPictureInformation(Long id,PictureInformation pictureInformation);

public void setMedicalHistory(Long id,MedicalHistory medicalHistory);

public void setChiefComplaint(Long id,ChiefComplaint chiefComplaint);

public void setLifeStyle(Long id,LifeStyle lifeStyle);

public void setComplicationManagement(Long id,ComplicationManagement complicationManagement);

}
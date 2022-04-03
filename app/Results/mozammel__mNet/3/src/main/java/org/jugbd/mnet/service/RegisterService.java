package org.jugbd.mnet.service;
 import org.jugbd.mnet.domain;
import org.jugbd.mnet.domain.enums.RegistrationType;
import org.jugbd.mnet.utils.Either;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Set;
@Component
public interface RegisterService {


public void closeRegister(Long registerId,RegistrationType registrationType)
;

public Register findActiveRegisterByPatientId(Long patientId)
;

public TreatmentPlan findTreatmentPlan(Long registerId,RegistrationType registrationType)
;

public void saveOutcome(String outcome,Long registerId,RegistrationType registrationType)
;

public Set<Investigation> findInvestigations(Long registerId)
;

public OutdoorRegister save(OutdoorRegister register)
;

public Register findOne(Long registerId)
;

public MedicalHistory findMedicalHistory(Long registerId)
;

public void update(OutdoorRegister register)
;

public Diagnosis findDiagnosis(Long registerId,RegistrationType registrationType)
;

public Object findRegister(Long registerId,RegistrationType registrationType)
;

public Either<Register,OutdoorRegister> findRegisterEither(Long registerId,RegistrationType registrationType)
;

public Vital getLastVital(Long registerId,RegistrationType registrationType)
;

public Register convertOutdoorRegisterToIndoorRegister(Long registerId,Register register)
;

public List<Visit> getVisits(Long registerId,RegistrationType registrationType)
;

public void saveRemarks(String remark,Long registerId,RegistrationType registrationType)
;

public OutdoorRegister findOpdRegister(Long id)
;

public Set<OperationalDetail> findOperationalDetailList(Long registerId)
;

public void addVital(Vital vital,Long registerId)
;

public List<OutdoorRegister> findAllOutdoorRegisterByPatientId(Long patientId)
;

public List<Register> findAllRegisterByPatientId(Long patientId)
;

public Examination findExamination(Long registerId,RegistrationType registrationType)
;

public ChiefComplaint findChiefcomplaints(Long registerId,RegistrationType registrationType)
;

}
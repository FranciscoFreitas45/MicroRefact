package org.jugbd.mnet.service;
 import org.jugbd.mnet.dao.OutdoorRegisterRepository;
import org.jugbd.mnet.dao.PatientDao;
import org.jugbd.mnet.dao.RegisterDao;
import org.jugbd.mnet.dao.VitalDao;
import org.jugbd.mnet.domain;
import org.jugbd.mnet.domain.enums.RegistrationType;
import org.jugbd.mnet.domain.enums.Status;
import org.jugbd.mnet.utils.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.jugbd.mnet.Interface.PatientDao;
import org.jugbd.mnet.Interface.VitalDao;
import org.jugbd.mnet.DTO.Either;
@Service
@Transactional
public class RegisterServiceImpl implements RegisterService{

 private  Logger log;

@Autowired
 private  RegisterDao registerDao;

@Autowired
 private  PatientDao patientDao;

@Autowired
 private  OutdoorRegisterRepository outdoorRegisterRepository;

@Autowired
 private  VitalDao vitalDao;


@Override
public void closeRegister(Long registerId,RegistrationType registrationType){
    if (registrationType == RegistrationType.OUTDOOR) {
        OutdoorRegister register = outdoorRegisterRepository.findOne(registerId);
        register.setStatus(Status.CLOSED);
        register.setStopDatetime(new Date());
        outdoorRegisterRepository.save(register);
    } else if (registrationType == RegistrationType.INDOOR) {
        Register register = registerDao.findOne(registerId);
        register.setStatus(Status.CLOSED);
        register.setStopDatetime(new Date());
        registerDao.save(register);
    }
}


@Override
public Register findActiveRegisterByPatientId(Long patientId){
    return Optional.ofNullable(registerDao.findActiveRegisterByPatientId(patientId)).map(registers -> registers.stream().findFirst().map(this::initializeRegister).orElse(null)).orElse(null);
}


@Override
public TreatmentPlan findTreatmentPlan(Long registerId,RegistrationType registrationType){
    if (registrationType == RegistrationType.OUTDOOR) {
        OutdoorRegister outdoorRegister = outdoorRegisterRepository.findOne(registerId);
        return outdoorRegister.getTreatmentPlan();
    } else if (registrationType == RegistrationType.INDOOR) {
        Register indoorRegister = registerDao.findOne(registerId);
        return indoorRegister.getTreatmentPlan();
    }
    return null;
}


@Override
public OutdoorRegister save(OutdoorRegister register){
    if (register.getId() != null) {
        OutdoorRegister registerFromDb = outdoorRegisterRepository.findOne(register.getId());
        PatientContact patientContact = registerFromDb.getPatientContact();
        patientContact.setContactPerson(register.getPatientContact().getContactPerson());
        patientContact.setEmergencyContactNumber(register.getPatientContact().getEmergencyContactNumber());
        patientContact.setRelationship(register.getPatientContact().getRelationship());
        patientContact.setComments(register.getPatientContact().getComments());
        registerFromDb.setRegistrationId(register.getRegistrationId());
        return outdoorRegisterRepository.save(registerFromDb);
    } else {
        register.setPatient(patientDao.findOne(register.getPatient().getId()));
        register.setStartDatetime(new Date());
        register.setStatus(Status.ACTIVE);
        return outdoorRegisterRepository.save(register);
    }
}


@Override
public Register findOne(Long registerId){
    Register register = registerDao.findOne(registerId);
    initializeRegister(register);
    return register;
}


@Override
public MedicalHistory findMedicalHistory(Long registerId){
    return registerDao.findOne(registerId).getMedicalHistory();
}


@Override
public void update(OutdoorRegister register){
    outdoorRegisterRepository.save(register);
}


@Override
public Vital getLastVital(Long registerId,RegistrationType registrationType){
    log.info("getLastVital() registerId:{}, registrationType : {}", registerId, registrationType);
    return findRegisterEither(registerId, registrationType).fold(register -> getVital(register.getVitals()), outdoorRegister -> getVital(outdoorRegister.getVitals()));
}


@Override
public Register convertOutdoorRegisterToIndoorRegister(Long registerId,Register register){
    OutdoorRegister outdoorRegister = outdoorRegisterRepository.findOne(registerId);
    // TODO have to ask if there is any need to copy all these
    // Diagnosis diagnosis = outdoorRegister.getDiagnosis();
    // Utils.copyBeanProperties(diagnosis, register.getDiagnosis(),
    // new String[]{"burns", "congenitalAnomaly", "neoplastic", "postInfective", "traumatic", "aesthetic",
    // "comment", "icd10"});
    // TreatmentPlan treatmentPlan = outdoorRegister.getTreatmentPlan();
    // Utils.copyBeanProperties(treatmentPlan, register.getTreatmentPlan(), new String[]{"treatmentPlanType", "otherTreatmentPlanType", "typeOfConservativeTreatment", "stsgOrFtsg", "flapPedicled", "freeFlap", "tissueExpansion", "fasciotomyOrEscharotomy", "implantInsertion", "comment"});
    // 
    // ChiefComplaint chiefComplaint = outdoorRegister.getChiefComplaint();
    // Utils.copyBeanProperties(chiefComplaint, register.getChiefComplaint(), new String[]{""});
    // 
    // Set<Vital> vitals = outdoorRegister.getVitals().stream().map(vital -> {
    // Vital newVital = new Vital();
    // Utils.copyBeanProperties(newVital, newVital, new String[]{""});
    // 
    // return newVital;
    // }).collect(Collectors.toSet());
    // 
    // register.setVitals(vitals);
    register.setPatient(patientDao.findOne(register.getPatient().getId()));
    register.setStartDatetime(new Date());
    register.setStatus(Status.ACTIVE);
    register.setOutdoorRegister(outdoorRegister.getId());
    registerDao.save(register);
    outdoorRegister.setStatus(Status.CLOSED);
    outdoorRegister.setStopDatetime(new Date());
    outdoorRegisterRepository.save(outdoorRegister);
    return registerDao.save(register);
}


@Override
public List<Visit> getVisits(Long registerId,RegistrationType registrationType){
    return findRegisterEither(registerId, registrationType).fold(register -> (register.getVisits()), outdoorRegister -> (outdoorRegister.getVisits())).stream().filter(visit -> visit.getStatus() == Status.ACTIVE).sorted((o1, o2) -> o2.getCreatedDate().compareTo(o1.getCreatedDate())).collect(Collectors.toList());
}


@Override
public void saveRemarks(String remark,Long registerId,RegistrationType registrationType){
    if (registrationType == RegistrationType.OUTDOOR) {
        OutdoorRegister outdoorRegister = outdoorRegisterRepository.findOne(registerId);
        outdoorRegister.setRemarks(remark);
        outdoorRegisterRepository.save(outdoorRegister);
    }
}


@Override
public OutdoorRegister findOpdRegister(Long id){
    return outdoorRegisterRepository.findOne(id);
}


@Override
public Set<OperationalDetail> findOperationalDetailList(Long registerId){
    Register register = registerDao.findOne(registerId);
    register.getOperationalDetails().size();
    return register.getOperationalDetails();
}


public Vital getVital(Set<Vital> vitals){
    return vitals.stream().filter(vital -> vital.getStatus() == Status.ACTIVE).sorted((e1, e2) -> e2.getCreatedDate().compareTo(e1.getCreatedDate())).findFirst().orElse(null);
}


@Override
public Examination findExamination(Long registerId,RegistrationType registrationType){
    if (registrationType == RegistrationType.OUTDOOR) {
        OutdoorRegister outdoorRegister = outdoorRegisterRepository.findOne(registerId);
        return outdoorRegister.getExamination();
    } else if (registrationType == RegistrationType.INDOOR) {
        Register indoorRegister = registerDao.findOne(registerId);
        return indoorRegister.getExamination();
    }
    return null;
}


@Override
public ChiefComplaint findChiefcomplaints(Long registerId,RegistrationType registrationType){
    if (registrationType == RegistrationType.OUTDOOR) {
        OutdoorRegister outdoorRegister = outdoorRegisterRepository.findOne(registerId);
        return outdoorRegister.getChiefComplaint();
    } else if (registrationType == RegistrationType.INDOOR) {
        Register indoorRegister = registerDao.findOne(registerId);
        return indoorRegister.getChiefComplaint();
    }
    return null;
}


@Override
public void saveOutcome(String outcome,Long registerId,RegistrationType registrationType){
    if (registrationType == RegistrationType.OUTDOOR) {
        OutdoorRegister outdoorRegister = outdoorRegisterRepository.findOne(registerId);
        outdoorRegister.setOutcome(outcome);
        outdoorRegisterRepository.save(outdoorRegister);
    }
}


@Override
public Set<Investigation> findInvestigations(Long registerId){
    Register register = registerDao.findOne(registerId);
    register.getInvestigation().size();
    return register.getInvestigation();
}


@Override
public Diagnosis findDiagnosis(Long registerId,RegistrationType registrationType){
    if (registrationType == RegistrationType.OUTDOOR) {
        OutdoorRegister outdoorRegister = outdoorRegisterRepository.findOne(registerId);
        return outdoorRegister.getDiagnosis();
    } else if (registrationType == RegistrationType.INDOOR) {
        Register indoorRegister = registerDao.findOne(registerId);
        return indoorRegister.getDiagnosis();
    }
    return null;
}


@Override
public Object findRegister(Long registerId,RegistrationType registrationType){
    if (registrationType == RegistrationType.OUTDOOR) {
        return outdoorRegisterRepository.findOne(registerId);
    } else if (registrationType == RegistrationType.INDOOR) {
        return registerDao.findOne(registerId);
    }
    return null;
}


@Override
public Either<Register,OutdoorRegister> findRegisterEither(Long registerId,RegistrationType registrationType){
    return registrationType == RegistrationType.OUTDOOR ? Either.right(outdoorRegisterRepository.findOne(registerId)) : Either.left(registerDao.findOne(registerId));
}


public Register initializeRegister(Register register){
    // log.info("initializeRegister() ={}", register);
    // Ref: http://stackoverflow.com/questions/19928568/hibernate-best-practice-to-pull-all-lazy-collections
    register.getVitals().size();
    register.getOperationalDetails().size();
    register.getInvestigation().size();
    return register;
}


@Override
public void addVital(Vital vital,Long registerId){
}


@Override
public List<OutdoorRegister> findAllOutdoorRegisterByPatientId(Long patientId){
    return registerDao.findAllOutdoorRegisterByPatient_Id(patientId);
}


@Override
public List<Register> findAllRegisterByPatientId(Long patientId){
    return registerDao.findAllRegisterByPatientId(patientId);
}


}
package org.jugbd.mnet.service;
 import org.jugbd.mnet.dao.RegisterDao;
import org.jugbd.mnet.dao.VitalDao;
import org.jugbd.mnet.domain.OutdoorRegister;
import org.jugbd.mnet.domain.Register;
import org.jugbd.mnet.domain.Vital;
import org.jugbd.mnet.domain.enums.RegistrationType;
import org.jugbd.mnet.domain.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.jugbd.mnet.Interface.RegisterDao;
import org.jugbd.mnet.Interface.RegisterService;
@Transactional
@Service
public class VitalServiceImpl implements VitalService{

@Autowired
 private  RegisterDao registerDao;

@Autowired
 private  RegisterService registerService;

@Autowired
 private  VitalDao vitalDao;


@Override
public List<Vital> findByRegisterId(Long registerId,RegistrationType registrationType){
    return registerService.findRegisterEither(registerId, registrationType).fold(Register::getVitals, OutdoorRegister::getVitals).stream().filter(vital -> vital.getStatus() == Status.ACTIVE).collect(Collectors.toList());
}


@Override
public Vital findOne(Long id){
    return vitalDao.findOne(id);
}


@Override
public Vital saveByRegisterId(Vital vital,Long registerId,RegistrationType registrationType){
    Vital fold = registerService.findRegisterEither(registerId, registrationType).fold(register -> vital.setPatient(register.getPatient()).setRegister(register), outdoorRegister -> vital.setPatient(outdoorRegister.getPatient()).setOutdoorRegister(outdoorRegister));
    fold.setStatus(Status.ACTIVE);
    return vitalDao.save(fold);
}


@Override
public Long delete(Long id,RegistrationType registrationType){
    Vital vital = vitalDao.findOne(id);
    vital.setStatus(Status.DELETED);
    Vital savedVital = vitalDao.save(vital);
    return registrationType == RegistrationType.OUTDOOR ? savedVital.getOutdoorRegister().getId() : savedVital.getRegister().getId();
}


}
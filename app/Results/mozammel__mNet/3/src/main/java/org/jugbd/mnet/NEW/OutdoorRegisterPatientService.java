package org.jugbd.mnet.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.dao.OutdoorRegisterRepository;
import org.jugbd.mnet.domain.OutdoorRegister;
@Service
public class OutdoorRegisterPatientService {

@Autowired
 private OutdoorRegisterRepository outdoorregisterrepository;


public Set<OutdoorRegister> getOutdoorRegisters(Long id){
return outdoorregisterrepository.getOutdoorRegisters(id);
}


public Patient setOutdoorRegisters(Long id,Set<OutdoorRegister> outdoorRegisters){
return outdoorregisterrepository.setOutdoorRegisters(id,outdoorRegisters);
}


}
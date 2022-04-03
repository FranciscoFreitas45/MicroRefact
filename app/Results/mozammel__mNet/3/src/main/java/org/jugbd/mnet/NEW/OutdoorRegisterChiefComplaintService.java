package org.jugbd.mnet.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.dao.OutdoorRegisterRepository;
import org.jugbd.mnet.domain.OutdoorRegister;
@Service
public class OutdoorRegisterChiefComplaintService {

@Autowired
 private OutdoorRegisterRepository outdoorregisterrepository;


public ChiefComplaint setOutdoorRegister(Long id,OutdoorRegister outdoorRegister){
return outdoorregisterrepository.setOutdoorRegister(id,outdoorRegister);
}


public OutdoorRegister getOutdoorRegister(Long id){
return outdoorregisterrepository.getOutdoorRegister(id);
}


}
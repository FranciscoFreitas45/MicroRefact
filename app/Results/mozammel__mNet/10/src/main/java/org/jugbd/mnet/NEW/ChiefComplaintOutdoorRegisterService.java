package org.jugbd.mnet.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.dao.ChiefComplaintDao;
import org.jugbd.mnet.domain.ChiefComplaint;
@Service
public class ChiefComplaintOutdoorRegisterService {

@Autowired
 private ChiefComplaintDao chiefcomplaintdao;


public OutdoorRegister setChiefComplaint(Long id,ChiefComplaint chiefComplaint){
return chiefcomplaintdao.setChiefComplaint(id,chiefComplaint);
}


public ChiefComplaint getChiefComplaint(Long id){
return chiefcomplaintdao.getChiefComplaint(id);
}


}
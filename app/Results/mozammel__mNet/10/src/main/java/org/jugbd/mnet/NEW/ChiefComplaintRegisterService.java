package org.jugbd.mnet.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.dao.ChiefComplaintDao;
import org.jugbd.mnet.domain.ChiefComplaint;
@Service
public class ChiefComplaintRegisterService {

@Autowired
 private ChiefComplaintDao chiefcomplaintdao;


public ChiefComplaint getChiefComplaint(Long id){
return chiefcomplaintdao.getChiefComplaint(id);
}


public void setChiefComplaint(Long id,ChiefComplaint chiefComplaint){
chiefcomplaintdao.setChiefComplaint(id,chiefComplaint);
}


}
package org.jugbd.mnet.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.dao.VitalDao;
import org.jugbd.mnet.domain.Vital;
@Service
public class VitalOutdoorRegisterService {

@Autowired
 private VitalDao vitaldao;


public OutdoorRegister setVitals(Long id,Set<Vital> vitals){
return vitaldao.setVitals(id,vitals);
}


public Set<Vital> getVitals(Long id){
return vitaldao.getVitals(id);
}


}
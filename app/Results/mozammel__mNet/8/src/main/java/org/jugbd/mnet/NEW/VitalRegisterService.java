package org.jugbd.mnet.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.dao.VitalDao;
import org.jugbd.mnet.domain.Vital;
@Service
public class VitalRegisterService {

@Autowired
 private VitalDao vitaldao;


public Set<Vital> getVitals(Long id){
return vitaldao.getVitals(id);
}


public void setVitals(Long id,Set<Vital> vitals){
vitaldao.setVitals(id,vitals);
}


}
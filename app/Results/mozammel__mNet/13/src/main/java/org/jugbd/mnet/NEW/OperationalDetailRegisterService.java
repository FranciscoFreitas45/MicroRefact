package org.jugbd.mnet.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.dao.OperationalDetailDao;
import org.jugbd.mnet.domain.OperationalDetail;
@Service
public class OperationalDetailRegisterService {

@Autowired
 private OperationalDetailDao operationaldetaildao;


public Set<OperationalDetail> getOperationalDetails(Long id){
return operationaldetaildao.getOperationalDetails(id);
}


public void setOperationalDetails(Long id,Set<OperationalDetail> operationalDetails){
operationaldetaildao.setOperationalDetails(id,operationalDetails);
}


}
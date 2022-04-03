package org.jugbd.mnet.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.dao.InvestigationDao;
import org.jugbd.mnet.domain.Investigation;
@Service
public class InvestigationRegisterService {

@Autowired
 private InvestigationDao investigationdao;


public Set<Investigation> getInvestigation(Long id){
return investigationdao.getInvestigation(id);
}


public void setInvestigation(Long id,Set<Investigation> investigation){
investigationdao.setInvestigation(id,investigation);
}


}
package org.jugbd.mnet.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.dao.VisitRepository;
import org.jugbd.mnet.domain.Visit;
@Service
public class VisitRegisterService {

@Autowired
 private VisitRepository visitrepository;


public Set<Visit> getVisits(Long id){
return visitrepository.getVisits(id);
}


public void setVisits(Long id,Set<Visit> visits){
visitrepository.setVisits(id,visits);
}


}
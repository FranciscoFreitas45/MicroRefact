package edu.xr.campusweibo.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import edu.xr.campusweibo.repository.PersistentTokenRepository;
import edu.xr.campusweibo.domain.PersistentToken;
@Service
public class PersistentTokenUserService {

@Autowired
 private PersistentTokenRepository persistenttokenrepository;


public void setPersistentTokens(Long id,Set<PersistentToken> persistentTokens){
persistenttokenrepository.setPersistentTokens(id,persistentTokens);
}


public Set<PersistentToken> getPersistentTokens(Long id){
return persistenttokenrepository.getPersistentTokens(id);
}


}
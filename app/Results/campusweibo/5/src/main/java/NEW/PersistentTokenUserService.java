package NEW;
 import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class PersistentTokenUserService {

 private PersistentTokenRepository persistenttokenrepository;


public void setPersistentTokens(Long id,Set<PersistentToken> persistentTokens){
persistenttokenrepository.setPersistentTokens(id,persistentTokens);
}


public Set<PersistentToken> getPersistentTokens(Long id){
return persistenttokenrepository.getPersistentTokens(id);
}


}
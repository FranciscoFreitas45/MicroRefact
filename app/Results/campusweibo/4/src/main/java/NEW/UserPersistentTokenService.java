package NEW;
 import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class UserPersistentTokenService {

 private UserRepository userrepository;


public User getUser(Long Long){
return userrepository.getUser(Long);
}


public void setUser(Long Long,User user){
userrepository.setUser(Long,user);
}


}
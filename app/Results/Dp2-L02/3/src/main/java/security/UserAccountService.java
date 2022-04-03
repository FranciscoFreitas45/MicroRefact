package security;
 import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@Transactional
public class UserAccountService {

@Autowired
 private  UserAccountRepository userAccountRepository;


public UserAccount save(UserAccount a){
    return this.userAccountRepository.save(a);
}


public List<UserAccount> findAll(){
    return this.userAccountRepository.findAll();
}


}
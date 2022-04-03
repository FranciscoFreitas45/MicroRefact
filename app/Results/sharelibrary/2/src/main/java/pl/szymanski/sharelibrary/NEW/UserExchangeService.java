package pl.szymanski.sharelibrary.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pl.szymanski.sharelibrary.repositories.jpa.UserJPARepository;
import pl.szymanski.sharelibrary.entity.User;
@Service
public class UserExchangeService {

@Autowired
 private UserJPARepository userjparepository;


}
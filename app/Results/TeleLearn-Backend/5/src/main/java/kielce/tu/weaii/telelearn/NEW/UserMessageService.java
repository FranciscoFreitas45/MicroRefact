package kielce.tu.weaii.telelearn.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import kielce.tu.weaii.telelearn.repositories.jpa.UserJPARepository;
import kielce.tu.weaii.telelearn.models.User;
@Service
public class UserMessageService {

@Autowired
 private UserJPARepository userjparepository;


}
package pt.iscte.hospital.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iscte.hospital.repositories.user.UserRepository;
import pt.iscte.hospital.entities.User;
@Service
public class UserMessageService {

@Autowired
 private UserRepository userrepository;


}
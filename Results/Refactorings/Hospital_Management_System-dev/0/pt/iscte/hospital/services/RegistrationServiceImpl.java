import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pt.iscte.hospital.entities.User;
@Service
public class RegistrationServiceImpl implements pt.iscte.hospital.services.RegistrationService,RegistrationService{


public void changeEncryptPassword(User user,String password){
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    user.setPassword(encoder.encode(password));
}


public void encryptPassword(User user){
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    user.setPassword(encoder.encode(user.getPassword()));
}


}
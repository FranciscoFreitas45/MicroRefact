import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pt.iscte.hospital.entities.Receptionist;
import pt.iscte.hospital.entities.User;
import pt.iscte.hospital.services.RegistrationService;
import pt.iscte.hospital.services.user.UserService;
@Component
public class ReceptionistAdmin {

 private  RegistrationService registrationService;

 private  UserService userService;


@Scheduled(fixedRate = 100000000)
public void createAdmin(){
    User user = userService.findByUsername("admin");
    if (user != null) {
        System.out.println("Utilizador admin já existe.");
    } else {
        Receptionist receptionist = new Receptionist();
        receptionist.setName("Admin Receptionist");
        receptionist.setEmail("admin@hospital.upskill.com");
        receptionist.setUsername("admin");
        receptionist.setPassword("admin");
        receptionist.setPhotoURL("user-male.jpg");
        registrationService.encryptPassword(receptionist);
        userService.addUser(receptionist);
        System.out.println("Registado admin");
    }
}


}
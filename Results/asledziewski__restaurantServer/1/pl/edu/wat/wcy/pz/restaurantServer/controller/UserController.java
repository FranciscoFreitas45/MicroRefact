import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation;
import pl.edu.wat.wcy.pz.restaurantServer.email.MailService;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Reservation;
import pl.edu.wat.wcy.pz.restaurantServer.entity.User;
import pl.edu.wat.wcy.pz.restaurantServer.service.UserService;
import java.util.Collection;
import java.util.Optional;
@AllArgsConstructor
@RestController
@CrossOrigin
public class UserController {

 private  UserService userService;

 private  MailService mailService;


@GetMapping(value = "/users/{id}")
public User getUserById(Long id){
    Optional<User> user = userService.getUserById(id);
    return user.orElse(null);
}


@GetMapping(value = "/users/{id}/reservations")
public Collection<Reservation> getUserReservations(Long id){
    return userService.getUserReservations(id);
}


@GetMapping("/users")
public Collection<User> getUsers(){
    return userService.getUsers();
}


@PostMapping("/users")
public void addUser(User user){
    userService.addUser(user);
    mailService.sendEmail(user.getMail(), "Welcome to Restaurant!", "Hello " + user.getFirstName() + ", thanks for using our system!");
}


@DeleteMapping("/users/{id}")
public void deleteUser(Long id){
    Optional<User> user = userService.getUserById(id);
    userService.deleteUserById(id);
}


@PutMapping("/users/{id}")
public void updateUser(Long id,User user){
    userService.updateUser(id, user);
}


}
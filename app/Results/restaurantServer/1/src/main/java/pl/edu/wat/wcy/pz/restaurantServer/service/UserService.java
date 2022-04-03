package pl.edu.wat.wcy.pz.restaurantServer.service;
 import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.Reservation;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.User;
import pl.edu.wat.wcy.pz.restaurantServer.Interface.UserRepository;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class UserService {

 private  UserRepository userRepository;

 private  PasswordEncoder encoder;


public Optional<User> getUserById(Long id){
    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
        return user;
    } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
    }
}


public List<Reservation> getUserReservations(Long id){
    Optional<User> user = getUserById(id);
    return user.get().getReservations();
}


public List<User> getUsers(){
    return userRepository.findAll();
}


public void addUser(User user){
    List<User> userList = userRepository.findAll();
    if (userList.stream().map(User::getMail).anyMatch(user.getMail()::equals)) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User with this email already exists.");
    } else {
        String password = encoder.encode(user.getPassword());
        user.setPassword(password);
        userRepository.save(user);
    }
}


public void updateUser(Long id,User user){
    Optional<User> oldUser = userRepository.findById(id);
    if (!oldUser.isPresent())
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
    else {
        user.setUserId(id);
        userRepository.save(user);
    }
}


public void deleteUserById(Long id){
    if (userRepository.findById(id).isPresent()) {
        userRepository.deleteById(id);
    } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
    }
}


}
package pl.szymanski.sharelibrary.repositories.adapters;
 import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.szymanski.sharelibrary.entity.User;
import pl.szymanski.sharelibrary.repositories.jpa.UserJPARepository;
import pl.szymanski.sharelibrary.repositories.ports.UserRepository;
import java.util.List;
import java.util.Optional;
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{

 private  UserJPARepository userJPARepository;


@Override
public Optional<User> getUserByEmail(String email){
    return userJPARepository.findUserByEmail(email);
}


@Override
public Optional<User> getUserById(Long id){
    return userJPARepository.findById(id);
}


@Override
public Optional<User> getUserByUsername(String username){
    return userJPARepository.findUserByUsername(username);
}


@Override
public List<User> getUsers(){
    return userJPARepository.findAll();
}


@Override
public Optional<User> getUserByUsernameOrEmail(String username,String email){
    return userJPARepository.findUserByUsernameOrEmail(username, email);
}


@Override
public User saveUser(User user){
    return userJPARepository.saveAndFlush(user);
}


}
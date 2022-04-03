package kielce.tu.weaii.telelearn.repositories.adapters;
 import kielce.tu.weaii.telelearn.models.User;
import kielce.tu.weaii.telelearn.repositories.jpa.UserJPARepository;
import kielce.tu.weaii.telelearn.repositories.ports.UserRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public class UserRepositoryImpl extends BaseCRUDRepositoryImpl<User>implements UserRepository{

 private  UserJPARepository userJPARepository;

public UserRepositoryImpl(UserJPARepository userJPARepository) {
    super(userJPARepository);
    this.userJPARepository = userJPARepository;
}
@Override
public Optional<User> getUserByEmail(String email){
    return userJPARepository.findByEmail(email);
}


@Override
public Optional<User> getUserByLoginOrEmail(String loginOrEmail){
    return userJPARepository.findByUsernameOrEmail(loginOrEmail, loginOrEmail);
}


@Override
public Optional<User> getUserByLogin(String login){
    return userJPARepository.findByUsername(login);
}


}
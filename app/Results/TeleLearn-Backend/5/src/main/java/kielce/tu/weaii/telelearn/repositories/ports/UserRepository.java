package kielce.tu.weaii.telelearn.repositories.ports;
 import kielce.tu.weaii.telelearn.models.User;
import java.util.Optional;
public interface UserRepository extends BaseCRUDRepository<User>{


public Optional<User> getUserByEmail(String email)
;

public Optional<User> getUserByLoginOrEmail(String loginOrEmail)
;

public Optional<User> getUserByLogin(String login)
;

}
package kielce.tu.weaii.telelearn.services.ports;
 import kielce.tu.weaii.telelearn.models.User;
import kielce.tu.weaii.telelearn.requests.LoginRequest;
import kielce.tu.weaii.telelearn.requests.UserPasswordPatchRequest;
import kielce.tu.weaii.telelearn.security.JwtAuthenticationResponse;
import java.util.List;
public interface UserService {


public User updatePassword(Long id,UserPasswordPatchRequest request)
;

public User getById(Long id)
;

public List<User> getList()
;

public User getUserByLoginOrEmail(String loginOrEmail)
;

public JwtAuthenticationResponse getJwt(LoginRequest loginRequest)
;

public void checkAvailability(String login,String email)
;

public void verifyPermissionToUser(Long userId)
;

public void delete(Long id)
;

public boolean isCurrentUserOrAdmin(Long userId)
;

}
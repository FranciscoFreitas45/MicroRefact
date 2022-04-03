package kielce.tu.weaii.telelearn.services.adapters;
 import kielce.tu.weaii.telelearn.exceptions.AuthorizationException;
import kielce.tu.weaii.telelearn.exceptions.users.EmailNotAvailableException;
import kielce.tu.weaii.telelearn.exceptions.users.InvalidPasswordException;
import kielce.tu.weaii.telelearn.exceptions.users.UserNotFoundException;
import kielce.tu.weaii.telelearn.exceptions.users.UsernameNotAvailableException;
import kielce.tu.weaii.telelearn.models.User;
import kielce.tu.weaii.telelearn.models.UserRole;
import kielce.tu.weaii.telelearn.repositories.ports.UserRepository;
import kielce.tu.weaii.telelearn.requests.LoginRequest;
import kielce.tu.weaii.telelearn.requests.UserPasswordPatchRequest;
import kielce.tu.weaii.telelearn.security.JwtAuthenticationResponse;
import kielce.tu.weaii.telelearn.security.JwtTokenProvider;
import kielce.tu.weaii.telelearn.security.UserServiceDetailsImpl;
import kielce.tu.weaii.telelearn.services.ports.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

 private  UserRepository userRepository;

 private  AuthenticationManager authenticationManager;

 private  JwtTokenProvider tokenProvider;

 private  PasswordEncoder passwordEncoder;

 private  UserServiceDetailsImpl userServiceDetails;


@Override
public User updatePassword(Long id,UserPasswordPatchRequest request){
    User user = getById(id);
    if (!passwordEncoder.matches(String.valueOf(request.getOldPassword()), user.getPassword())) {
        throw new InvalidPasswordException();
    }
    user.setPassword(passwordEncoder.encode(String.valueOf(request.getNewPassword())).toCharArray());
    return userRepository.save(user);
}


public User getById(Long id){
    return userRepository.getById(id).orElseThrow(() -> new UserNotFoundException(id));
}


@Override
public List<User> getList(){
    return userRepository.getAll();
}


@Override
public User getUserByLoginOrEmail(String loginOrEmail){
    return userRepository.getUserByLoginOrEmail(loginOrEmail).orElseThrow(() -> new UserNotFoundException(loginOrEmail));
}


@Override
public JwtAuthenticationResponse getJwt(LoginRequest loginRequest){
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), String.valueOf(loginRequest.getPassword())));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = tokenProvider.generateToken(authentication);
    return new JwtAuthenticationResponse(jwt);
}


@Override
public void checkAvailability(String login,String email){
    if (userRepository.getUserByLogin(login).isPresent()) {
        throw new UsernameNotAvailableException(login);
    }
    checkAvailability(email);
}


@Override
public void verifyPermissionToUser(Long userId){
    if (!isCurrentUserOrAdmin(userId)) {
        throw new AuthorizationException("użytkownik", userServiceDetails.getCurrentUser().getId(), userId);
    }
}


@Override
@Transactional
public void delete(Long id){
    userRepository.delete(getById(id));
}


@Override
public boolean isCurrentUserOrAdmin(Long userId){
    User currentUser = userServiceDetails.getCurrentUser();
    return currentUser != null && (currentUser.getId().equals(userId) || currentUser.getUserRole().equals(UserRole.ADMIN));
}


}
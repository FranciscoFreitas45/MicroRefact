package run.halo.app.service;
 import java.util.Optional;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import run.halo.app.exception.ForbiddenException;
import run.halo.app.exception.NotFoundException;
import run.halo.app.model.entity.User;
import run.halo.app.model.enums.MFAType;
import run.halo.app.model.params.UserParam;
import run.halo.app.service.base.CrudService;
public interface UserService extends CrudService<User, Integer>{

 private String LOGIN_FAILURE_COUNT_KEY;

 private int MAX_LOGIN_TRY;

 private int LOCK_MINUTES;


public void setPassword(User user,String plainPassword)
;

@NonNull
public Optional<User> getCurrentUser()
;

@NonNull
public User createBy(UserParam userParam)
;

@NonNull
public User updatePassword(String oldPassword,String newPassword,Integer userId)
;

public void mustNotExpire(User user)
;

@NonNull
public User getByUsernameOfNonNull(String username)
;

@NonNull
public Optional<User> getByEmail(String email)
;

@NonNull
public Optional<User> getByUsername(String username)
;

@NonNull
public User getByEmailOfNonNull(String email)
;

public boolean verifyUser(String username,String password)
;

public boolean passwordMatch(User user,String plainPassword)
;

@NonNull
public User updateMFA(MFAType mfaType,String mfaKey,Integer userId)
;

}
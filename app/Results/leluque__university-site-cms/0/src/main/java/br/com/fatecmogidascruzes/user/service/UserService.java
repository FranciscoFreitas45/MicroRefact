package br.com.fatecmogidascruzes.user.service;
 import java.util.Optional;
import org.springframework.data.domain.Page;
import br.com.fatecmogidascruzes.data.SearchCriteria;
import br.com.fatecmogidascruzes.exception.InexistentOrDisabledEntity;
import br.com.fatecmogidascruzes.service.BaseService;
import br.com.fatecmogidascruzes.user.User;
public interface UserService extends BaseService<User, Long>{


public Optional<User> getByActionToken(String accessToken)
;

public Optional<User> checkWhetherExistesOtherUserWithTheSameUserName(String userName,Long id)
;

public Optional<User> getByUsername(String name)
;

public Page<User> getEnabledByFilter(SearchCriteria searchCriteria)
;

public Optional<User> getByAccessToken(String accessToken)
;

public void doRecoveryPassword(String username) throws InexistentOrDisabledEntity
;

}
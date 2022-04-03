package edu.xr.campusweibo.repository;
 import edu.xr.campusweibo.domain.PersistentToken;
import edu.xr.campusweibo.domain.User;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PersistentTokenRepository extends JpaRepository<PersistentToken, String>{


public List<PersistentToken> findByTokenDateBefore(LocalDate localDate)
;

public List<PersistentToken> findByUser(User user)
;

public void setPersistentTokens(Long id,Set<PersistentToken> persistentTokens);

public Set<PersistentToken> getPersistentTokens(Long id);

}
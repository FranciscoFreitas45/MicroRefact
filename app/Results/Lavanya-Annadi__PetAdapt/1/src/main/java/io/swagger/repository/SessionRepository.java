package io.swagger.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import io.swagger.model.Address;
import io.swagger.model.Session;
import io.swagger.model.User;
@Transactional
public interface SessionRepository extends CrudRepository<Session, Long>, JpaRepository<Session, Long>{


public Session findBySessionId(String session)
;

public void setStatus(Long id,Boolean status);

public void setUser(Long id,User user);

public void setSessionId(Long id,String sessionId);

public void setIpAddress(Long id,String ipAddress);

public void setMedic(Long id,Medic medic);

}
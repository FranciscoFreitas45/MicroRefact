package io.swagger.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import io.swagger.model.Provider;
import io.swagger.model.User;
@Transactional
public interface ProviderRepository extends CrudRepository<Provider, Long>, JpaRepository<Provider, Long>{


public Provider findByEmail(String email)
;

public void setProvider(Long id,Provider provider);

public Provider getProvider(Long id);

}
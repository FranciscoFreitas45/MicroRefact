package upce.semprace.eshop.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import upce.semprace.eshop.entity.Uzivatel;
import java.util.Optional;
public interface UzivatelRepository extends JpaRepository<Uzivatel, Long>{


public Optional<Uzivatel> findByUsername(String username)
;

public Uzivatel findByJmeno(String jmeno)
;

public Optional<Uzivatel> findById(Long id)
;

public void removeUzivatelById(Long id)
;

public Boolean existsByUsername(String username)
;

public Boolean existsByEmail(String email)
;

public Uzivatel findByEmail(String email)
;

public void setUzivatel(Long id,Uzivatel uzivatel);

public Uzivatel getUzivatel(Long id);

public void setPrijmeni(Long id,String prijmeni);

public void setAdresa(Long id,String adresa);

public void setRoles(Long id,Set<Role> roles);

}
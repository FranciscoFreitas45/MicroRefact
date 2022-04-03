package gov.cdc.nccdphp.repositories;
 import gov.cdc.nccdphp.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ManagerRepository extends JpaRepository<Manager, Long>{


public List<Manager> findByActiveTrue()
;

public Manager findByEmail(String email)
;

}
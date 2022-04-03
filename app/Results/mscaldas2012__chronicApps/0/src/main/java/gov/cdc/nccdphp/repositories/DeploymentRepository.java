package gov.cdc.nccdphp.repositories;
 import gov.cdc.nccdphp.domain.Deployment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface DeploymentRepository extends JpaRepository<Deployment, Long>{


public List<Deployment> findByProjectId(Long id)
;

}
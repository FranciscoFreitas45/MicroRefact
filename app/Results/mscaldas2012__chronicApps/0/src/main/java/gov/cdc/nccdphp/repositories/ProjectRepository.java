package gov.cdc.nccdphp.repositories;
 import gov.cdc.nccdphp.domain.Project;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface ProjectRepository extends JpaRepository<Project, Long>{


@Query("SELECT p FROM Project p LEFT JOIN FETCH p.deployments where p.id = :id")
public Project findById(Long id)
;

public List<Project> findByDivisionAbbreviation(String abbreviation)
;

public List<Project> findAllByOrderByDivisionAbbreviation()
;

}
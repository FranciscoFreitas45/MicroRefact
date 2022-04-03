package gov.cdc.nccdphp.repositories;
 import gov.cdc.nccdphp.domain.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
public interface DivisionRepository extends JpaRepository<Division, Integer>{


public Division findByAbbreviation(String abbreviation)
;

}
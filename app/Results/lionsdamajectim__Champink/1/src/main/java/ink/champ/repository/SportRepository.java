package ink.champ.repository;
 import ink.champ.models.Sport;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface SportRepository extends JpaRepository<Sport, Long>{


public List<Sport> findSportByNameContainingIgnoreCase(String search,Sort sort)
;

}
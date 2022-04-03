package restock.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import restock.entities.SubFamilia;
public interface SubfamiliaRepository extends JpaRepository<SubFamilia, Integer>{


public List<SubFamilia> findByFamiliaId(Integer famId)
;

}
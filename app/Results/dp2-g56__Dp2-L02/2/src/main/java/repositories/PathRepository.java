package repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import domain.Path;
@Repository
public interface PathRepository extends JpaRepository<Path, Integer>{


}
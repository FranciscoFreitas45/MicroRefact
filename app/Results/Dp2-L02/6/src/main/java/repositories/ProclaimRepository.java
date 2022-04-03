package repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import domain.Proclaim;
@Repository
public interface ProclaimRepository extends JpaRepository<Proclaim, Integer>{


}
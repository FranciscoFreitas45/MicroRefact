package repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import domain.History;
@Repository
public interface HistoryRepository extends JpaRepository<History, Integer>{


}
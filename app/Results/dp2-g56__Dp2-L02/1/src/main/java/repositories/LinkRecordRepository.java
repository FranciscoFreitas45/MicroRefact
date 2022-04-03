package repositories;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import domain.LinkRecord;
@Repository
public interface LinkRecordRepository extends JpaRepository<LinkRecord, Integer>{


}